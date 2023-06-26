// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiElementVisitor;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;
import consulo.language.psi.util.PsiTreeUtil;
import com.intellij.protobuf.lang.psi.*;

import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbEnumValueImpl extends PbEnumValueMixin implements PbEnumValue {

  public PbEnumValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitEnumValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PbNumberValue getNumberValue() {
    return PsiTreeUtil.getChildOfType(this, PbNumberValue.class);
  }

  @Override
  @Nullable
  public PbOptionList getOptionList() {
    return PsiTreeUtil.getChildOfType(this, PbOptionList.class);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return findChildByType(IDENTIFIER_LITERAL);
  }

}
