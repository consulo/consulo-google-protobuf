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

import com.intellij.protobuf.ide.util.PbIcons;
import consulo.language.file.LanguageFileType;
import consulo.localize.LocalizeValue;
import consulo.ui.image.Image;
import google.protobuf.localize.ProtobufIdeLocalize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link LanguageFileType} for prototext files.
 */
public class PbTextFileType extends LanguageFileType {

  public static final PbTextFileType INSTANCE = new PbTextFileType();

  private PbTextFileType() {
    super(PbTextLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getId() {
    // Warning: this is conflated with Language#myID in several places...
    // They should be identical.
    return PbTextLanguage.INSTANCE.getID();
  }

  @NotNull
  @Override
  public LocalizeValue getDescription() {
    return ProtobufIdeLocalize.prototextNameSentence();
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "textproto";
  }

  @Nullable
  @Override
  public Image getIcon() {
    return PbIcons.TEXT_FILE;
  }

  public boolean equals(Object other) {
    return other != null && getClass().equals(other.getClass());
  }

  public int hashCode() {
    return getClass().hashCode();
  }
}
