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

@ExtensionImpl
public class PbColorSettingsPage implements ColorSettingsPage {

  private static final AttributesDescriptor[] DESCRIPTORS =
    new AttributesDescriptor[]{
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorIdentifier(), PbSyntaxHighlighter.IDENTIFIER),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorNumber(), PbSyntaxHighlighter.NUMBER),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorKeyboard(), PbSyntaxHighlighter.KEYWORD),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorString(), PbSyntaxHighlighter.STRING),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorEnum(), PbSyntaxHighlighter.ENUM_VALUE),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorBlockComment(), PbSyntaxHighlighter.BLOCK_COMMENT),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorLineComment(), PbSyntaxHighlighter.LINE_COMMENT),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorOperator(), PbSyntaxHighlighter.OPERATION_SIGN),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorBraces(), PbSyntaxHighlighter.BRACES),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorBrackets(), PbSyntaxHighlighter.BRACKETS),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorParentheses(), PbSyntaxHighlighter.PARENTHESES),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorDot(), PbSyntaxHighlighter.DOT),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorSemicolon(), PbSyntaxHighlighter.SEMICOLON),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorComma(), PbSyntaxHighlighter.COMMA),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorValidEscapeSequence(),
                               PbSyntaxHighlighter.VALID_STRING_ESCAPE),
      new AttributesDescriptor(ProtobufIdeLocalize.attributeDescriptorInvalidEscapeSequence(),
                               PbSyntaxHighlighter.INVALID_STRING_ESCAPE),
    };

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new PbSyntaxHighlighter(true);
  }

  @NotNull
  @Override
  public String getDemoText() {
    try {
      return ResourceUtil.readUrlAsString(getClass().getResource("/example.proto"));
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
    return ProtobufIdeLocalize.pluginName();
  }
}
