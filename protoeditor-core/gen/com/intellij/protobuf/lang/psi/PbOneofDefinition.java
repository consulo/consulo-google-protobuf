// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import consulo.language.psi.PsiElement;
import consulo.language.psi.StubBasedPsiElement;
import org.jetbrains.annotations.*;
import com.intellij.protobuf.lang.stub.PbOneofDefinitionStub;

public interface PbOneofDefinition extends PbDefinition, PbNamedElement, StubBasedPsiElement<PbOneofDefinitionStub>
{

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  PbOneofBody getBody();

}
