// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import org.jetbrains.annotations.*;
import consulo.language.psi.StubBasedPsiElement;
import com.intellij.protobuf.lang.stub.PbExtendDefinitionStub;

public interface PbExtendDefinition extends PbDefinition, StubBasedPsiElement<PbExtendDefinitionStub> {

  @Nullable
  PbExtendBody getBody();

  @Nullable
  PbMessageTypeName getTypeName();

}
