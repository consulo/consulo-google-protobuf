package com.intellij.protobuf.ide.editing;

import com.intellij.protobuf.lang.PbTextLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 25/06/2023
 */
@ExtensionImpl
public class PbTextBraceMatcher extends ProtoBraceMatcher {
  @Nonnull
  @Override
  public Language getLanguage() {
    return PbTextLanguage.INSTANCE;
  }
}
