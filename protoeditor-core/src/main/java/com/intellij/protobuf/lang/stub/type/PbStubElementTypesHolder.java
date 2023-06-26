package com.intellij.protobuf.lang.stub.type;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.psi.stub.ObjectStubSerializerProvider;
import consulo.language.psi.stub.StubElementTypeHolder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author VISTALL
 * @since 26/06/2023
 */
@ExtensionImpl
public class PbStubElementTypesHolder extends StubElementTypeHolder<PbStubElementTypes> {
  @Nullable
  @Override
  public String getExternalIdPrefix() {
    return null;
  }

  @Nonnull
  @Override
  public List<ObjectStubSerializerProvider> loadSerializers() {
    return allFromStaticFields(PbStubElementTypes.class, Field::get);
  }
}
