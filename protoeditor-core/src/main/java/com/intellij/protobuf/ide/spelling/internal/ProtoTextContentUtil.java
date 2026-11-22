/*
 * Copyright 2013-2026 consulo.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.protobuf.ide.spelling.internal;

import com.intellij.protobuf.lang.psi.ProtoStringPart;
import com.intellij.protobuf.lang.psi.ProtoStringValue;
import com.intellij.protobuf.lang.util.ProtoString;
import consulo.annotation.access.RequiredReadAction;
import consulo.document.util.TextRange;
import consulo.language.ast.IElementType;
import consulo.language.psi.PsiComment;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiUtilCore;
import consulo.language.spellcheker.text.TextContent;
import consulo.language.spellcheker.text.TextContent.TextDomain;
import consulo.language.spellcheker.text.TextContentBuilder;
import consulo.language.spellcheker.text.util.PsiUtils;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Text extraction helpers shared by protobuf and prototext text extractors.
 *
 * @author VISTALL
 * @since 2026-11-02
 */
public final class ProtoTextContentUtil {
    private static final TextContentBuilder COMMENT_BUILDER =
        TextContentBuilder.FromPsi.removingIndents(" \t/*#").removingLineSuffixes(" \t*/");

    private ProtoTextContentUtil() {
    }

    /**
     * Joins the comment with adjacent comments of the same type which satisfy {@code accepted}.
     */
    @RequiredReadAction
    public static @Nullable TextContent buildComment(PsiComment comment, Predicate<PsiComment> accepted) {
        IElementType type = PsiUtilCore.getElementType(comment);
        List<PsiElement> roots = PsiUtils.getNotSoDistantSimilarSiblings(
            comment,
            it -> it instanceof PsiComment other && PsiUtilCore.getElementType(other) == type && accepted.test(other)
        );
        List<TextContent> fragments = new ArrayList<>();
        for (PsiElement root : roots) {
            TextContent fragment = COMMENT_BUILDER.build(root, TextDomain.COMMENTS);
            if (fragment != null) {
                fragments.add(fragment);
            }
        }
        return TextContent.joinWithWhitespace('\n', fragments);
    }

    /**
     * Builds one text from all parts of a (possibly concatenated) string value, without quotes and with escapes handled.
     */
    @RequiredReadAction
    public static @Nullable TextContent buildString(ProtoStringValue value, TextDomain domain) {
        List<TextContent> parts = new ArrayList<>();
        for (ProtoStringPart part : value.getStringParts()) {
            TextContent content = buildStringPart(part, domain);
            if (content != null) {
                parts.add(content);
            }
        }
        return TextContent.joinWithWhitespace(' ', parts);
    }

    @RequiredReadAction
    private static @Nullable TextContent buildStringPart(ProtoStringPart part, TextDomain domain) {
        int partStart = part.getTextRange().getStartOffset();
        TextRange valueRange = part.getTextRangeNoQuotes().shiftLeft(partStart);
        TextContent content = TextContentBuilder.FromPsi.build(part, domain, valueRange);
        if (content == null) {
            return null;
        }

        List<TextRange> whitespaceEscapes = new ArrayList<>();
        List<TextRange> otherEscapes = new ArrayList<>();
        collectEscapes(part.getParsedString(), valueRange.getEndOffset(), partStart, whitespaceEscapes, otherEscapes);

        TextContent result = replaceWithSpaces(content, whitespaceEscapes);
        if (result == null) {
            return null;
        }

        List<TextContent.Exclusion> exclusions = new ArrayList<>();
        for (TextRange escape : otherEscapes) {
            TextRange rangeInText = result.fileRangeToText(escape);
            if (rangeInText != null && !rangeInText.isEmpty()) {
                exclusions.add(TextContent.Exclusion.markUnknown(rangeInText));
            }
        }
        return exclusions.isEmpty() ? result : result.excludeRanges(exclusions);
    }

    private static void collectEscapes(ProtoString parsed,
                                       int valueEnd,
                                       int partStart,
                                       List<TextRange> whitespaceEscapes,
                                       List<TextRange> otherEscapes) {
        String original = parsed.getOriginal();
        String value = parsed.toString();
        int i = 0;
        while (i < value.length()) {
            int start = parsed.getOriginalOffset(i);
            int next = i + 1;
            while (next < value.length() && parsed.getOriginalOffset(next) == start) {
                next++;
            }
            int end = next < value.length() ? parsed.getOriginalOffset(next) : valueEnd;
            if (start < original.length() && original.charAt(start) == '\\' && end > start) {
                TextRange fileRange = new TextRange(start + partStart, end + partStart);
                if (next - i == 1 && Character.isWhitespace(value.charAt(i))) {
                    whitespaceEscapes.add(fileRange);
                }
                else {
                    otherEscapes.add(fileRange);
                }
            }
            i = next;
        }
    }

    private static @Nullable TextContent replaceWithSpaces(TextContent content, List<TextRange> fileRanges) {
        if (fileRanges.isEmpty()) {
            return content;
        }
        List<TextContent> components = new ArrayList<>();
        int textStart = 0;
        for (TextRange fileRange : fileRanges) {
            TextRange rangeInText = content.fileRangeToText(fileRange);
            if (rangeInText == null || rangeInText.getStartOffset() < textStart) {
                continue;
            }
            TextContent component = content.subText(new TextRange(textStart, rangeInText.getStartOffset()));
            if (component != null) {
                components.add(component);
            }
            textStart = rangeInText.getEndOffset();
        }
        TextContent tail = content.subText(new TextRange(textStart, content.length()));
        if (tail != null) {
            components.add(tail);
        }
        return TextContent.joinWithWhitespace(' ', components);
    }
}
