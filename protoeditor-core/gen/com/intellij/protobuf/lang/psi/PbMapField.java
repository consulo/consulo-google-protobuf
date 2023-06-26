// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElement;

public interface PbMapField extends PbField, PbSymbolContributor {

  @Nullable
  PbOptionList getOptionList();

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  PbNumberValue getFieldNumber();

  @Nullable
  PbTypeName getKeyType();

  @Nullable
  PbTypeName getValueType();

  @Nullable
  PbFieldLabel getDeclaredLabel();

}
