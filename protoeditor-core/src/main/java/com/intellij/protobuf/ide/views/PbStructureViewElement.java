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
package com.intellij.protobuf.ide.views;

import consulo.annotation.access.RequiredReadAction;
import consulo.language.editor.structureView.PsiTreeElementBase;
import com.intellij.protobuf.lang.psi.*;
import consulo.fileEditor.structureView.StructureViewTreeElement;
import consulo.localize.LocalizeValue;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Represents a protobuf element in structure view.
 */
public class PbStructureViewElement extends PsiTreeElementBase<PbElement> {
    public PbStructureViewElement(PbElement element) {
        super(element);
    }

    @NotNull
    @Override
    @RequiredReadAction
    public Collection<StructureViewTreeElement> getChildrenBase() {
        PbElement element = getElement();
        if (!(element instanceof PbStatementOwner owner)) {
            return Collections.emptyList();
        }
        // Only allow expanding statements at lower levels. Otherwise, statements like
        // syntax, import, file-levels option, etc. show up at the top-level.
        boolean allowStatements = !(element instanceof PbFile);
        return owner
            .getStatements()
            .stream()
            .filter(child -> child instanceof PbDefinition || allowStatements)
            .map(PbStructureViewElement::new)
            .collect(Collectors.toList());
    }

    @Override
    @RequiredReadAction
    public LocalizeValue getPresentableText() {
        PbElement element = getElement();
        if (element == null) {
            return LocalizeValue.empty();
        }
        if (element instanceof PbNamedElement named) {
            return LocalizeValue.ofNullable(named.getName());
        }
        else if (element instanceof PbStatement stmt) {
            return stmt.getPresentableText();
        }
        else if (element instanceof PbFile file) {
            return LocalizeValue.of(file.getName());
        }
        return LocalizeValue.of(element.getText());
    }
}
