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
package com.intellij.protobuf.ide.highlighter;

import consulo.language.lexer.Lexer;
import consulo.language.ast.IElementType;
import com.intellij.protobuf.lang.psi.ProtoTokenTypes;
import com.intellij.protobuf.lang.util.BuiltInType;
import consulo.language.lexer.DelegateLexer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BuiltInTypeLexer extends DelegateLexer
{

  BuiltInTypeLexer(@NotNull Lexer delegate) {
    super(delegate);
  }

  @Nullable
  @Override
  public IElementType getTokenType() {
    if (BuiltInType.isBuiltInType(getTokenText())) {
      return ProtoTokenTypes.BUILT_IN_TYPE;
    }
    return myDelegate.getTokenType();
  }
}
