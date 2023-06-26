// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import java.util.List;

import consulo.language.psi.PsiElement;
import org.jetbrains.annotations.*;

public interface PbServiceStream extends PbNamedElement, PbOptionStatementOwner {

  @NotNull
  List<PbMessageTypeName> getMessageTypeNameList();

  @Nullable
  PbMethodOptions getMethodOptions();

  @Nullable
  PsiElement getNameIdentifier();

}
