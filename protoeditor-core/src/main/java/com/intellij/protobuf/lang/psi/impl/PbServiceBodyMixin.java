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

import consulo.language.psi.util.QualifiedName;
import com.intellij.protobuf.lang.descriptor.Descriptor;
import com.intellij.protobuf.lang.descriptor.DescriptorOptionType;
import com.intellij.protobuf.lang.psi.PbServiceBody;
import com.intellij.protobuf.lang.psi.PbServiceDefinition;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract class PbServiceBodyMixin extends PbElementBase implements PbServiceBody {

  PbServiceBodyMixin(ASTNode node) {
    super(node);
  }

  @NotNull
  @Override
  public QualifiedName getDescriptorOptionsTypeName(Descriptor descriptor) {
    return DescriptorOptionType.SERVICE_OPTIONS.forDescriptor(descriptor);
  }

  @Override
  @Nullable
  public QualifiedName getExtensionOptionScope() {
    PsiElement parent = getParent();
    if (!(parent instanceof PbServiceDefinition)) {
      return null;
    }
    PbServiceDefinition service = (PbServiceDefinition) parent;
    QualifiedName name = service.getQualifiedName();
    return name != null ? name.removeLastComponent() : null;
  }
}
