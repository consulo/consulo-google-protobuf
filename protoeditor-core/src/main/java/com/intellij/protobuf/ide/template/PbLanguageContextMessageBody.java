package com.intellij.protobuf.ide.template;

import com.intellij.protobuf.ide.PbIdeBundle;
import com.intellij.protobuf.lang.psi.PbMessageBody;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.context.TemplateContextType;

/**
 * {@link TemplateContextType} implementation that matches within a message.
 */
@ExtensionImpl
public class PbLanguageContextMessageBody extends PbLanguageContext.BlockBodyContext {
  public PbLanguageContextMessageBody() {
    super("PROTO_MESSAGE", PbIdeBundle.message("template.type.message"), PbMessageBody.class);
  }
}
