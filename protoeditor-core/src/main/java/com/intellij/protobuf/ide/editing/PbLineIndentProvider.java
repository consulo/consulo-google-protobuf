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
package com.intellij.protobuf.ide.editing;

import com.google.common.collect.ImmutableMap;
import com.intellij.protobuf.lang.PbLanguage;
import com.intellij.protobuf.lang.psi.ProtoTokenTypes;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.ast.IElementType;
import consulo.language.ast.TokenType;
import consulo.language.codeStyle.lineIndent.JavaLikeLangLineIndentProvider;
import consulo.language.codeStyle.lineIndent.SemanticEditorPosition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** A simple LineIndentProvider for protobuf files. */
@ExtensionImpl
public class PbLineIndentProvider extends JavaLikeLangLineIndentProvider {
  private static final ImmutableMap<IElementType, SemanticEditorPosition.SyntaxElement> SYNTAX_MAP;

  static {
    SYNTAX_MAP =
        ImmutableMap.<IElementType, SemanticEditorPosition.SyntaxElement>builder()
            .put(TokenType.WHITE_SPACE, JavaLikeElement.Whitespace)
            .put(ProtoTokenTypes.LBRACE, JavaLikeElement.BlockOpeningBrace)
            .put(ProtoTokenTypes.RBRACE, JavaLikeElement.BlockClosingBrace)
            .put(ProtoTokenTypes.LBRACK, JavaLikeElement.BlockOpeningBrace)
            .put(ProtoTokenTypes.RBRACK, JavaLikeElement.BlockClosingBrace)
            .put(ProtoTokenTypes.SEMI, JavaLikeElement.Semicolon)
            .put(ProtoTokenTypes.COMMA, JavaLikeElement.Comma)
            .put(ProtoTokenTypes.BLOCK_COMMENT, JavaLikeElement.BlockComment)
            .put(ProtoTokenTypes.LINE_COMMENT, JavaLikeElement.LineComment)
            .build();
  }

  @Nullable
  @Override
  protected SemanticEditorPosition.SyntaxElement mapType(@NotNull IElementType tokenType) {
    return SYNTAX_MAP.get(tokenType);
  }

  @Override
  public boolean isSuitableForLanguage(@NotNull Language language) {
    return language.isKindOf(PbLanguage.INSTANCE);
  }
}
