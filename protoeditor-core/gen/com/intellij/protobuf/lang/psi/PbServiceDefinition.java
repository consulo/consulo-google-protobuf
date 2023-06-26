// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import consulo.language.psi.StubBasedPsiElement;
import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElement;
import com.intellij.protobuf.lang.stub.PbServiceDefinitionStub;

public interface PbServiceDefinition extends PbDefinition, PbNamedElement, PbSymbolOwner, StubBasedPsiElement<PbServiceDefinitionStub>
{

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  PbServiceBody getBody();

}
