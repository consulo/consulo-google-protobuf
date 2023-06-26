// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import java.util.List;

import consulo.language.psi.PsiElement;
import org.jetbrains.annotations.*;

public interface PbTextValueList extends ProtoBlockBody {

  @NotNull
  List<PbTextIdentifierValue> getIdentifierValueList();

  @NotNull
  List<PbTextMessageValue> getMessageValueList();

  @NotNull
  List<PbTextNumberValue> getNumberValueList();

  @NotNull
  List<PbTextStringValue> getStringValueList();

  @NotNull
  PsiElement getStart();

  @Nullable
  PsiElement getEnd();

}
