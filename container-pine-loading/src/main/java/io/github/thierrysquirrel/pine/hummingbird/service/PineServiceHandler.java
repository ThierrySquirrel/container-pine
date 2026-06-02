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

package io.github.thierrysquirrel.pine.hummingbird.service;

import io.github.thierrysquirrel.hummingbird.core.facade.SocketChannelFacade;
import io.github.thierrysquirrel.hummingbird.core.handler.HummingbirdHandler;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.PineRequestContext;
import io.github.thierrysquirrel.pine.hummingbird.service.core.factory.constant.ThreadPoolFactoryConstant;
import io.github.thierrysquirrel.pine.hummingbird.service.core.thread.execution.PineServiceBusinessThreadExecution;

import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: PineServiceHandler
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class PineServiceHandler implements HummingbirdHandler<PineRequestContext> {
    private static final Logger logger = Logger.getLogger(PineServiceHandler.class.getName());

    @Override
    public void channelMessage(SocketChannelFacade<PineRequestContext> socketChannelFacade, PineRequestContext pineRequestContext) {
        PineServiceBusinessThreadExecution pineServiceBusinessThreadExecution = new PineServiceBusinessThreadExecution(socketChannelFacade, pineRequestContext);
        ThreadPoolFactoryConstant.PING_SERVICE_BUSINESS_THREAD_POOL.execute(pineServiceBusinessThreadExecution);
    }

    @Override
    public void channelTimeout(SocketChannelFacade<PineRequestContext> socketChannelFacade) {
        socketChannelFacade.close();
        logger.log(Level.INFO, "channelTimeout");
    }

    @Override
    public void channelClose(SocketAddress socketAddress, SocketAddress socketAddress1) {
        logger.log(Level.INFO, "channelClose");
    }
}
