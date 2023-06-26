package com.intellij.protobuf.ide.template;

import com.intellij.protobuf.ide.PbIdeBundle;
import com.intellij.protobuf.lang.psi.PbExtendBody;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.context.TemplateContextType;

/**
 * {@link TemplateContextType} implementation that matches within an extend body.
 */
@ExtensionImpl
public class PbLanguageContextExtendBody extends PbLanguageContext.BlockBodyContext {
  public PbLanguageContextExtendBody() {
    super("PROTO_EXTEND", PbIdeBundle.message("template.type.extend"), PbExtendBody.class);
  }
}
