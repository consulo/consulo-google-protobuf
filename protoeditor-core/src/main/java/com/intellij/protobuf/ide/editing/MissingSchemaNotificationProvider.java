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
package com.intellij.protobuf.ide.editing;

import com.intellij.protobuf.ide.actions.InsertSchemaDirectiveAction;
import com.intellij.protobuf.ide.settings.PbTextLanguageSettings;
import com.intellij.protobuf.ide.settings.PbTextLanguageSettingsConfigurable;
import com.intellij.protobuf.lang.psi.PbTextFile;
import com.intellij.protobuf.lang.resolve.directive.SchemaDirective;
import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.document.Document;
import consulo.fileEditor.*;
import consulo.ide.setting.ShowSettingsUtil;
import consulo.language.psi.PsiDocumentManager;
import consulo.language.psi.PsiFile;
import consulo.project.Project;
import consulo.util.collection.WeakList;
import consulo.virtualFileSystem.VirtualFile;
import google.protobuf.localize.ProtobufIdeLocalize;
import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import org.jetbrains.annotations.Nullable;

import javax.swing.event.HyperlinkListener;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Provides an editor notification (a bar at the top of the editor) when a text format file is
 * opened without an associated root message. The notification provides some remediation actions.
 */
@ExtensionImpl
public class MissingSchemaNotificationProvider implements EditorNotificationProvider {
  private final Collection<VirtualFile> ignoredFiles = new WeakList<>();

  private final Project myProject;

  @Inject
  public MissingSchemaNotificationProvider(Project project) {
    myProject = project;
  }

  /**
   * Updates the notification for the given file. If the file no longer needs a notification, it
   * will be removed.
   *
   * @param file the file whose notifications should be reevaluated.
   */
  public static void update(PbTextFile file) {
    if (file == null) {
      return;
    }
    VirtualFile virtualFile = file.getVirtualFile();
    if (virtualFile == null) {
      return;
    }
    EditorNotifications.getInstance(file.getProject()).updateNotifications(virtualFile);
  }

  @Nonnull
  @Override
  public String getId() {
    return "protobuf.missing.scheme.notification";
  }

  @RequiredReadAction
  @Nullable
  @Override
  public EditorNotificationBuilder buildNotification(@Nonnull VirtualFile virtualFile,
                                                     @Nonnull FileEditor fileEditor,
                                                     @Nonnull Supplier<EditorNotificationBuilder> supplier) {
    if (ignoredFiles.contains(virtualFile)) {
      return null;
    }
    if (!(fileEditor instanceof TextEditor)) {
      return null;
    }
    TextEditor textEditor = (TextEditor)fileEditor;
    Editor editor = textEditor.getEditor();
    PbTextLanguageSettings settings = PbTextLanguageSettings.getInstance(myProject);
    if (settings == null || !settings.isMissingSchemaWarningEnabled()) {
      return null;
    }

    Document document = editor.getDocument();
    PsiDocumentManager documentManager = PsiDocumentManager.getInstance(myProject);
    PsiFile file = documentManager.getPsiFile(document);
    if (!(file instanceof PbTextFile)) {
      return null;
    }
    PbTextFile textFile = (PbTextFile)file;

    SchemaDirective existingDirective = SchemaDirective.find(textFile);
    if (existingDirective != null) {
      // One or both of the comments already exists, and should be annotated with warnings if
      // they're not correct. Defer to that and don't show the top notification bar.
      return null;
    }

    return createPanelForTextFormatFile(textFile, supplier);
  }

  private EditorNotificationBuilder createPanelForTextFormatFile(PbTextFile file, Supplier<EditorNotificationBuilder> factory) {
    if (file.isBound()) {
      // File has a schema association, so we don't create the notification.
      return null;
    }

    EditorNotificationBuilder builder = factory.get();
    builder.withText(ProtobufIdeLocalize.prototextMissingSchemaMessage());

    // TODO(volkman): also support a project-level configuration mechanism that will work for
    // readonly files.
    if (file.isWritable()) {
      builder
        .withAction(
          ProtobufIdeLocalize.prototextMissingSchemaInsertAnnotation(),
          InsertSchemaDirectiveAction.ACTION_ID);
    }
    PbTextLanguageSettings settings = PbTextLanguageSettings.getInstance(file.getProject());
    if (settings != null) {
      builder
        .withAction(
          ProtobufIdeLocalize.prototextMissingSchemaSettings(),
          (e) -> {
            ShowSettingsUtil.getInstance()
                            .showSettingsDialog(file.getProject(), PbTextLanguageSettingsConfigurable.class)
                            .doWhenDone(() -> {
                              EditorNotifications.getInstance(file.getProject()).updateNotifications(file.getVirtualFile());
                            });
          });
    }
    builder
      .withAction(ProtobufIdeLocalize.prototextMissingSchemaIgnore(), (e) -> {
        ignoredFiles.add(file.getVirtualFile());
        EditorNotifications.getInstance(file.getProject()).updateNotifications(file.getVirtualFile());
      });
    return builder;
  }
}
