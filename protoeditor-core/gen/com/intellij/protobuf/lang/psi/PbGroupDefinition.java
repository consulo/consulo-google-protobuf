// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import consulo.language.psi.PsiElement;
import org.jetbrains.annotations.*;
import consulo.language.psi.StubBasedPsiElement;
import com.intellij.protobuf.lang.stub.PbGroupDefinitionStub;

public interface PbGroupDefinition extends PbDefinition, PbMessageType, PbSymbolContributor, PbGroupDefinitionBase, StubBasedPsiElement<PbGroupDefinitionStub> {

  @Nullable
  PbGroupOptionContainer getGroupOptionContainer();

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  PbMessageBody getBody();

  @Nullable
  PbNumberValue getFieldNumber();

  @Nullable
  PbFieldLabel getDeclaredLabel();

}
