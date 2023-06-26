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
package com.intellij.protobuf.lang.psi.impl;

import com.intellij.protobuf.lang.PbTextFileType;
import com.intellij.protobuf.lang.psi.PbTextFile;
import com.intellij.protobuf.lang.psi.PbTextRootMessage;
import com.intellij.protobuf.lang.resolve.SchemaInfo;
import com.intellij.protobuf.lang.resolve.SchemaProvider;
import consulo.application.util.CachedValueProvider;
import consulo.language.Language;
import consulo.language.file.FileViewProvider;
import consulo.language.impl.psi.PsiFileBase;
import consulo.language.psi.PsiModificationTracker;
import consulo.language.psi.util.LanguageCachedValueUtil;
import consulo.virtualFileSystem.fileType.FileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation class for prototext file elements.
 */
public class PbTextFileImpl extends PsiFileBase implements PbTextFile {

  public PbTextFileImpl(@NotNull FileViewProvider viewProvider, @NotNull Language language) {
    super(viewProvider, language);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return PbTextFileType.INSTANCE;
  }

  @Override
  public PbTextRootMessage getRootMessage() {
    return this;
  }

  @Override
  @Nullable
  public SchemaInfo getSchemaInfo() {
    return LanguageCachedValueUtil.getCachedValue(
      this,
      () -> CachedValueProvider.Result.create(SchemaProvider.forFile(this), PsiModificationTracker.MODIFICATION_COUNT));
  }

  @Override
  public String toString() {
    return "Protocol Buffer Text File";
  }
}
