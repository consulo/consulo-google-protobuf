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
import com.intellij.protobuf.lang.psi.PbNamedElement;
import com.intellij.protobuf.lang.psi.PbSymbolOwner;
import com.intellij.protobuf.lang.psi.util.PbPsiImplUtil;
import com.intellij.protobuf.lang.stub.PbNamedElementStub;
import consulo.language.impl.ast.LeafElement;
import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.util.QualifiedName;
import consulo.language.util.IncorrectOperationException;
import consulo.language.icon.IconDescriptorUpdaters;
import consulo.navigation.ItemPresentation;
import consulo.ui.image.Image;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract class PbStubbedNamedDefinitionBase<T extends PbNamedElementStub<?>>
    extends PbStubbedDefinitionBase<T> implements PbNamedElement {

  PbStubbedNamedDefinitionBase(ASTNode node) {
    super(node);
  }

  PbStubbedNamedDefinitionBase(T stub, IStubElementType nodeType) {
    super(stub, nodeType);
  }

  @Nullable
  @Override
  public QualifiedName getQualifiedName() {
    return PbPsiImplUtil.getQualifiedName(this);
  }

  @Nullable
  @Override
  public PbSymbolOwner getSymbolOwner() {
    return PbPsiImplUtil.getSymbolOwner(this);
  }

  @Nullable
  @Override
  public String getName() {
    PbNamedElementStub<?> stub = getStub();
    if (stub != null) {
      return stub.getName();
    }
    PsiElement id = getNameIdentifier();
    if (id != null) {
      return id.getText();
    }
    return null;
  }

  @Override
  public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException
  {
    PsiElement identifier = getNameIdentifier();
    if (identifier == null) {
      throw new IncorrectOperationException();
    }
    ASTNode node = identifier.getNode();
    if (node instanceof LeafElement) {
      ((LeafElement) node).replaceWithText(name);
      return this;
    }
    throw new IncorrectOperationException();
  }

  @Override
  public int getTextOffset() {
    PsiElement name = getNameIdentifier();
    return name != null ? name.getTextOffset() : super.getTextOffset();
  }

  @NotNull
  @Override
  public String getPresentableText() {
    String name = getName();
    return name != null ? name : getText();
  }

  @Override
  @Nullable
  public ItemPresentation getPresentation() {
    PbNamedElement element = this;
    return new ItemPresentation() {

      @Override
      public String getPresentableText() {
        return element.getPresentableText();
      }

      @Nullable
      @Override
      public String getLocationString() {
        PbSymbolOwner owner = getSymbolOwner();
        if (owner != null) {
          QualifiedName qualifiedName = owner.getChildScope();
          if (qualifiedName != null) {
            return qualifiedName.toString();
          }
        }
        return null;
      }

      @Nullable
      @Override
      public Image getIcon() {
        return IconDescriptorUpdaters.getIcon(element, 0);
      }
    };
  }
}
