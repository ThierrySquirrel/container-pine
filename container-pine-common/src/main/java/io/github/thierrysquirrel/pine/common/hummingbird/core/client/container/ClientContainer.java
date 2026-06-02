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

package io.github.thierrysquirrel.pine.common.hummingbird.core.client.container;

import io.github.thierrysquirrel.jellyfish.thread.pool.ThreadPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: ClientContainer
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class ClientContainer {
    private static final Map<String, ThreadLocal<ThreadPool>> EVENT_LOOP_GROUP_MAP = new ConcurrentHashMap<>();

    private ClientContainer() {
    }

    public static ThreadPool getThreadPool(String url) {
        ThreadLocal<ThreadPool> threadLocal = EVENT_LOOP_GROUP_MAP.computeIfAbsent(url, key -> new ThreadLocal<>());
        ThreadPool threadPool = threadLocal.get();
        if (threadPool == null) {
            threadPool = new ThreadPool(1);
            threadLocal.set(threadPool);
        }
        return threadPool;
    }
}
