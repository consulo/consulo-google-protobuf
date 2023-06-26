package com.intellij.protobuf.ide.template;

import com.intellij.protobuf.ide.PbIdeBundle;
import com.intellij.protobuf.lang.psi.PbEnumBody;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.context.TemplateContextType;

/**
 * {@link TemplateContextType} implementation that matches within an enum.
 */
@ExtensionImpl
public class PbLanguageContextEnumBody extends PbLanguageContext.BlockBodyContext {
  public PbLanguageContextEnumBody() {
    super("PROTO_ENUM", PbIdeBundle.message("template.type.enum"), PbEnumBody.class);
  }
}
