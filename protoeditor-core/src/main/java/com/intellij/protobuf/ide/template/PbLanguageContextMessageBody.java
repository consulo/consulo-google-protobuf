package com.intellij.protobuf.ide.template;

import com.intellij.protobuf.lang.psi.PbMessageBody;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.context.TemplateContextType;
import google.protobuf.localize.ProtobufIdeLocalize;

/**
 * {@link TemplateContextType} implementation that matches within a message.
 */
@ExtensionImpl
public class PbLanguageContextMessageBody extends PbLanguageContext.BlockBodyContext {
  public PbLanguageContextMessageBody() {
    super("PROTO_MESSAGE", ProtobufIdeLocalize.templateTypeMessage(), PbMessageBody.class);
  }
}
