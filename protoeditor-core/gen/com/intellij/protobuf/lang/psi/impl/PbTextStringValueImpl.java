// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import java.util.List;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.*;

import com.intellij.protobuf.lang.psi.*;

public class PbTextStringValueImpl extends PbTextElementBase implements PbTextStringValue {

  public PbTextStringValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbTextVisitor visitor) {
    visitor.visitStringValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbTextVisitor) accept((PbTextVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PbTextStringPart> getStringParts() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PbTextStringPart.class);
  }

}
