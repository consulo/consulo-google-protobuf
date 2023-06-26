// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;

import com.intellij.protobuf.lang.psi.*;

public class PbTypeNameImpl extends PbTypeNameMixin implements PbTypeName {

  public PbTypeNameImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitTypeName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PbSymbolPath getSymbolPath() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, PbSymbolPath.class));
  }

}
