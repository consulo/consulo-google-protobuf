// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import java.util.List;

import consulo.language.ast.ASTNode;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.util.PsiTreeUtil;
import com.intellij.protobuf.lang.psi.*;

public class PbExtensionsStatementImpl extends PbExtensionsStatementMixin implements PbExtensionsStatement {

  public PbExtensionsStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitExtensionsStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PbExtensionRange> getExtensionRangeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PbExtensionRange.class);
  }

  @Override
  @Nullable
  public PbOptionList getOptionList() {
    return PsiTreeUtil.getChildOfType(this, PbOptionList.class);
  }

}
