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
package com.intellij.protobuf.lang.stub.type;

import consulo.index.io.StringRef;
import consulo.language.Language;
import consulo.language.psi.stub.*;
import consulo.language.psi.util.QualifiedName;
import com.intellij.protobuf.lang.psi.PbOneofDefinition;
import com.intellij.protobuf.lang.psi.impl.PbOneofDefinitionImpl;
import com.intellij.protobuf.lang.stub.PbOneofDefinitionStub;
import com.intellij.protobuf.lang.stub.index.QualifiedNameIndex;
import com.intellij.protobuf.lang.stub.index.ShortNameIndex;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PbOneofDefinitionType
    extends IStubElementType<PbOneofDefinitionStub, PbOneofDefinition>
{

  PbOneofDefinitionType(String debugName, Language language) {
    super(debugName, language);
  }

  @Override
  public PbOneofDefinition createPsi(@NotNull PbOneofDefinitionStub stub) {
    return new PbOneofDefinitionImpl(stub, this);
  }

  @NotNull
  @Override
  public PbOneofDefinitionStub createStub(
      @NotNull PbOneofDefinition psi, StubElement parentStub) {
    return new PbOneofDefinitionStub(parentStub, this, psi.getName());
  }

  @NotNull
  @Override
  public String getExternalId() {
    return "protobuf.oneof";
  }

  @Override
  public void serialize(@NotNull PbOneofDefinitionStub stub, @NotNull StubOutputStream dataStream)
      throws IOException {
    dataStream.writeName(stub.getName());
  }

  @NotNull
  @Override
  public PbOneofDefinitionStub deserialize(
		  @NotNull StubInputStream dataStream, StubElement parentStub)
      throws IOException {
    String name = null;
    StringRef nameRef = dataStream.readName();
    if (nameRef != null) {
      name = nameRef.getString();
    }
    return new PbOneofDefinitionStub(parentStub, this, name);
  }

  @Override
  public void indexStub(@NotNull PbOneofDefinitionStub stub, @NotNull IndexSink sink) {
    String name = stub.getName();
    if (name != null) {
      sink.occurrence(ShortNameIndex.KEY, name);
    }
    QualifiedName qualifiedName = stub.getQualifiedName();
    if (qualifiedName != null) {
      sink.occurrence(QualifiedNameIndex.KEY, qualifiedName.toString());
    }
  }
}
