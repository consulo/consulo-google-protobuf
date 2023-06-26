// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import org.jetbrains.annotations.*;
import consulo.language.psi.PsiElement;
import consulo.language.psi.StubBasedPsiElement;
import com.intellij.protobuf.lang.stub.PbEnumDefinitionStub;

public interface PbEnumDefinition extends PbDefinition, PbNamedTypeElement, PbEnumDefinitionBase, StubBasedPsiElement<PbEnumDefinitionStub> {

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  PbEnumBody getBody();

}
