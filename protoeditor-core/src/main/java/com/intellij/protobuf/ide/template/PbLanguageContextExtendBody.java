package com.intellij.protobuf.ide.template;

import com.intellij.protobuf.lang.psi.PbExtendBody;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.context.TemplateContextType;
import google.protobuf.localize.ProtobufIdeLocalize;

/**
 * {@link TemplateContextType} implementation that matches within an extend body.
 */
@ExtensionImpl
public class PbLanguageContextExtendBody extends PbLanguageContext.BlockBodyContext {
  public PbLanguageContextExtendBody() {
    super("PROTO_EXTEND", ProtobufIdeLocalize.templateTypeExtend(), PbExtendBody.class);
  }
}
