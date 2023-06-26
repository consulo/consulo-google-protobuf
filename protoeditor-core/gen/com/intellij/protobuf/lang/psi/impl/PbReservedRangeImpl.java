// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import java.util.List;

import consulo.language.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElementVisitor;

import com.intellij.protobuf.lang.psi.*;

public class PbReservedRangeImpl extends PbReservedRangeMixin implements PbReservedRange {

  public PbReservedRangeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitReservedRange(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PbNumberValue> getNumberValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PbNumberValue.class);
  }

  @Override
  @NotNull
  public PbNumberValue getFromValue() {
    List<PbNumberValue> p1 = getNumberValueList();
    return p1.get(0);
  }

  @Override
  @Nullable
  public PbNumberValue getToValue() {
    List<PbNumberValue> p1 = getNumberValueList();
    return p1.size() < 2 ? null : p1.get(1);
  }

}
