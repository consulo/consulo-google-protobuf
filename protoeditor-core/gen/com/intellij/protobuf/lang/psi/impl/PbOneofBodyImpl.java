// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi.impl;

import java.util.List;

import consulo.language.psi.PsiElementVisitor;
import org.jetbrains.annotations.*;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import consulo.language.psi.util.PsiTreeUtil;
import com.intellij.protobuf.lang.psi.*;

import static com.intellij.protobuf.lang.psi.ProtoTokenTypes.*;

public class PbOneofBodyImpl extends PbOneofBodyMixin implements PbOneofBody {

  public PbOneofBodyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PbVisitor visitor) {
    visitor.visitOneofBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PbVisitor) accept((PbVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PbGroupDefinition> getGroupDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PbGroupDefinition.class);
  }

  @Override
  @NotNull
  public List<PbSimpleField> getSimpleFieldList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PbSimpleField.class);
  }

  @Override
  @NotNull
  public List<PbOptionStatement> getOptionStatements() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PbOptionStatement.class);
  }

  @Override
  @NotNull
  public PsiElement getStart() {
    return notNullChild(findChildByType(LBRACE));
  }

  @Override
  @Nullable
  public PsiElement getEnd() {
    return findChildByType(RBRACE);
  }

}
