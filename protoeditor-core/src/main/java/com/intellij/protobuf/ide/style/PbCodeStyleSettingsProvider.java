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
package com.intellij.protobuf.ide.style;

import com.intellij.protobuf.ide.PbIdeBundle;
import com.intellij.protobuf.ide.highlighter.PbSyntaxHighlighter;
import com.intellij.protobuf.lang.PbLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.EditorHighlighter;
import consulo.colorScheme.EditorColorsScheme;
import consulo.configurable.Configurable;
import consulo.language.codeStyle.CodeStyleSettings;
import consulo.language.codeStyle.CustomCodeStyleSettings;
import consulo.language.codeStyle.setting.CodeStyleSettingsProvider;
import consulo.language.codeStyle.ui.setting.CodeStyleAbstractConfigurable;
import consulo.language.codeStyle.ui.setting.CodeStyleAbstractPanel;
import consulo.language.codeStyle.ui.setting.TabbedLanguageCodeStylePanel;
import consulo.language.editor.highlight.EditorHighlighterFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExtensionImpl
public class PbCodeStyleSettingsProvider extends CodeStyleSettingsProvider {

  @Override
  public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
    return new PbCodeStyleSettings(settings);
  }

  @Override
  public String getConfigurableDisplayName() {
    return PbIdeBundle.message("plugin.name");
  }

  @NotNull
  @Override
  public Configurable createSettingsPage(
    @NotNull CodeStyleSettings settings, @NotNull CodeStyleSettings originalSettings) {
    return new CodeStyleAbstractConfigurable(settings, originalSettings, PbIdeBundle.message("plugin.name")) {
      @Override
      protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
        return new ProtoCodeStyleMainPanel(getCurrentSettings(), settings);
      }

      @Nullable
      @Override
      public String getHelpTopic() {
        return null;
      }
    };
  }

  private static class ProtoCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
    ProtoCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
      super(PbLanguage.INSTANCE, currentSettings, settings);
    }

    @Override
    protected EditorHighlighter createHighlighter(EditorColorsScheme scheme) {
      return EditorHighlighterFactory.getInstance()
                                     .createEditorHighlighter(new PbSyntaxHighlighter(true), scheme);
    }
  }
}
