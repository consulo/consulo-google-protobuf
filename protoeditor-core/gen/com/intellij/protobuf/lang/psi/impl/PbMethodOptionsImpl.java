// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import java.util.List;

import consulo.language.ast.ASTNode;
import consulo.language.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiElementVisitor;

import com.intellij.protobuf.lang.psi.*;

import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbMethodOptionsImpl extends PbElementBase implements PbMethodOptions {

  public PbMethodOptionsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitMethodOptions(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PbOptionStatement> getOptionStatements() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PbOptionStatement.class);
  }

  @Override
  @NotNull
  public PsiElement getStart() {
    return notNullChild(findChildByType(LBRACE));
  }

  @Override
  @Nullable
  public PsiElement getEnd() {
    return findChildByType(RBRACE);
  }

}
