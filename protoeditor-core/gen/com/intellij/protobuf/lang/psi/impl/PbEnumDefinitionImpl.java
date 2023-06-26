// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.*;

import com.intellij.protobuf.lang.psi.*;
import com.intellij.protobuf.lang.stub.PbEnumDefinitionStub;

import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbEnumDefinitionImpl extends PbEnumDefinitionMixin implements PbEnumDefinition {

  public PbEnumDefinitionImpl(ASTNode node) {
    super(node);
  }

  public PbEnumDefinitionImpl(PbEnumDefinitionStub stub, IStubElementType type) {
    super(stub, type);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitEnumDefinition(this);
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
  public PbEnumBody getBody() {
    return PsiTreeUtil.getChildOfType(this, PbEnumBody.class);
  }

}
