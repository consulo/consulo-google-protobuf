/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.protobuf.ide.documentation;

import com.intellij.protobuf.lang.PbLanguage;
import com.intellij.protobuf.lang.psi.PbCommentOwner;
import com.intellij.protobuf.lang.psi.util.PbCommentUtil;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.documentation.AbstractDocumentationProvider;
import consulo.language.editor.documentation.DocumentationProvider;
import consulo.language.editor.documentation.LanguageDocumentationProvider;
import consulo.language.psi.PsiComment;
import consulo.language.psi.PsiElement;
import consulo.util.lang.StringUtil;
import jakarta.annotation.Nonnull;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A {@link DocumentationProvider} for proto elements.
 */
@ExtensionImpl
public class PbDocumentationProvider extends AbstractDocumentationProvider implements LanguageDocumentationProvider {
  @Nullable
  @Override
  public
  @Nls
  String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
    if (!(element instanceof PbCommentOwner)) {
      return null;
    }

    PbCommentOwner owner = (PbCommentOwner)element;
    List<PsiComment> comments = owner.getComments();
    if (comments.isEmpty()) {
      return null;
    }

    StringBuilder commentBuilder = new StringBuilder("<pre>");
    for (String line : PbCommentUtil.extractText(comments)) {
      commentBuilder.append(StringUtil.escapeXmlEntities(line));
      commentBuilder.append("\n");
    }
    commentBuilder.append("</pre>");

    return commentBuilder.toString();
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return PbLanguage.INSTANCE;
  }
}
