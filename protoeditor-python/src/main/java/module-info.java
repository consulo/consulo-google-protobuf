/**
 * @author VISTALL
 * @since 26/06/2023
 */
module consulo.google.protobuf.python {
  requires consulo.google.protobuf.api;

  requires consulo.code.editor.api;
  requires consulo.datacontext.api;
  requires consulo.language.api;
  requires consulo.language.editor.api;

  requires consulo.python.language.api;

  requires com.google.common;
}
