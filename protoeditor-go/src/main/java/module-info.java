/**
 * @author VISTALL
 * @since 26/06/2023
 */
module consulo.google.protobuf.go {
  requires consulo.google.protobuf.api;

  requires consulo.code.editor.api;
  requires consulo.datacontext.api;
  requires consulo.language.api;
  requires consulo.language.editor.api;
  requires consulo.language.impl;
  requires consulo.virtual.file.system.api;

  requires consulo.google.go;

  requires com.google.common;
}
