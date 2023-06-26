// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElementVisitor;
import org.jetbrains.annotations.*;
import com.intellij.protobuf.lang.psi.*;

public class PbMessageTypeNameImpl extends PbTypeNameImpl implements PbMessageTypeName {

  public PbMessageTypeNameImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitMessageTypeName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

}
