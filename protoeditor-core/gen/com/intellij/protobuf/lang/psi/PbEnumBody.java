// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import java.util.List;

import consulo.language.psi.PsiElement;
import org.jetbrains.annotations.*;

public interface PbEnumBody extends PbBlockBody, PbOptionStatementOwner {

  @NotNull
  List<PbEnumReservedStatement> getEnumReservedStatementList();

  @NotNull
  List<PbEnumValue> getEnumValueList();

  @NotNull
  List<PbOptionStatement> getOptionStatements();

  @NotNull
  PsiElement getStart();

  @Nullable
  PsiElement getEnd();

}
