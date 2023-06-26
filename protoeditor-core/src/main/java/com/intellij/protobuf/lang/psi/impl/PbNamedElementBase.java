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
import consulo.language.icon.IconDescriptorUpdaters;
import consulo.language.psi.util.QualifiedName;
import consulo.navigation.ItemPresentation;
import com.intellij.protobuf.lang.psi.PbNamedElement;
import com.intellij.protobuf.lang.psi.PbSymbolOwner;
import com.intellij.protobuf.lang.psi.util.PbPsiImplUtil;
import consulo.language.psi.PsiElement;
import consulo.language.impl.ast.LeafElement;
import consulo.language.util.IncorrectOperationException;
import consulo.ui.image.Image;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract class PbNamedElementBase extends PbStatementBase implements PbNamedElement {

  PbNamedElementBase(ASTNode node) {
    super(node);
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
    PsiElement name = getNameIdentifier();
    if (name != null) {
      return name.getText();
    }
    return super.getName();
  }

  @Override
  public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
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

      @Override
      public Image getIcon() {
        return IconDescriptorUpdaters.getIcon(element, 0);
      }
    };
  }
}
