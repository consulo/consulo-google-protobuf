// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.IStubElementType;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.util.PsiTreeUtil;
import com.intellij.protobuf.lang.psi.*;
import com.intellij.protobuf.lang.stub.PbGroupDefinitionStub;

import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbGroupDefinitionImpl extends PbGroupDefinitionMixin implements PbGroupDefinition {

  public PbGroupDefinitionImpl(ASTNode node) {
    super(node);
  }

  public PbGroupDefinitionImpl(PbGroupDefinitionStub stub, IStubElementType type) {
    super(stub, type);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitGroupDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PbGroupOptionContainer getGroupOptionContainer() {
    return PsiTreeUtil.getChildOfType(this, PbGroupOptionContainer.class);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return findChildByType(IDENTIFIER_LITERAL);
  }

  @Override
  @Nullable
  public PbMessageBody getBody() {
    return PsiTreeUtil.getChildOfType(this, PbMessageBody.class);
  }

  @Override
  @Nullable
  public PbNumberValue getFieldNumber() {
    return PsiTreeUtil.getChildOfType(this, PbNumberValue.class);
  }

  @Override
  @Nullable
  public PbFieldLabel getDeclaredLabel() {
    return PsiTreeUtil.getChildOfType(this, PbFieldLabel.class);
  }

}
