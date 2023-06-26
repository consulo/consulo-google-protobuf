// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElementVisitor;

import com.intellij.protobuf.lang.psi.*;

public class PbImportNameImpl extends PbImportNameMixin implements PbImportName {

  public PbImportNameImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitImportName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PbStringValue getStringValue() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, PbStringValue.class));
  }

}
