// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.psi.PsiElementVisitor;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;
import com.intellij.protobuf.lang.psi.*;

public class PbTextFieldImpl extends PbTextFieldMixin implements PbTextField {

  public PbTextFieldImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbTextVisitor visitor) {
    visitor.visitField(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbTextVisitor) accept((PbTextVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PbTextFieldName getFieldName() {
    return findNotNullChildByClass(PbTextFieldName.class);
  }

  @Override
  @Nullable
  public PbTextIdentifierValue getIdentifierValue() {
    return findChildByClass(PbTextIdentifierValue.class);
  }

  @Override
  @Nullable
  public PbTextMessageValue getMessageValue() {
    return findChildByClass(PbTextMessageValue.class);
  }

  @Override
  @Nullable
  public PbTextNumberValue getNumberValue() {
    return findChildByClass(PbTextNumberValue.class);
  }

  @Override
  @Nullable
  public PbTextStringValue getStringValue() {
    return findChildByClass(PbTextStringValue.class);
  }

  @Override
  @Nullable
  public PbTextValueList getValueList() {
    return findChildByClass(PbTextValueList.class);
  }

}
