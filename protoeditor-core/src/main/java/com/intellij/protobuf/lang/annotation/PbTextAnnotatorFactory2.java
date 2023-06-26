package com.intellij.protobuf.lang.annotation;

import com.intellij.protobuf.lang.PbLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.annotation.Annotator;
import consulo.language.editor.annotation.AnnotatorFactory;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author VISTALL
 * @since 26/06/2023
 */
@ExtensionImpl
public class PbTextAnnotatorFactory2 implements AnnotatorFactory {
  @Nullable
  @Override
  public Annotator createAnnotator() {
    return new PbTextAnnotator();
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return PbLanguage.INSTANCE;
  }
}
