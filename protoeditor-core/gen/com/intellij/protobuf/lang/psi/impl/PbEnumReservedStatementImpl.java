// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import java.util.List;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElementVisitor;
import org.jetbrains.annotations.*;
import consulo.language.psi.util.PsiTreeUtil;
import com.intellij.protobuf.lang.psi.*;

public class PbEnumReservedStatementImpl extends PbStatementBase implements PbEnumReservedStatement {

  public PbEnumReservedStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitEnumReservedStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PbEnumReservedRange> getEnumReservedRangeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PbEnumReservedRange.class);
  }

  @Override
  @NotNull
  public List<PbStringValue> getStringValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PbStringValue.class);
  }

}
