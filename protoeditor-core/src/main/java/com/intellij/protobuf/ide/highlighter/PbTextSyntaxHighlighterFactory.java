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

import com.intellij.protobuf.lang.PbTextLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.highlight.SyntaxHighlighterFactory;
import consulo.virtualFileSystem.VirtualFile;
import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.project.Project;
import jakarta.annotation.Nonnull;
import org.jetbrains.annotations.NotNull;

/** Creates a {@link SyntaxHighlighter} for use with standalone prototext files. */
@ExtensionImpl
public class PbTextSyntaxHighlighterFactory extends SyntaxHighlighterFactory {

  @NotNull
  @Override
  public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
    return new PbTextSyntaxHighlighter();
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return PbTextLanguage.INSTANCE;
  }
}
