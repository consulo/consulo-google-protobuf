/**
 * @author VISTALL
 * @since 26/06/2023
 */
module consulo.google.protobuf.jvm {
  requires consulo.google.protobuf.api;

  requires consulo.application.api;
  requires consulo.code.editor.api;
  requires consulo.datacontext.api;
  requires consulo.find.api;
  requires consulo.index.io;
  requires consulo.language.api;
  requires consulo.language.editor.api;
  requires consulo.logging.api;
  requires consulo.module.api;
  requires consulo.project.api;
  requires consulo.util.collection;
  requires consulo.util.io;
  requires consulo.util.lang;
  requires consulo.virtual.file.system.api;

  requires consulo.java.language.api;

  requires com.google.common;
}
