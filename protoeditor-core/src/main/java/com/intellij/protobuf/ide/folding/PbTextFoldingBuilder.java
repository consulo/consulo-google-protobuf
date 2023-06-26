package com.intellij.protobuf.ide.folding;

import com.intellij.protobuf.lang.PbTextLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 25/06/2023
 */
@ExtensionImpl
public class PbTextFoldingBuilder extends ProtoFoldingBuilder {
  @Nonnull
  @Override
  public Language getLanguage() {
    return PbTextLanguage.INSTANCE;
  }
}
