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
package com.intellij.protobuf.ide.highlighter;

import com.intellij.protobuf.ide.util.ResourceUtil;
import consulo.annotation.component.ExtensionImpl;
import consulo.colorScheme.setting.AttributesDescriptor;
import consulo.language.editor.colorScheme.setting.ColorSettingsPage;
import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.localize.LocalizeValue;
import google.protobuf.localize.ProtobufIdeLocalize;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * A {@link ColorSettingsPage} for standalone prototext files.
 */
@ExtensionImpl
public class PbTextColorSettingsPage implements ColorSettingsPage {

  private static final AttributesDescriptor[] DESCRIPTORS =
    new AttributesDescriptor[]{
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeIdentifier(), PbTextSyntaxHighlighter.IDENTIFIER),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeKeyword(), PbTextSyntaxHighlighter.KEYWORD),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeNumber(), PbTextSyntaxHighlighter.NUMBER),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeString(), PbTextSyntaxHighlighter.STRING),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeEnumValue(), PbTextSyntaxHighlighter.ENUM_VALUE),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeLineComment(), PbTextSyntaxHighlighter.LINE_COMMENT),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeOperator(), PbTextSyntaxHighlighter.OPERATION_SIGN),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeBraces(), PbTextSyntaxHighlighter.BRACES),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeBrackets(), PbTextSyntaxHighlighter.BRACKETS),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeDot(), PbTextSyntaxHighlighter.DOT),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeSemicolon(), PbTextSyntaxHighlighter.SEMICOLON),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeComma(), PbTextSyntaxHighlighter.COMMA),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeValidEscapeSequence(), PbTextSyntaxHighlighter.VALID_STRING_ESCAPE),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeInvalidEscapeSequence(),
                               PbTextSyntaxHighlighter.INVALID_STRING_ESCAPE),
      new AttributesDescriptor(ProtobufIdeLocalize.prototextTypeCommentDirective(), PbTextSyntaxHighlighter.COMMENT_DIRECTIVE),
    };

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new PbTextSyntaxHighlighter();
  }

  @NotNull
  @Override
  public String getDemoText() {
    try {
      return ResourceUtil.readUrlAsString(getClass().getResource("/example.pb"));
    }
    catch (IOException e) {
      return "Error loading example.";
    }
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return DESCRIPTORS;
  }

  @NotNull
  @Override
  public LocalizeValue getDisplayName() {
    return ProtobufIdeLocalize.prototextName();
  }
}
