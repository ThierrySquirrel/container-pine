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

package io.github.thierrysquirrel.pine.hummingbird.service.core.thread.execution;

import io.github.thierrysquirrel.hummingbird.core.facade.SocketChannelFacade;
import io.github.thierrysquirrel.pine.common.hummingbird.core.factory.ArgsConversionFactory;
import io.github.thierrysquirrel.pine.common.hummingbird.core.factory.execution.EventExecutionContainerFactoryExecution;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.PineRequest;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.PineRequestContext;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.Command;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.Modular;
import io.github.thierrysquirrel.pine.hummingbird.service.core.thread.AbstractPineServiceBusinessThread;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * ClassName: PineServiceBusinessThreadExecution
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class PineServiceBusinessThreadExecution extends AbstractPineServiceBusinessThread {

    public PineServiceBusinessThreadExecution(SocketChannelFacade<PineRequestContext> socketChannelFacade, PineRequestContext pineRequestContext) {
        super(socketChannelFacade, pineRequestContext);
    }

    private static final Logger logger = Logger.getLogger(PineServiceBusinessThreadExecution.class.getName());


    @Override
    protected void pineServiceBusinessExecution(SocketChannelFacade<PineRequestContext> socketChannelFacade, PineRequestContext pineRequestContext) {
        Modular modular = pineRequestContext.getModular();
        Command command = pineRequestContext.getCommand();
        PineRequest pineRequest = pineRequestContext.getPineRequest();
        Object[] args;
        if (pineRequest != null) {
            args = ArgsConversionFactory.getArgs(socketChannelFacade, pineRequestContext, pineRequest.getParameters());
        } else {
            args = ArgsConversionFactory.getArgs(socketChannelFacade, pineRequestContext);
        }

        try {
            EventExecutionContainerFactoryExecution.execution(modular, command, args);
        } catch (Exception e) {
            String logMsg = "BusinessThreadExecution Error";
            logger.log(Level.WARNING, logMsg, e);
        }

    }

}
