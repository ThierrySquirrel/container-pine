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

package io.github.thierrysquirrel.pine.hummingbird.service.core.thread;

import io.github.thierrysquirrel.hummingbird.core.facade.SocketChannelFacade;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.PineRequestContext;


/**
 * ClassName: AbstractPineServiceBusinessThread
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public abstract class AbstractPineServiceBusinessThread implements Runnable {
    private SocketChannelFacade<PineRequestContext> socketChannelFacade;
    private PineRequestContext pineRequestContext;

    protected AbstractPineServiceBusinessThread(SocketChannelFacade<PineRequestContext> socketChannelFacade, PineRequestContext pineRequestContext) {
        this.socketChannelFacade = socketChannelFacade;
        this.pineRequestContext = pineRequestContext;
    }

    /**
     * pineServiceBusinessExecution
     *
     * @param socketChannelFacade SocketChannelFacade
     * @param pineRequestContext  PineRequestContext
     */
    protected abstract void pineServiceBusinessExecution(SocketChannelFacade<PineRequestContext> socketChannelFacade, PineRequestContext pineRequestContext);

    @Override
    public void run() {
        pineServiceBusinessExecution(this.socketChannelFacade, this.pineRequestContext);
    }

    public SocketChannelFacade<PineRequestContext> getSocketChannelFacade() {
        return socketChannelFacade;
    }

    public void setSocketChannelFacade(SocketChannelFacade<PineRequestContext> socketChannelFacade) {
        this.socketChannelFacade = socketChannelFacade;
    }

    public PineRequestContext getPineRequestContext() {
        return pineRequestContext;
    }

    public void setPineRequestContext(PineRequestContext pineRequestContext) {
        this.pineRequestContext = pineRequestContext;
    }

    @Override
    public String toString() {
        return "AbstractPineServiceBusinessThread{" +
                "socketChannelFacade=" + socketChannelFacade +
                ", pineRequestContext=" + pineRequestContext +
                '}';
    }
}
