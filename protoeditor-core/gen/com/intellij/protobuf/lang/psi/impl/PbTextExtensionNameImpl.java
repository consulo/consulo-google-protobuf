// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElementVisitor;
import com.intellij.protobuf.lang.psi.*;

public class PbTextExtensionNameImpl extends PbTextExtensionNameMixin implements PbTextExtensionName {

  public PbTextExtensionNameImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbTextVisitor visitor) {
    visitor.visitExtensionName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbTextVisitor) accept((PbTextVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PbTextDomain getDomain() {
    return findChildByClass(PbTextDomain.class);
  }

  @Override
  @Nullable
  public PbTextSymbolPath getSymbolPath() {
    return findChildByClass(PbTextSymbolPath.class);
  }

}
