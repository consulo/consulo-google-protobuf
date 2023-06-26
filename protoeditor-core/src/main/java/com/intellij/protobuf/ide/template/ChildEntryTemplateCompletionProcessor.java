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

import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.completion.lookup.LookupElement;
import consulo.language.editor.template.TemplateCompletionProcessor;
import com.intellij.protobuf.lang.resolve.FileResolveProvider.ChildEntry;
import consulo.language.editor.template.ExpressionContext;

/**
 * A TemplateCompletionProcessor that prevents moving to the next template tab if the accepted
 * LookupItem was a directory {@link ChildEntry}.
 */
@ExtensionImpl
public class ChildEntryTemplateCompletionProcessor implements TemplateCompletionProcessor {
  @Override
  public boolean nextTabOnItemSelected(ExpressionContext context, LookupElement item) {
    Object object = item.getObject();
    return !(object instanceof ChildEntry && ((ChildEntry) object).isDirectory());
  }
}
