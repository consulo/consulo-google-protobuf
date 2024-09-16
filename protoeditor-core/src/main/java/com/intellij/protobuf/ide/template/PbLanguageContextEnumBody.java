package com.intellij.protobuf.ide.template;

import com.intellij.protobuf.lang.psi.PbEnumBody;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.context.TemplateContextType;
import google.protobuf.localize.ProtobufIdeLocalize;

/**
 * {@link TemplateContextType} implementation that matches within an enum.
 */
@ExtensionImpl
public class PbLanguageContextEnumBody extends PbLanguageContext.BlockBodyContext {
  public PbLanguageContextEnumBody() {
    super("PROTO_ENUM", ProtobufIdeLocalize.templateTypeEnum(), PbEnumBody.class);
  }
}
