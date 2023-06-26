// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.*;

import com.intellij.protobuf.lang.psi.*;

public class PbImportStatementImpl extends PbImportStatementMixin implements PbImportStatement {

  public PbImportStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitImportStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PbImportName getImportName() {
    return PsiTreeUtil.getChildOfType(this, PbImportName.class);
  }

}
