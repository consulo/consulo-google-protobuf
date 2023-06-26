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

import consulo.language.ast.ASTNode;
import com.intellij.protobuf.ide.util.PbIcons;
import com.intellij.protobuf.lang.psi.PbServiceDefinition;
import com.intellij.protobuf.lang.stub.PbServiceDefinitionStub;
import consulo.language.psi.stub.IStubElementType;
import consulo.ui.image.Image;
import org.jetbrains.annotations.Nullable;

abstract class PbServiceDefinitionMixin extends PbStubbedSymbolOwnerBase<PbServiceDefinitionStub>
    implements PbServiceDefinition {

  PbServiceDefinitionMixin(ASTNode node) {
    super(node);
  }

  PbServiceDefinitionMixin(PbServiceDefinitionStub stub, IStubElementType nodeType) {
    super(stub, nodeType);
  }

  @Nullable
  //@Override
  public Image getIcon(int flags) {
    return PbIcons.SERVICE;
  }
}
