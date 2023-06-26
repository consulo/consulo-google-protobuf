// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElementVisitor;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElement;
import com.intellij.protobuf.lang.psi.*;
import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbTextStringPartImpl extends PbTextElementBase implements PbTextStringPart {

  public PbTextStringPartImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbTextVisitor visitor) {
    visitor.visitStringPart(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbTextVisitor) accept((PbTextVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getStringLiteral() {
    return findNotNullChildByType(STRING_LITERAL);
  }

}
