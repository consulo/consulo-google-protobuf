package com.intellij.protobuf.lang;

import com.intellij.protobuf.lang.psi.PbTextFile;
import consulo.annotation.component.ExtensionImpl;
import consulo.virtualFileSystem.fileType.FileTypeConsumer;
import consulo.virtualFileSystem.fileType.FileTypeFactory;
import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 25/06/2023
 */
@ExtensionImpl
public class PbFileTypeFactory extends FileTypeFactory {
  @Override
  public void createFileTypes(@Nonnull FileTypeConsumer fileTypeConsumer) {
    fileTypeConsumer.consume(PbFileType.INSTANCE);
    fileTypeConsumer.consume(PbTextFileType.INSTANCE, "pb;textprot");
  }
}
