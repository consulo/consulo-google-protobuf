// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import java.util.List;

import consulo.language.psi.PsiElement;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.util.PsiTreeUtil;
import com.intellij.protobuf.lang.psi.*;

import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbMapFieldImpl extends PbMapFieldMixin implements PbMapField {

  public PbMapFieldImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitMapField(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PbOptionList getOptionList() {
    return PsiTreeUtil.getChildOfType(this, PbOptionList.class);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return findChildByType(IDENTIFIER_LITERAL);
  }

  @Override
  @Nullable
  public PbNumberValue getFieldNumber() {
    return PsiTreeUtil.getChildOfType(this, PbNumberValue.class);
  }

  @Override
  @Nullable
  public PbTypeName getKeyType() {
    List<PbTypeName> p1 = PsiTreeUtil.getChildrenOfTypeAsList(this, PbTypeName.class);
    return p1.size() < 1 ? null : p1.get(0);
  }

  @Override
  @Nullable
  public PbTypeName getValueType() {
    List<PbTypeName> p1 = PsiTreeUtil.getChildrenOfTypeAsList(this, PbTypeName.class);
    return p1.size() < 2 ? null : p1.get(1);
  }

  @Override
  @Nullable
  public PbFieldLabel getDeclaredLabel() {
    return PsiTreeUtil.getChildOfType(this, PbFieldLabel.class);
  }

}
