package com.intellij.protobuf.lang.stub;

import com.intellij.protobuf.lang.psi.PbServiceMethod;
import consulo.language.psi.stub.StubBase;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.stub.StubElement;
import consulo.language.psi.util.QualifiedName;
import org.jetbrains.annotations.Nullable;

public class PbServiceMethodStub extends StubBase<PbServiceMethod>
  implements PbNamedElementStub<PbServiceMethod> {

  private final String name;

  public PbServiceMethodStub(@Nullable StubElement parent,
                             IStubElementType elementType,
                             String name) {
    super(parent, elementType);
    this.name = name;
  }

  @Override
  public @Nullable String getName() {
    return name;
  }

  @Override
  public @Nullable
  QualifiedName getQualifiedName() {
    return StubMethods.getQualifiedName(this);
  }
}
