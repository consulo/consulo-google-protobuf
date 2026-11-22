/*
 * Copyright 2013-2026 consulo.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package consulo.google.protobuf.it;

import com.intellij.protobuf.ide.settings.PbProjectSettings;
import com.intellij.protobuf.ide.settings.ProjectSettingsConfiguratorManager;
import com.intellij.protobuf.lang.psi.PbFile;
import consulo.application.ReadAction;
import consulo.it.CodeInsightTestFixture;
import consulo.it.HeadlessProjectExtension;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiReference;
import consulo.project.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Imports resolve against the automatically configured import paths: the module roots and the descriptors bundled
 * with the plugin ({@code include/google/protobuf}).
 */
@ExtendWith(HeadlessProjectExtension.class)
public class PbImportResolveTest {
    private static final String SRC = CodeInsightTestFixture.SOURCE_ROOT + "/";

    @Test
    public void testImportSibling(CodeInsightTestFixture fixture) throws Exception {
        fixture.addFileToProject(SRC + "common.proto", "syntax = \"proto3\";\npackage mmo;\nmessage Vec3 {}\n");
        PsiElement target = resolve(fixture, "session.proto", "syntax = \"proto3\";\nimport \"com<caret>mon.proto\";\n");
        assertFile(target, "common.proto");
    }

    @Test
    public void testImportSubdirectory(CodeInsightTestFixture fixture) throws Exception {
        fixture.addFileToProject(SRC + "internal/admin.proto", "syntax = \"proto3\";\npackage mmo.internal;\n");
        PsiElement target = resolve(fixture, "envelope.proto", "syntax = \"proto3\";\nimport \"internal/adm<caret>in.proto\";\n");
        assertFile(target, "admin.proto");
    }

    @Test
    public void testImportFromContentRoot(CodeInsightTestFixture fixture) throws Exception {
        // outside of the source root: found through the content root of the module
        fixture.addFileToProject("root.proto", "syntax = \"proto3\";\n");
        PsiElement target = resolve(fixture, "main.proto", "syntax = \"proto3\";\nimport \"ro<caret>ot.proto\";\n");
        assertFile(target, "root.proto");
    }

    @Test
    public void testImportBundledWellKnownType(CodeInsightTestFixture fixture) throws Exception {
        PsiElement target = resolve(fixture, "main.proto",
            "syntax = \"proto3\";\nimport \"google/protobuf/time<caret>stamp.proto\";\n");
        assertFile(target, "timestamp.proto");
    }

    @Test
    public void testImportBundledDescriptor(CodeInsightTestFixture fixture) throws Exception {
        PsiElement target = resolve(fixture, "main.proto",
            "syntax = \"proto3\";\nimport \"google/protobuf/desc<caret>riptor.proto\";\n");
        assertFile(target, "descriptor.proto");
    }

    @Test
    public void testBuiltInOptionResolvesToDescriptor(CodeInsightTestFixture fixture) throws Exception {
        PsiElement target = resolve(fixture, "main.proto",
            "syntax = \"proto3\";\noption java_pack<caret>age = \"com.example\";\n");
        assertEquals("descriptor.proto", ReadAction.compute(() -> target.getContainingFile().getName()));
    }

    private static PsiElement resolve(CodeInsightTestFixture fixture, String fileName, String text) throws Exception {
        configureImportPaths(fixture.getProject());
        fixture.configureByText(fileName, text);
        PsiReference reference = fixture.getReferenceAtCaretPositionWithAssertion();
        PsiElement target = ReadAction.compute(reference::resolve);
        assertNotNull(target, () -> "unresolved: " + ReadAction.compute(reference::getCanonicalText) + " in "
            + PbProjectSettings.getInstance(fixture.getProject()).getImportPathEntries().stream()
            .map(PbProjectSettings.ImportPathEntry::getLocation).toList());
        return target;
    }

    /**
     * The automatic configuration runs on a pooled thread after the project opens and its roots change; run it here
     * so the test does not race it.
     */
    private static void configureImportPaths(Project project) {
        PbProjectSettings settings = PbProjectSettings.getInstance(project);
        PbProjectSettings configured = ReadAction.compute(
            () -> ProjectSettingsConfiguratorManager.getInstance(project).configure(settings));
        assertNotNull(configured, "no configurator produced import paths");
        settings.copyState(configured);
    }

    private static void assertFile(PsiElement target, String fileName) {
        assertInstanceOf(PbFile.class, target);
        assertEquals(fileName, ReadAction.compute(() -> ((PbFile) target).getName()));
    }
}
