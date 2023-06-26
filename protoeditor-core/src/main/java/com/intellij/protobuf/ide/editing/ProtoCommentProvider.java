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

import com.intellij.protobuf.lang.psi.PbFile;
import com.intellij.protobuf.lang.psi.PbTextFile;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.language.Commenter;
import consulo.language.Language;
import consulo.language.editor.MultipleLangCommentProvider;
import consulo.language.file.FileViewProvider;
import consulo.language.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

/**
 * Provides the correct commenter for protobuf and prototext files. Specifically, prototext elements
 * should be commented with {@link PbCommenter} when contained within a {@link PbFile}.
 */
@ExtensionImpl
public class ProtoCommentProvider implements MultipleLangCommentProvider {

  @Nullable
  @Override
  public Commenter getLineCommenter(
      PsiFile file, Editor editor, Language lineStartLanguage, Language lineEndLanguage) {
    if (file instanceof PbFile) {
      return PbCommenter.INSTANCE;
    }
    if (file instanceof PbTextFile) {
      return PbTextCommenter.INSTANCE;
    }
    return null;
  }

  @Override
  public boolean canProcess(PsiFile file, FileViewProvider viewProvider) {
    return file instanceof PbFile || file instanceof PbTextFile;
  }
}
