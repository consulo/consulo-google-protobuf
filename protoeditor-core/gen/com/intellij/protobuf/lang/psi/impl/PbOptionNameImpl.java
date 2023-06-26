// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElementVisitor;
import com.intellij.protobuf.lang.psi.*;

public class PbOptionNameImpl extends PbOptionNameMixin implements PbOptionName {

  public PbOptionNameImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitOptionName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

}
