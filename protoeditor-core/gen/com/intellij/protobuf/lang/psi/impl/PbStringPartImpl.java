// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiElementVisitor;
import org.jetbrains.annotations.*;
import com.intellij.protobuf.lang.psi.*;

import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbStringPartImpl extends PbElementBase implements PbStringPart {

  public PbStringPartImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitStringPart(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getStringLiteral() {
    return notNullChild(findChildByType(STRING_LITERAL));
  }

}
