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
package com.intellij.protobuf.lang.gotobyname;

import com.intellij.protobuf.lang.psi.PbNamedElement;
import com.intellij.protobuf.lang.stub.index.QualifiedNameIndex;
import consulo.annotation.component.ExtensionImpl;
import consulo.application.util.function.Processor;
import consulo.content.scope.SearchScope;
import consulo.ide.navigation.GotoSymbolContributor;
import consulo.language.psi.search.FindSymbolParameters;
import consulo.language.psi.stub.IdFilter;
import consulo.language.psi.stub.StubIndex;
import consulo.navigation.NavigationItem;
import consulo.project.content.scope.ProjectAwareSearchScope;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@ExtensionImpl
public class PbGotoSymbolContributor implements GotoSymbolContributor {

  @Override
  public void processNames(@Nonnull Processor<String> processor, @Nonnull SearchScope searchScope, @Nullable IdFilter idFilter) {
    StubIndex.getInstance().processAllKeys(QualifiedNameIndex.KEY, processor, (ProjectAwareSearchScope)searchScope, idFilter);
  }

  @Override
  public void processElementsWithName(@Nonnull String key,
                                      @Nonnull Processor<NavigationItem> processor,
                                      @Nonnull FindSymbolParameters findSymbolParameters) {
    StubIndex.getInstance()
             .processElements(QualifiedNameIndex.KEY,
                              key,
                              findSymbolParameters.getProject(),
                              findSymbolParameters.getSearchScope(),
                              PbNamedElement.class,
                              processor);
  }
}
