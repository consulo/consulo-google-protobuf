// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElementVisitor;
import com.intellij.protobuf.lang.psi.*;
import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbTextFieldNameImpl extends PbTextFieldNameMixin implements PbTextFieldName {

  public PbTextFieldNameImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbTextVisitor visitor) {
    visitor.visitFieldName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbTextVisitor) accept((PbTextVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PbTextExtensionName getExtensionName() {
    return findChildByClass(PbTextExtensionName.class);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return findChildByType(IDENTIFIER_LITERAL);
  }

}
