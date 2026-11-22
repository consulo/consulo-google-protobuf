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
package com.intellij.protobuf.ide.spelling;

import com.intellij.protobuf.ide.spelling.internal.ProtoTextContentUtil;
import com.intellij.protobuf.lang.PbLanguage;
import com.intellij.protobuf.lang.psi.PbOptionExpression;
import com.intellij.protobuf.lang.psi.PbStringValue;
import com.intellij.protobuf.lang.psi.PbTextStringValue;
import com.intellij.protobuf.lang.psi.ProtoStringPart;
import com.intellij.protobuf.lang.psi.ProtoStringValue;
import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.psi.PsiComment;
import consulo.language.psi.PsiElement;
import consulo.language.spellcheker.text.CommonTextExtractor;
import consulo.language.spellcheker.text.TextContent;
import consulo.language.spellcheker.text.TextContent.TextDomain;
import consulo.language.spellcheker.text.TextExtractor;

import java.util.List;
import java.util.Set;

/**
 * Extracts natural language text from {@code .proto} files: comments and string option values.
 * Other strings (import paths, syntax, package etc.) are not extracted.
 *
 * @author VISTALL
 * @since 2026-11-02
 */
@ExtensionImpl
public class PbTextExtractor extends TextExtractor {
    @Override
    @RequiredReadAction
    protected List<TextContent> buildTextContents(PsiElement element, Set<TextDomain> allowedDomains) {
        if (element instanceof PsiComment) {
            return CommonTextExtractor.buildCommonTextContents(element, allowedDomains);
        }
        if (element instanceof ProtoStringPart && allowedDomains.contains(TextDomain.LITERALS)) {
            PsiElement value = element.getParent();
            if (value instanceof ProtoStringValue stringValue && isOptionValue(stringValue)) {
                TextContent content = ProtoTextContentUtil.buildString(stringValue, TextDomain.LITERALS);
                return content == null ? List.of() : List.of(content);
            }
        }
        return List.of();
    }

    private static boolean isOptionValue(ProtoStringValue value) {
        // aggregate option values are written in text format
        return value instanceof PbTextStringValue
            || value instanceof PbStringValue && value.getParent() instanceof PbOptionExpression;
    }

    @Override
    public Language getLanguage() {
        return PbLanguage.INSTANCE;
    }
}
