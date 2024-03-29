// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import java.util.List;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElement;

import com.intellij.protobuf.lang.psi.*;

import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbServiceStreamImpl extends PbServiceStreamMixin implements PbServiceStream {

  public PbServiceStreamImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitServiceStream(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PbMessageTypeName> getMessageTypeNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PbMessageTypeName.class);
  }

  @Override
  @Nullable
  public PbMethodOptions getMethodOptions() {
    return PsiTreeUtil.getChildOfType(this, PbMethodOptions.class);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return findChildByType(IDENTIFIER_LITERAL);
  }

}
