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

package io.github.thierrysquirrel.pine.common.hummingbird.core.client.factory;


import io.github.thierrysquirrel.pine.common.hummingbird.core.client.factory.constant.InetSocketAddressConstant;

import java.net.InetSocketAddress;

/**
 * ClassName: InetSocketAddressFactory
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class InetSocketAddressFactory {
    private InetSocketAddressFactory() {
    }

    public static InetSocketAddress getInetSocketAddress(String url) {
        int i = url.indexOf(InetSocketAddressConstant.SEPARATOR.getValue());
        String host = url.substring(0, i);
        int port = Integer.parseInt(url.substring(i + 1));
        return new InetSocketAddress(host, port);
    }
}
