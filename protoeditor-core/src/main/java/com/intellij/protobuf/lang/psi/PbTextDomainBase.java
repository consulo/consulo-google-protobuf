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
package com.intellij.protobuf.lang.psi;

import consulo.language.ast.ASTNode;
import consulo.language.ast.TokenSet;

interface PbTextDomainBase extends PbTextElement {
  TokenSet DOMAIN_COMPONENTS =
      TokenSet.create(ProtoTokenTypes.IDENTIFIER_LITERAL, ProtoTokenTypes.DOT);

  /** Returns the domain name as a string, without any whitespace between tokens. */
  default String getDomainName() {
    StringBuilder name = new StringBuilder();
    for (ASTNode component : getNode().getChildren(DOMAIN_COMPONENTS)) {
      name.append(component.getText());
    }
    return name.toString();
  }
}
