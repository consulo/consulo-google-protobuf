/**
 * @author VISTALL
 * @since 26/06/2023
 */
open module consulo.google.protobuf.api {
  requires transitive consulo.ide.api;

  requires transitive com.google.common;

  // TODO remove in future
  requires forms.rt;
  requires java.desktop;

  exports com.intellij.protobuf.ide;
  exports com.intellij.protobuf.ide.actions;
  exports com.intellij.protobuf.ide.documentation;
  exports com.intellij.protobuf.ide.editing;
  exports com.intellij.protobuf.ide.folding;
  exports com.intellij.protobuf.ide.formatter;
  exports com.intellij.protobuf.ide.highlighter;
  exports com.intellij.protobuf.ide.settings;
  exports com.intellij.protobuf.ide.spelling;
  exports com.intellij.protobuf.ide.style;
  exports com.intellij.protobuf.ide.template;
  exports com.intellij.protobuf.ide.util;
  exports com.intellij.protobuf.ide.views;
  exports com.intellij.protobuf.lang;
  exports com.intellij.protobuf.lang.annotation;
  exports com.intellij.protobuf.lang.completion;
  exports com.intellij.protobuf.lang.descriptor;
  exports com.intellij.protobuf.lang.findusages;
  exports com.intellij.protobuf.lang.gotobyname;
  exports com.intellij.protobuf.lang.lexer;
  exports com.intellij.protobuf.lang.names;
  exports com.intellij.protobuf.lang.parser;
  exports com.intellij.protobuf.lang.psi;
  exports com.intellij.protobuf.lang.psi.impl;
  exports com.intellij.protobuf.lang.psi.type;
  exports com.intellij.protobuf.lang.psi.util;
  exports com.intellij.protobuf.lang.resolve;
  exports com.intellij.protobuf.lang.resolve.directive;
  exports com.intellij.protobuf.lang.stub;
  exports com.intellij.protobuf.lang.stub.index;
  exports com.intellij.protobuf.lang.stub.type;
  exports com.intellij.protobuf.lang.util;
  exports com.intellij.protobuf.shared.gencode;
  exports consulo.google.protobuf.icon;
  exports google.protobuf.localize;
}