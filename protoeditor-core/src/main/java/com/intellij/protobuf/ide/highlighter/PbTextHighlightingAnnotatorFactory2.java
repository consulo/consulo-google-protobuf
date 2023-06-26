package com.intellij.protobuf.ide.highlighter;

import com.intellij.protobuf.lang.PbLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.annotation.Annotator;
import consulo.language.editor.annotation.AnnotatorFactory;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author VISTALL
 * @since 25/06/2023
 */
@ExtensionImpl
public class PbTextHighlightingAnnotatorFactory2 implements AnnotatorFactory {
  @Nullable
  @Override
  public Annotator createAnnotator() {
    return new PbTextHighlightingAnnotator();
  }

  @Nonnull
  @Override
  public Language getLanguage() {
    return PbLanguage.INSTANCE;
  }
}
