// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElementVisitor;
import com.intellij.protobuf.lang.psi.*;

public class PbTextSymbolPathImpl extends PbTextSymbolPathMixin implements PbTextSymbolPath {

  public PbTextSymbolPathImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbTextVisitor visitor) {
    visitor.visitSymbolPath(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbTextVisitor) accept((PbTextVisitor)visitor);
    else super.accept(visitor);
  }

}
