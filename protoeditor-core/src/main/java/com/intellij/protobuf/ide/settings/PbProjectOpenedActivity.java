package com.intellij.protobuf.ide.settings;

import consulo.annotation.component.ExtensionImpl;
import consulo.project.Project;
import consulo.project.startup.BackgroundStartupActivity;
import consulo.ui.UIAccess;
import jakarta.annotation.Nonnull;

@ExtensionImpl
public final class PbProjectOpenedActivity implements BackgroundStartupActivity {
  @Override
  public void runActivity(@Nonnull Project project, @Nonnull UIAccess uiAccess) {
    ProjectSettingsConfiguratorManager instance = ProjectSettingsConfiguratorManager.getInstance(project);
    //project.getExtensionArea().getExtensionPoint(ProjectSettingsConfigurator.EP_NAME)
    //    .addChangeListener(instance::configureSettingsIfNecessary, instance);
    instance.configureSettingsIfNecessary();
  }
}
