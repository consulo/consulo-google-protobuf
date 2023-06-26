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

import com.intellij.protobuf.lang.psi.PbAggregateValue;
import com.intellij.protobuf.lang.psi.PbFile;
import com.intellij.protobuf.lang.psi.PbTextFile;
import consulo.annotation.component.ExtensionImpl;
import consulo.application.ApplicationManager;
import consulo.codeEditor.*;
import consulo.codeEditor.util.EditorModificationUtil;
import consulo.document.Document;
import consulo.document.RangeMarker;
import consulo.language.ast.IElementType;
import consulo.language.codeStyle.CodeStyleManager;
import consulo.language.editor.CodeInsightSettings;
import consulo.language.editor.action.BraceMatchingUtil;
import consulo.language.editor.action.TypedHandlerDelegate;
import consulo.language.editor.highlight.BraceMatcher;
import consulo.language.editor.highlight.NontrivialBraceMatcher;
import consulo.language.psi.PsiDocumentManager;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.language.util.IncorrectOperationException;
import consulo.logging.Logger;
import consulo.project.Project;
import consulo.util.lang.CharArrayUtil;
import consulo.virtualFileSystem.fileType.FileType;
import jakarta.annotation.Nonnull;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link TypedHandlerDelegate} that auto-inserts matching '>' characters, similar to
 * auto-insertion of matching '}'.
 */
@ExtensionImpl
public class ProtoTypedHandler extends TypedHandlerDelegate {
  private static final Logger logger = Logger.getInstance(ProtoTypedHandler.class);

  @NotNull
  @Override
  public Result beforeCharTyped(
    final char c,
    @NotNull final Project project,
    @NotNull final Editor editor,
    @NotNull final PsiFile file,
    @NotNull FileType fileType) {
    if (c == '>' && handleFile(file) && handleRParen(editor, file.getFileType(), c)) {
      return Result.STOP;
    }
    return Result.CONTINUE;
  }

  public static boolean handleRParen(@Nonnull Editor editor, @Nonnull FileType fileType, char charTyped) {
    if (!CodeInsightSettings.getInstance().AUTOINSERT_PAIR_BRACKET) return false;

    int offset = editor.getCaretModel().getOffset();

    if (offset == editor.getDocument().getTextLength()) return false;

    HighlighterIterator iterator = ((EditorEx)editor).getHighlighter().createIterator(offset);
    if (iterator.atEnd()) return false;

    if (iterator.getEnd() - iterator.getStart() != 1 || editor.getDocument().getCharsSequence().charAt(iterator.getStart()) != charTyped) {
      return false;
    }

    BraceMatcher braceMatcher = BraceMatchingUtil.getBraceMatcher(fileType, iterator);
    CharSequence text = editor.getDocument().getCharsSequence();
    if (!braceMatcher.isRBraceToken(iterator, text, fileType)) {
      return false;
    }

    IElementType tokenType = (IElementType)iterator.getTokenType();

    iterator.retreat();

    IElementType lparenTokenType = braceMatcher.getOppositeBraceTokenType(tokenType);
    int lparenthOffset = BraceMatchingUtil.findLeftmostLParen(iterator, lparenTokenType, text, fileType);

    if (lparenthOffset < 0) {
      if (braceMatcher instanceof NontrivialBraceMatcher) {
        for (IElementType t : ((NontrivialBraceMatcher)braceMatcher).getOppositeBraceTokenTypes(tokenType)) {
          if (t == lparenTokenType) continue;
          lparenthOffset = BraceMatchingUtil.findLeftmostLParen(iterator, t, text, fileType);
          if (lparenthOffset >= 0) break;
        }
      }
      if (lparenthOffset < 0) return false;
    }

    iterator = ((EditorEx)editor).getHighlighter().createIterator(lparenthOffset);
    boolean matched = BraceMatchingUtil.matchBrace(text, fileType, iterator, true, true);

    if (!matched) return false;

    EditorModificationUtil.moveCaretRelatively(editor, 1);
    return true;
  }

  @NotNull
  @Override
  public Result charTyped(
    final char c, @NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiFile file) {

    if (handleFile(file)) {
      if (c == '<') {
        handleAfterLParen(editor, file.getFileType(), c);
        return Result.STOP;
      }
      else if (c == '>' && inTextFormat(file, editor)) {
        indentBrace(project, editor, c);
      }
    }

    return Result.CONTINUE;
  }

  private static boolean handleFile(PsiFile file) {
    return file instanceof PbFile || file instanceof PbTextFile;
  }

