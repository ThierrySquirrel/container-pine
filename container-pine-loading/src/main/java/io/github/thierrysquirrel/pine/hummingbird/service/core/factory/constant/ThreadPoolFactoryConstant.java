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
package io.github.thierrysquirrel.pine.hummingbird.service.core.factory.constant;

import io.github.thierrysquirrel.jellyfish.thread.pool.ThreadPool;
import io.github.thierrysquirrel.jellyfish.thread.scheduled.one.ThreadScheduledOne;


/**
 * ClassName: ThreadPoolFactoryConstant
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public final class ThreadPoolFactoryConstant {
    private ThreadPoolFactoryConstant() {
    }

    public static final ThreadPool PINE_SERVICE_INIT_THREAD_POOL = new ThreadPool(ThreadPoolSizeConstant.PINE_SERVICE_INIT_CORE_POOL_SIZE);
    public static final ThreadPool PING_SERVICE_BUSINESS_THREAD_POOL = new ThreadPool(ThreadPoolSizeConstant.PINE_SERVICE_BUSINESS_CORE_POOL_SIZE);
    public static final ThreadScheduledOne PINE_SERVICE_HEARTBEAT_THREAD_POOL = new ThreadScheduledOne();
    public static final ThreadPool SYNCHRONOUS_PRODUCERS_THREAD_POOL = new ThreadPool(ThreadPoolSizeConstant.SYNCHRONOUS_PRODUCERS_CORE_POOL_SIZE);
}
