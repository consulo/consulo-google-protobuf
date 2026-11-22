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
import com.intellij.protobuf.lang.PbTextLanguage;
import com.intellij.protobuf.lang.psi.ProtoStringPart;
import com.intellij.protobuf.lang.psi.ProtoStringValue;
import com.intellij.protobuf.lang.resolve.directive.SchemaDirective;
import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.psi.PsiComment;
import consulo.language.psi.PsiElement;
import consulo.language.spellcheker.text.TextContent;
import consulo.language.spellcheker.text.TextContent.TextDomain;
import consulo.language.spellcheker.text.TextExtractor;
import org.jspecify.annotations.Nullable;

import java.util.Set;

/**
 * Extracts natural language text from prototext files: comments (except schema directives
 * such as {@code # proto-file:} and {@code # proto-message:}) and string values.
 *
 * @author VISTALL
 * @since 2026-11-02
 */
@ExtensionImpl
public class PbTextFormatTextExtractor extends TextExtractor {
    @Override
    @RequiredReadAction
    protected @Nullable TextContent buildTextContent(PsiElement element, Set<TextDomain> allowedDomains) {
        if (element instanceof PsiComment comment) {
            if (!allowedDomains.contains(TextDomain.COMMENTS)) {
                return null;
            }
            SchemaDirective directive = SchemaDirective.find(comment.getContainingFile());
            if (directive != null && directive.getSchemaComment(comment) != null) {
                return null;
            }
            return ProtoTextContentUtil.buildComment(comment, it -> directive == null || directive.getSchemaComment(it) == null);
        }
        if (element instanceof ProtoStringPart && allowedDomains.contains(TextDomain.LITERALS)
            && element.getParent() instanceof ProtoStringValue stringValue) {
            return ProtoTextContentUtil.buildString(stringValue, TextDomain.LITERALS);
        }
        return null;
    }

    @Override
    public Language getLanguage() {
        return PbTextLanguage.INSTANCE;
    }
}