  // This is a copy of TypedHandler#handleAfterLParen(...), which is unfortunately private.
  private static void handleAfterLParen(Editor editor, FileType fileType, char lparenChar) {
    int offset = editor.getCaretModel().getOffset();
    HighlighterIterator iterator = ((EditorEx)editor).getHighlighter().createIterator(offset);
    boolean atEndOfDocument = offset == editor.getDocument().getTextLength();

    if (!atEndOfDocument) {
      iterator.retreat();
    }
    if (iterator.atEnd()) {
      return;
    }
    BraceMatcher braceMatcher = BraceMatchingUtil.getBraceMatcher(fileType, iterator);
    if (iterator.atEnd()) {
      return;
    }
    IElementType braceTokenType = (IElementType)iterator.getTokenType();
    final CharSequence fileText = editor.getDocument().getCharsSequence();
    if (!braceMatcher.isLBraceToken(iterator, fileText, fileType)) {
      return;
    }

    if (!iterator.atEnd()) {
      iterator.advance();

      if (!iterator.atEnd()
        && !BraceMatchingUtil.isPairedBracesAllowedBeforeTypeInFileType(
        braceTokenType, (IElementType)iterator.getTokenType(), fileType)) {
        return;
      }

      iterator.retreat();
    }

    int lparenOffset =
      BraceMatchingUtil.findLeftmostLParen(iterator, braceTokenType, fileText, fileType);
    if (lparenOffset < 0) {
      lparenOffset = 0;
    }

    iterator = ((EditorEx)editor).getHighlighter().createIterator(lparenOffset);
    boolean matched = BraceMatchingUtil.matchBrace(fileText, fileType, iterator, true, true);

    if (!matched) {
      String text;
      if (lparenChar == '(') {
        text = ")";
      }
      else if (lparenChar == '[') {
        text = "]";
      }
      else if (lparenChar == '<') {
        text = ">";
      }
      else if (lparenChar == '{') {
        text = "}";
      }
      else {
        throw new AssertionError("Unknown char " + lparenChar);
      }
      editor.getDocument().insertString(offset, text);
    }
  }

  /**
   * A copy of TypedHandler#indentBrace(...) which is not public.
   *
   */
  private static void indentBrace(
    @NotNull final Project project, @NotNull final Editor editor, final char braceChar) {
    final int offset = editor.getCaretModel().getOffset() - 1;
    final Document document = editor.getDocument();
    CharSequence chars = document.getCharsSequence();
    if (offset < 0 || chars.charAt(offset) != braceChar) {
      return;
    }

    int spaceStart = CharArrayUtil.shiftBackward(chars, offset - 1, " \t");
    if (spaceStart < 0 || chars.charAt(spaceStart) == '\n' || chars.charAt(spaceStart) == '\r') {
      PsiDocumentManager documentManager = PsiDocumentManager.getInstance(project);
      documentManager.commitDocument(document);

      final PsiFile file = documentManager.getPsiFile(document);
      if (file == null || !file.isWritable()) {
        return;
      }
      PsiElement element = file.findElementAt(offset);
      if (element == null) {
        return;
      }

      EditorHighlighter highlighter = ((EditorEx)editor).getHighlighter();
      HighlighterIterator iterator = highlighter.createIterator(offset);

      final FileType fileType = file.getFileType();
      BraceMatcher braceMatcher = BraceMatchingUtil.getBraceMatcher(fileType, iterator);
      IElementType oppositeTokenType = braceMatcher.getOppositeBraceTokenType((IElementType)iterator.getTokenType());
      boolean rBraceToken = braceMatcher.isRBraceToken(iterator, chars, fileType);
      final boolean isBrace = braceMatcher.isLBraceToken(iterator, chars, fileType) || rBraceToken;
      int lBraceOffset = -1;

      if (CodeInsightSettings.getInstance().REFORMAT_BLOCK_ON_RBRACE
        && rBraceToken
        && braceMatcher.isStructuralBrace(iterator, chars, fileType)
        && offset > 0
        && oppositeTokenType != null) {
        lBraceOffset =
          BraceMatchingUtil.findLeftLParen(
            highlighter.createIterator(offset - 1),
            oppositeTokenType,
            editor.getDocument().getCharsSequence(),
            fileType);
      }
      if (element.getNode() != null && isBrace) {
        final int finalLBraceOffset = lBraceOffset;
        ApplicationManager.getApplication()
                          .runWriteAction(
                            () -> {
                              try {
                                int newOffset;
                                if (finalLBraceOffset != -1) {
                                  RangeMarker marker = document.createRangeMarker(offset, offset + 1);
                                  CodeStyleManager.getInstance(project)
                                                  .reformatRange(file, finalLBraceOffset, offset, true);
                                  newOffset = marker.getStartOffset();
                                  marker.dispose();
                                }
                                else {
                                  newOffset =
                                    CodeStyleManager.getInstance(project).adjustLineIndent(file, offset);
                                }

                                editor.getCaretModel().moveToOffset(newOffset + 1);
                                editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
                                editor.getSelectionModel().removeSelection();
                              }
                              catch (IncorrectOperationException e) {
                                logger.error(e);
                              }
                            });
      }
    }
  }

  static boolean inTextFormat(PsiFile file, Editor editor) {
    if (file instanceof PbTextFile) {
      return true;
    }
    if (file instanceof PbFile) {
      PsiElement element = file.findElementAt(editor.getCaretModel().getOffset());
      if (element == null) {
        return false;
      }
      return PsiTreeUtil.getParentOfType(element, PbAggregateValue.class) != null;
    }
    return false;
  }
}
