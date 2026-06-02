/**
 * Copyright 2026/6/3 ThierrySquirrel
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package io.github.thierrysquirrel.pine.common.hummingbird.core.factory;

import io.github.thierrysquirrel.pine.common.hummingbird.core.constant.MapSizeConstant;
import io.github.thierrysquirrel.pine.common.hummingbird.core.domain.MethodContainer;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.Command;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.Modular;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: EventExecutionContainerFactory
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class EventExecutionContainerFactory {
    private EventExecutionContainerFactory() {
    }

    private static Map<Modular, Map<Command, MethodContainer>> eventExecutionContainer = new ConcurrentHashMap<>();


    public static MethodContainer getMethodContainer(Modular modular, Command command) {
        Map<Command, MethodContainer> commandMethodContainerMap = eventExecutionContainer.get(modular);
        return commandMethodContainerMap.get(command);
    }

    public static void setMethodContainer(Modular modular, Command command, MethodContainer methodContainer) {
        Map<Command, MethodContainer> commandMethodContainerMap = eventExecutionContainer
                .computeIfAbsent(modular, mod -> new ConcurrentHashMap<>(MapSizeConstant.DEFAULT_SIZE.getValue()));
        commandMethodContainerMap.put(command, methodContainer);
    }
}

