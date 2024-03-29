// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.psi.PsiElementVisitor;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;
import consulo.language.psi.util.PsiTreeUtil;
import com.intellij.protobuf.lang.psi.*;

public class PbOptionExpressionImpl extends PbElementBase implements PbOptionExpression {

  public PbOptionExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitOptionExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PbAggregateValue getAggregateValue() {
    return PsiTreeUtil.getChildOfType(this, PbAggregateValue.class);
  }

  @Override
  @Nullable
  public PbIdentifierValue getIdentifierValue() {
    return PsiTreeUtil.getChildOfType(this, PbIdentifierValue.class);
  }

  @Override
  @Nullable
  public PbNumberValue getNumberValue() {
    return PsiTreeUtil.getChildOfType(this, PbNumberValue.class);
  }

  @Override
  @NotNull
  public PbOptionName getOptionName() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, PbOptionName.class));
  }

  @Override
  @Nullable
  public PbStringValue getStringValue() {
    return PsiTreeUtil.getChildOfType(this, PbStringValue.class);
  }

}
