package com.intellij.protobuf.ide.template;

import com.intellij.protobuf.ide.PbIdeBundle;
import com.intellij.protobuf.lang.psi.PbOneofBody;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.context.TemplateContextType;

/**
 * {@link TemplateContextType} implementation that matches within a oneof.
 */
@ExtensionImpl
public class PbLanguageContextOneofBody extends PbLanguageContext.BlockBodyContext {
  public PbLanguageContextOneofBody() {
    super("PROTO_ONEOF", PbIdeBundle.message("template.type.extend"), PbOneofBody.class);
  }
}
