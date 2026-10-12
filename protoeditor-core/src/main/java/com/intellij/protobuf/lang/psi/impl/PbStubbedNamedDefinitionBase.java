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

import com.intellij.protobuf.lang.psi.PbNamedElement;
import com.intellij.protobuf.lang.psi.PbSymbolOwner;
import com.intellij.protobuf.lang.psi.util.PbPsiImplUtil;
import com.intellij.protobuf.lang.stub.PbNamedElementStub;
import consulo.annotation.access.RequiredReadAction;
import consulo.language.ast.ASTNode;
import consulo.language.icon.IconDescriptorUpdaters;
import consulo.language.impl.ast.LeafElement;
import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.util.QualifiedName;
import consulo.language.util.IncorrectOperationException;
import consulo.localize.LocalizeValue;
import consulo.navigation.ItemPresentation;
import consulo.ui.image.Image;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

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
    @RequiredReadAction
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
    @RequiredReadAction
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        PsiElement identifier = getNameIdentifier();
        if (identifier == null) {
            throw new IncorrectOperationException();
        }
        if (identifier.getNode() instanceof LeafElement leafElem) {
            leafElem.replaceWithText(name);
            return this;
        }
        throw new IncorrectOperationException();
    }

    @Override
    @RequiredReadAction
    public int getTextOffset() {
        PsiElement name = getNameIdentifier();
        return name != null ? name.getTextOffset() : super.getTextOffset();
    }

    @NotNull
    @Override
    @RequiredReadAction
    public LocalizeValue getPresentableText() {
        String name = getName();
        return name != null ? LocalizeValue.of(name) : LocalizeValue.of(getText());
    }

    @Override
    @Nullable
    public ItemPresentation getPresentation() {
        PbNamedElement element = this;
        return new ItemPresentation() {
            @Override
            @RequiredReadAction
            public LocalizeValue getPresentableText() {
                return element.getPresentableText();
            }

            @Override
            public @Nullable String getLocationString() {
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
            @RequiredReadAction
            public @Nullable Image getIcon() {
                return IconDescriptorUpdaters.getIcon(element, 0);
            }
        };
    }
}
