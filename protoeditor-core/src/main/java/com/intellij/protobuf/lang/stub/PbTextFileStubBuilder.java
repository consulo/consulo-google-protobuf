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
package com.intellij.protobuf.lang.stub;

import com.intellij.protobuf.lang.PbTextFileType;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.psi.stub.BinaryFileStubBuilder;
import consulo.language.psi.stub.FileContent;
import consulo.language.psi.stub.Stub;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileType.FileType;
import jakarta.annotation.Nonnull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Empty stub builder to suppress errors when IntelliJ is looking for stubs.
 */
@ExtensionImpl
public class PbTextFileStubBuilder implements BinaryFileStubBuilder {
  private static final int STUB_VERSION = 0;

  @Nonnull
  @Override
  public FileType getFileType() {
    return PbTextFileType.INSTANCE;
  }

  @Override
  public boolean acceptsFile(@NotNull VirtualFile file) {
    return false;
  }

  @Nullable
  @Override
  public Stub buildStubTree(@NotNull FileContent fileContent) {
    return null;
  }

  @Override
  public int getStubVersion() {
    return STUB_VERSION;
  }
}
