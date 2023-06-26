package com.intellij.protobuf.ide.views;

import com.intellij.protobuf.lang.PbLanguage;
import com.intellij.protobuf.lang.psi.*;
import consulo.annotation.component.ExtensionImpl;
import consulo.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import consulo.language.Language;
import consulo.language.psi.PsiElement;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * @author VISTALL
 * @since 25/06/2023
 */
@ExtensionImpl
public class PbStructureAwareNavBarModelExtension extends StructureAwareNavBarModelExtension {
  @Nonnull
  @Override
  protected Language getLanguage() {
    return PbLanguage.INSTANCE;
  }

  @Nullable
  @Override
  public String getPresentableText(Object e) {
    if (e instanceof PbNamedElement pbNamedElement) {
      return pbNamedElement.getName();
    }
    return e.toString();
  }

  private String getTooltipPrefix(@NotNull PsiElement element) {
    if (element instanceof PbMessageDefinition) {
      return "message";
    }
    if (element instanceof PbEnumDefinition) {
      return "enum";
    }
    if (element instanceof PbEnumValue) {
      return "enum value";
    }
    if (element instanceof PbGroupDefinition) {
      return "group";
    }
    if (element instanceof PbMapField) {
      return "map";
    }
    if (element instanceof PbField) {
      return "field";
    }
    if (element instanceof PbOneofDefinition) {
      return "oneof";
    }
    if (element instanceof PbServiceDefinition) {
      return "service";
    }
    if (element instanceof PbServiceMethod) {
      return "rpc";
    }
    return "element";
  }
}
