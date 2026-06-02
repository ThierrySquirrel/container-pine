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

package io.github.thierrysquirrel.pine.common.hummingbird.core.client;


import io.github.thierrysquirrel.hummingbird.core.client.init.HummingbirdClientInit;
import io.github.thierrysquirrel.hummingbird.core.client.init.builder.HummingbirdClientInitBuilder;
import io.github.thierrysquirrel.hummingbird.core.facade.SocketChannelFacade;
import io.github.thierrysquirrel.pine.common.hummingbird.coder.PineDecoder;
import io.github.thierrysquirrel.pine.common.hummingbird.coder.PineEncode;
import io.github.thierrysquirrel.pine.common.hummingbird.core.client.container.ClientContainer;
import io.github.thierrysquirrel.pine.common.hummingbird.core.client.factory.constant.IdleStateConstant;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.PineRequestContext;

import java.util.concurrent.CompletableFuture;


/**
 * ClassName: ClientInit
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class ClientInit {
    private String url;

    private HummingbirdClientInit<PineRequestContext> clientInit;
    private SocketChannelFacade<PineRequestContext> connect;

    private CompletableFuture<PineRequestContext> completableFuture = new CompletableFuture<>();

    public ClientInit(String url) {
        this.url = url;
    }

    public SocketChannelFacade<PineRequestContext> init() throws Throwable {
        completableFuture = new CompletableFuture<>();
        if (clientInit == null || connect == null) {
            initConnect();
        }
        if (!connect.isOpen()) {
            initConnect();
        }

        return connect;
    }

    private void initConnect() throws Throwable {
        clientInit = HummingbirdClientInitBuilder.builderHummingbirdClientInit(ClientContainer.getThreadPool(url), url,
                IdleStateConstant.OTHER_TIMEOUT.getValue(), IdleStateConstant.WRITE_TIMEOUT.getValue(),
                new PineDecoder(), new PineEncode(), new ClientHandler(this));
        connect = clientInit.connect();
    }

    public void call(PineRequestContext pineRequestContext) {
        completableFuture.complete(pineRequestContext);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HummingbirdClientInit<PineRequestContext> getClientInit() {
        return clientInit;
    }

    public void setClientInit(HummingbirdClientInit<PineRequestContext> clientInit) {
        this.clientInit = clientInit;
    }

    public SocketChannelFacade<PineRequestContext> getConnect() {
        return connect;
    }

    public void setConnect(SocketChannelFacade<PineRequestContext> connect) {
        this.connect = connect;
    }

    public CompletableFuture<PineRequestContext> getCompletableFuture() {
        return completableFuture;
    }

    public void setCompletableFuture(CompletableFuture<PineRequestContext> completableFuture) {
        this.completableFuture = completableFuture;
    }
}
