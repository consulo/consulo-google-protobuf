// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElement;

public interface PbSimpleField extends PbField {

  @Nullable
  PbOptionList getOptionList();

  @NotNull
  PbTypeName getTypeName();

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  PbNumberValue getFieldNumber();

  @Nullable
  PbFieldLabel getDeclaredLabel();

}
