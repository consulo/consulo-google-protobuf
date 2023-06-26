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
package com.intellij.protobuf.lang;

import com.intellij.protobuf.lang.lexer.ProtoLexer;
import com.intellij.protobuf.lang.parser.PbParser;
import com.intellij.protobuf.lang.psi.PbTextElementType;
import com.intellij.protobuf.lang.psi.PbTextTypes;
import com.intellij.protobuf.lang.psi.PbTypes;
import com.intellij.protobuf.lang.psi.ProtoTokenTypes;
import com.intellij.protobuf.lang.psi.impl.PbFileImpl;
import com.intellij.protobuf.lang.stub.type.PbFileElementType;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IFileElementType;
import consulo.language.ast.TokenSet;
import consulo.language.ast.TokenType;
import consulo.language.file.FileViewProvider;
import consulo.language.lexer.Lexer;
import consulo.language.parser.ParserDefinition;
import consulo.language.parser.PsiParser;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.version.LanguageVersion;
import jakarta.annotation.Nonnull;
import org.jetbrains.annotations.NotNull;

@ExtensionImpl
public class PbParserDefinition implements ParserDefinition {
  private static final TokenSet WHITE_SPACE = TokenSet.create(TokenType.WHITE_SPACE);
  private static final TokenSet COMMENTS =
    TokenSet.create(ProtoTokenTypes.LINE_COMMENT, ProtoTokenTypes.BLOCK_COMMENT);
  private static final TokenSet STRINGS = TokenSet.create(ProtoTokenTypes.STRING_LITERAL);

  private static final IFileElementType FILE = new PbFileElementType(PbLanguage.INSTANCE);

  public PbParserDefinition() {
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return PbLanguage.INSTANCE;
  }

  @NotNull
  @Override
  public Lexer createLexer(LanguageVersion version) {
    return ProtoLexer.forProtobufWithKeywords();
  }

  @Override
  public PsiParser createParser(LanguageVersion version) {
    return new PbParser();
  }

  @NotNull
  @Override
  public TokenSet getWhitespaceTokens(LanguageVersion version) {
    return WHITE_SPACE;
  }

  @NotNull
  @Override
  public TokenSet getCommentTokens(LanguageVersion version) {
    return COMMENTS;
  }

  @NotNull
  @Override
  public TokenSet getStringLiteralElements(LanguageVersion version) {
    return STRINGS;
  }

  @Override
  public IFileElementType getFileNodeType() {
    return FILE;
  }

  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new PbFileImpl(viewProvider);
  }

  @Override
  public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }

  @NotNull
  @Override
  public PsiElement createElement(ASTNode node) {
    if (node instanceof PbTextElementType) {
      return PbTextTypes.Factory.createElement(node);
    }
    return PbTypes.Factory.createElement(node);
  }
}
