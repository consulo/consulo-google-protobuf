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

import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ServiceAPI;
import consulo.annotation.component.ServiceImpl;
import consulo.component.persist.PersistentStateComponent;
import consulo.project.Project;
import consulo.component.persist.State;
import consulo.component.persist.Storage;
import consulo.ide.ServiceManager;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * A persistent service that stores protobuf text format settings.
 */
@State(name = "PrototextLanguageSettings", storages = @Storage("protoeditor.xml"))
@ServiceAPI(ComponentScope.PROJECT)
@ServiceImpl
@Singleton
public class PbTextLanguageSettings
  implements PersistentStateComponent<PbTextLanguageSettings.State> {

  private State state;

  @Inject
  public PbTextLanguageSettings() {
    this(new State());
  }

  private PbTextLanguageSettings(State state) {
    this.state = state;
  }

  public static PbTextLanguageSettings getInstance(Project project) {
    return ServiceManager.getService(project, PbTextLanguageSettings.class);
  }

  @Nullable
  @Override
  public State getState() {
    return state;
  }

  @Override
  public void loadState(@NotNull State state) {
    this.state = state;
  }

  public boolean isMissingSchemaWarningEnabled() {
    return state.missingSchemaWarningEnabled;
  }

  public void setMissingSchemaWarningEnabled(boolean value) {
    state.missingSchemaWarningEnabled = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof PbTextLanguageSettings)) {
      return false;
    }
    PbTextLanguageSettings other = (PbTextLanguageSettings)obj;
    return Objects.equals(isMissingSchemaWarningEnabled(), other.isMissingSchemaWarningEnabled());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(isMissingSchemaWarningEnabled());
  }

  /**
   * Persistent state holder.
   *
   * <p>Values must be public to be serialized. The initial values below represent defaults.
   */
  static class State {
    public boolean missingSchemaWarningEnabled = true;
  }
}
