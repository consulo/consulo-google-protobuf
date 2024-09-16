package com.intellij.protobuf.ide.template;

import com.intellij.protobuf.ide.PbIdeBundle;
import com.intellij.protobuf.lang.psi.PbServiceBody;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.context.TemplateContextType;
import google.protobuf.localize.ProtobufIdeLocalize;

/**
 * {@link TemplateContextType} implementation that matches within a service.
 */
@ExtensionImpl
public class PbLanguageContextServiceBody extends PbLanguageContext.BlockBodyContext {
  public PbLanguageContextServiceBody() {
    super("PROTO_SERVICE", ProtobufIdeLocalize.templateTypeService(), PbServiceBody.class);
  }
}
