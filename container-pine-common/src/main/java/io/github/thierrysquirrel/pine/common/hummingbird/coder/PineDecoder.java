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

package io.github.thierrysquirrel.pine.common.hummingbird.coder;


import io.github.thierrysquirrel.hummingbird.core.coder.HummingbirdDecoder;
import io.github.thierrysquirrel.hummingbird.core.facade.ByteBufferFacade;
import io.github.thierrysquirrel.hummingbird.core.facade.SocketChannelFacade;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.PineRequestContext;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.CoderConstant;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.DecoderConstant;
import io.github.thierrysquirrel.pine.common.hummingbird.utils.SerializerUtils;

import java.util.Arrays;

/**
 * ClassName: PineDecoder
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class PineDecoder implements HummingbirdDecoder<PineRequestContext> {

    @Override
    public PineRequestContext decoder(ByteBufferFacade byteBufferFacade, SocketChannelFacade<PineRequestContext> socketChannelFacade) {
        if (byteBufferFacade.length() < DecoderConstant.MINIMUM_DECODING.getValue()) {
            return null;
        }

        byte[] tag = new byte[CoderConstant.PINE.getValue().length];
        byteBufferFacade.make();
        byteBufferFacade.getBytes(tag);

        if (!Arrays.equals(tag, CoderConstant.PINE.getValue())) {
            byteBufferFacade.reset();
            return null;
        }

        int contextSize = byteBufferFacade.getInt();
        if (byteBufferFacade.length() < contextSize) {
            byteBufferFacade.reset();
            return null;
        }

        byte[] data = new byte[contextSize];
        byteBufferFacade.getBytes(data);
        return SerializerUtils.deSerialize(data, PineRequestContext.class);
    }
}
