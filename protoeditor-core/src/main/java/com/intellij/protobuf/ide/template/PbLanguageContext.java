/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.protobuf.ide.template;

import com.intellij.protobuf.ide.PbIdeBundle;
import com.intellij.protobuf.lang.PbLanguage;
import com.intellij.protobuf.lang.psi.*;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.context.TemplateActionContext;
import consulo.language.editor.template.context.TemplateContextType;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiUtilCore;
import consulo.language.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Defines a Live Template context for protobuf files types.
 */
@ExtensionImpl
public class PbLanguageContext extends TemplateContextType {

  public PbLanguageContext() {
    super("PROTO", PbIdeBundle.message("settings.project.display"));
  }

  @Override
  public boolean isInContext(@NotNull TemplateActionContext templateActionContext) {
    return PbLanguage.INSTANCE.is(PsiUtilCore.getLanguageAtOffset(
      templateActionContext.getFile(), templateActionContext.getStartOffset()));
  }

  /**
   * Base context that returns true when the closest parent block is of the given type.
   */
  abstract static class BlockBodyContext extends TemplateContextType {

    private final Class<? extends PbBlockBody> bodyClass;

    BlockBodyContext(String id, String presentableName, Class<? extends PbBlockBody> bodyClass) {
      super(id, presentableName, PbLanguageContext.class);
      this.bodyClass = bodyClass;
    }

    @Override
    public boolean isInContext(@NotNull TemplateActionContext templateActionContext) {
      PsiElement element = PsiUtilCore.getElementAtOffset(
        templateActionContext.getFile(), templateActionContext.getStartOffset());
      return bodyClass.isInstance(
        PsiTreeUtil.getParentOfType(element, PbBlockBody.class, /* strict */ false));
    }
  }

}
