// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiElementVisitor;

import com.intellij.protobuf.lang.psi.*;
import com.intellij.protobuf.lang.stub.PbServiceDefinitionStub;
import consulo.language.psi.stub.IStubElementType;
import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbServiceDefinitionImpl extends PbServiceDefinitionMixin implements PbServiceDefinition {

  public PbServiceDefinitionImpl(ASTNode node) {
    super(node);
  }

  public PbServiceDefinitionImpl(PbServiceDefinitionStub stub, IStubElementType type) {
    super(stub, type);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitServiceDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return findChildByType(IDENTIFIER_LITERAL);
  }

  @Override
  @Nullable
  public PbServiceBody getBody() {
    return PsiTreeUtil.getChildOfType(this, PbServiceBody.class);
  }

}
