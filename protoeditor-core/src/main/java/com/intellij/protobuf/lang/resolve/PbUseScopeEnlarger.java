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
package com.intellij.protobuf.lang.resolve;

import com.intellij.protobuf.lang.psi.PbElement;
import consulo.annotation.component.ExtensionImpl;
import consulo.content.scope.SearchScope;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.UseScopeEnlarger;
import consulo.language.psi.scope.GlobalSearchScope;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Enlarges the use scope of protobuf elements to contain files outside project roots but resolvable
 * by configured {@link FileResolveProvider} instances.
 *
 * <p>Without this, placing the caret over a target that exists in a file outside of the project
 * (such as descriptor.proto) does not highlight other usages of the target.
 */
@ExtensionImpl
public class PbUseScopeEnlarger implements UseScopeEnlarger {

  @Nullable
  @Override
  public SearchScope getAdditionalUseScope(@NotNull PsiElement element) {
    if (!(element instanceof PbElement) || !element.isValid()) {
      return null;
    }
    PsiFile psiFile = element.getContainingFile();
    if (psiFile == null) {
      return null;
    }
    VirtualFile file = psiFile.getVirtualFile();
    if (file == null) {
      return null;
    }
    Project project = element.getProject();
    GlobalSearchScope scope = PbFileResolver.getUnionScope(project);
    return scope.contains(file) ? scope : null;
  }
}
