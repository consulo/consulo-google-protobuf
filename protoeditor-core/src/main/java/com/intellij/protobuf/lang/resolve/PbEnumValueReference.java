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

import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiReferenceBase;
import consulo.language.impl.ast.LeafElement;
import consulo.language.util.IncorrectOperationException;
import com.intellij.protobuf.lang.psi.PbEnumDefinition;
import com.intellij.protobuf.lang.psi.PbEnumValue;
import com.intellij.protobuf.lang.psi.ProtoIdentifierValue;
import consulo.document.util.TextRange;
import consulo.language.ast.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Provides a reference to a {@link PbEnumValue}. */
public class PbEnumValueReference extends PsiReferenceBase<ProtoIdentifierValue> {

  @NotNull private final PbEnumDefinition enumDefinition;

  public PbEnumValueReference(
      @NotNull ProtoIdentifierValue element, @NotNull PbEnumDefinition enumDefinition) {
    super(element);
    this.enumDefinition = enumDefinition;
  }

  @Nullable
  @Override
  protected TextRange calculateDefaultRangeInElement() {
    return TextRange.create(0, myElement.getTextLength());
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    String valueName = myElement.getText();
    return enumDefinition.getEnumValueMap().get(valueName).stream().findFirst().orElse(null);
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    return enumDefinition
        .getEnumValues()
        .stream()
        .map(PbSymbolLookupElement::new)
        .distinct()
        .toArray();
  }

  @Override
  public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
    PsiElement element = getElement().getIdentifierLiteral();
    if (element != null) {
      ASTNode node = element.getNode();
      if (node instanceof LeafElement) {
        ((LeafElement) node).replaceWithText(newElementName);
        return element;
      }
    }
    return super.handleElementRename(newElementName);
  }
}
