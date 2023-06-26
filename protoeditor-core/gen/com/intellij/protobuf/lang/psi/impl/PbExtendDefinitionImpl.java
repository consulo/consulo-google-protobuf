// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.stub.IStubElementType;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;
import consulo.language.psi.util.PsiTreeUtil;
import com.intellij.protobuf.lang.psi.*;
import com.intellij.protobuf.lang.stub.PbExtendDefinitionStub;

public class PbExtendDefinitionImpl extends PbExtendDefinitionMixin implements PbExtendDefinition {

  public PbExtendDefinitionImpl(ASTNode node) {
    super(node);
  }

  public PbExtendDefinitionImpl(PbExtendDefinitionStub stub, IStubElementType type) {
    super(stub, type);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitExtendDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PbExtendBody getBody() {
    return PsiTreeUtil.getChildOfType(this, PbExtendBody.class);
  }

  @Override
  @Nullable
  public PbMessageTypeName getTypeName() {
    return PsiTreeUtil.getChildOfType(this, PbMessageTypeName.class);
  }

}
