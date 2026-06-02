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

package io.github.thierrysquirrel.pine.hummingbird.service.core.factory.execution;

import io.github.thierrysquirrel.pine.common.hummingbird.core.client.factory.constant.SeparatorConstant;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.PineRequestContext;
import io.github.thierrysquirrel.pine.hummingbird.service.core.factory.constant.ThreadPoolFactoryConstant;
import io.github.thierrysquirrel.pine.hummingbird.service.core.thread.execution.SynchronousProducersThreadExecution;

/**
 * ClassName: SynchronousProducersFactoryExecution
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class SynchronousProducersFactoryExecution {
    private SynchronousProducersFactoryExecution() {
    }

    public static void execution(String clusterServiceUrl, String serviceUrl, PineRequestContext synchronousProducers) {
        String[] split = clusterServiceUrl.split(SeparatorConstant.URL_SEPARATOR.getValue());
        for (String url : split) {
            if (url.equals(serviceUrl)) {
                continue;
            }
            SynchronousProducersThreadExecution synchronousProducersThreadExecution = new SynchronousProducersThreadExecution(url, synchronousProducers);
            ThreadPoolFactoryConstant.SYNCHRONOUS_PRODUCERS_THREAD_POOL.execute(synchronousProducersThreadExecution);
        }
    }
}
