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
package com.intellij.protobuf.ide.settings;

import com.intellij.protobuf.ide.PbIdeBundle;
import consulo.annotation.component.ExtensionImpl;
import consulo.configurable.Configurable;
import consulo.configurable.IdeaConfigurableBase;
import consulo.configurable.ProjectConfigurable;
import consulo.configurable.StandardConfigurableIds;
import consulo.project.Project;
import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link Configurable} that provides a protobuf language settings panel.
 */
@ExtensionImpl
public class PbLanguageSettingsConfigurable
  extends IdeaConfigurableBase<PbLanguageSettingsForm, PbProjectSettings> implements ProjectConfigurable {

  public static final String ID = "proto";

  private final Project project;

  @Inject
  public PbLanguageSettingsConfigurable(Project project) {
    super(ID, PbIdeBundle.message("settings.project.display"), null);
    this.project = project;
  }

  @Nullable
  @Override
  public String getParentId() {
    return StandardConfigurableIds.EDITOR_GROUP;
  }

  @NotNull
  @Override
  protected PbProjectSettings getSettings() {
    return PbProjectSettings.getInstance(project);
  }

  @Override
  protected PbLanguageSettingsForm createUi() {
    return new PbLanguageSettingsForm(project);
  }
}
