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

import com.intellij.protobuf.ide.settings.PbProjectSettings.ImportPathEntry;
import consulo.annotation.component.ExtensionImpl;
import consulo.module.content.ProjectRootManager;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.util.VirtualFileUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A {@link ProjectSettingsConfigurator} that generates configuration based on project source roots.
 *
 * <p>The descriptor is set to <code>google/protobuf/descriptor.proto</code>.
 */
@ExtensionImpl(order = "last")
public final class DefaultConfigurator implements ProjectSettingsConfigurator {
  private static final String DESCRIPTOR = "google/protobuf/descriptor.proto";

  @Override
  public @NotNull PbProjectSettings configure(Project project, PbProjectSettings settings) {
    settings.setDescriptorPath(DESCRIPTOR);
    settings.getImportPathEntries().clear();
    ProjectRootManager rootManager = ProjectRootManager.getInstance(project);
    // Source roots first, then content roots, so modules without source folders still resolve
    // imports relative to their root.
    Set<VirtualFile> roots = new LinkedHashSet<>();
    Collections.addAll(roots, rootManager.getContentSourceRoots());
    Collections.addAll(roots, rootManager.getContentRoots());
    for (VirtualFile root : roots) {
      settings.getImportPathEntries().add(new ImportPathEntry(root.getUrl(), ""));
    }

    ImportPathEntry includeEntry = getBuiltInIncludeEntry();
    if (includeEntry != null) {
      settings.getImportPathEntries().add(includeEntry);
    }

    return settings;
  }

  @NotNull
  @Override
  public Collection<String> getDescriptorPathSuggestions(Project project) {
    return Collections.singletonList(DESCRIPTOR);
  }

  @Nullable
  static ImportPathEntry getBuiltInIncludeEntry() {
    // Directory resources are not visible through a plugin module class loader, so locate
    // a known file inside the include root and walk up to it.
    URL descriptorUrl = DefaultConfigurator.class.getClassLoader().getResource("include/" + DESCRIPTOR);
    if (descriptorUrl == null) {
      return null;
    }
    VirtualFile descriptorFile = VirtualFileUtil.findFileByURL(descriptorUrl);
    if (descriptorFile == null) {
      return null;
    }
    // include/google/protobuf/descriptor.proto -> include
    VirtualFile descriptorsDirectory = descriptorFile.getParent();
    for (int i = 0; i < 2 && descriptorsDirectory != null; i++) {
      descriptorsDirectory = descriptorsDirectory.getParent();
    }
    if (descriptorsDirectory == null || !descriptorsDirectory.isDirectory()) {
      return null;
    }
    return new ImportPathEntry(descriptorsDirectory.getUrl(), null);
  }
}
