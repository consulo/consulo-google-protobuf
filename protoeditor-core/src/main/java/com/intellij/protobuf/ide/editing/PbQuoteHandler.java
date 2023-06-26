package com.intellij.protobuf.ide.editing;

import com.intellij.protobuf.lang.PbFileType;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.action.FileQuoteHandler;
import consulo.virtualFileSystem.fileType.FileType;
import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 26/06/2023
 */
@ExtensionImpl
public class PbQuoteHandler extends ProtoQuoteHandler implements FileQuoteHandler {
  @Nonnull
  @Override
  public FileType getFileType() {
    return PbFileType.INSTANCE;
  }
}
