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

package io.github.thierrysquirrel.pine.hummingbird.service.event;

import io.github.thierrysquirrel.container.scanner.annotation.Set;
import io.github.thierrysquirrel.hummingbird.core.facade.SocketChannelFacade;
import io.github.thierrysquirrel.pine.annotation.PineServiceEvent;
import io.github.thierrysquirrel.pine.annotation.PineServiceHandler;
import io.github.thierrysquirrel.pine.common.hummingbird.core.client.factory.PineRequestContextFactory;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.PineRequestContext;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.Command;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.Modular;
import io.github.thierrysquirrel.pine.loading.PineLoading;
import io.github.thierrysquirrel.pine.hummingbird.service.core.factory.HeartbeatFactory;


/**
 * ClassName: SynchronizingHandler
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
@PineServiceHandler
public class SynchronizingHandler {
    @Set
    private PineLoading pineLoading;

    @PineServiceEvent(modular = Modular.SYNCHRONIZING, command = Command.SYNCHRONOUS_PRODUCERS)
    public void synchronousProducers(SocketChannelFacade<PineRequestContext> socketChannelFacade, PineRequestContext pineRequestContext, String clientServiceName, String clientServiceUrl) throws Throwable {
        HeartbeatFactory.getClientServicePing(clientServiceName, clientServiceUrl, pineLoading.getMaxNumberHeartbeatTimeouts());
        PineRequestContext synchronousResponse = PineRequestContextFactory.createSynchronousResponse(clientServiceUrl);
        socketChannelFacade.sendMessage(synchronousResponse);
    }
}
