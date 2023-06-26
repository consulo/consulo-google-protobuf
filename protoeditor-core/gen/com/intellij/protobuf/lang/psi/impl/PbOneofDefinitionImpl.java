// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.IStubElementType;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.util.PsiTreeUtil;
import com.intellij.protobuf.lang.psi.*;
import com.intellij.protobuf.lang.stub.PbOneofDefinitionStub;

import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbOneofDefinitionImpl extends PbOneofDefinitionMixin implements PbOneofDefinition {

  public PbOneofDefinitionImpl(ASTNode node) {
    super(node);
  }

  public PbOneofDefinitionImpl(PbOneofDefinitionStub stub, IStubElementType type) {
    super(stub, type);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitOneofDefinition(this);
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
  public PbOneofBody getBody() {
    return PsiTreeUtil.getChildOfType(this, PbOneofBody.class);
  }

}
