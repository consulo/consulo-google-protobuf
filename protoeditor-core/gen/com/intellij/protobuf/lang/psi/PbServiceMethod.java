// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import java.util.List;

import consulo.language.psi.PsiElement;
import consulo.language.psi.StubBasedPsiElement;
import org.jetbrains.annotations.*;
import com.intellij.protobuf.lang.stub.PbServiceMethodStub;

public interface PbServiceMethod extends PbNamedElement, PbOptionStatementOwner, StubBasedPsiElement<PbServiceMethodStub>
{

  @Nullable
  PbMethodOptions getMethodOptions();

  @NotNull
  List<PbServiceMethodType> getServiceMethodTypeList();

  @Nullable
  PsiElement getNameIdentifier();

}
