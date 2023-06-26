// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import consulo.language.ast.ASTNode;
import consulo.language.psi.stub.IStubElementType;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.psi.util.PsiTreeUtil;
import com.intellij.protobuf.lang.psi.*;
import com.intellij.protobuf.lang.stub.PbPackageStatementStub;

public class PbPackageStatementImpl extends PbPackageStatementMixin implements PbPackageStatement {

  public PbPackageStatementImpl(ASTNode node) {
    super(node);
  }

  public PbPackageStatementImpl(PbPackageStatementStub stub, IStubElementType type) {
    super(stub, type);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitPackageStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PbPackageName getPackageName() {
    return PsiTreeUtil.getChildOfType(this, PbPackageName.class);
  }

}
