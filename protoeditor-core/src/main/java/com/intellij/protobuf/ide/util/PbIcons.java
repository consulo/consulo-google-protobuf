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
package com.intellij.protobuf.ide.util;

import consulo.google.protobuf.icon.ProtobufIconGroup;
import consulo.platform.base.icon.PlatformIconGroup;
import consulo.ui.image.Image;

public interface PbIcons {

  Image ENUM = PlatformIconGroup.nodesEnum();
  Image ENUM_VALUE = PlatformIconGroup.nodesField();
  Image EXTEND = PlatformIconGroup.actionsExport();
  Image FIELD = PlatformIconGroup.nodesField();
  Image FILE = ProtobufIconGroup.protofile();
  Image GROUP_FIELD = PlatformIconGroup.nodesAnonymousclass();
  Image MESSAGE = ProtobufIconGroup.protomessage();
  Image ONEOF = PlatformIconGroup.nodesClass();
  Image PACKAGE = PlatformIconGroup.nodesPackage();
  Image SERVICE = PlatformIconGroup.nodesInterface();
  Image SERVICE_METHOD = PlatformIconGroup.nodesMethod();

  // TODO(volkman): find a better icon.
  Image TEXT_FILE = ProtobufIconGroup.protofile();
}
