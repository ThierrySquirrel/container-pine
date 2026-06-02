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

import io.github.thierrysquirrel.hummingbird.core.server.init.HummingbirdServerInit;
import io.github.thierrysquirrel.pine.common.hummingbird.coder.PineDecoder;
import io.github.thierrysquirrel.pine.common.hummingbird.coder.PineEncode;
import io.github.thierrysquirrel.pine.common.hummingbird.core.client.factory.constant.IdleStateConstant;
import io.github.thierrysquirrel.pine.hummingbird.service.PineServiceHandler;
import io.github.thierrysquirrel.pine.hummingbird.service.core.thread.AbstractPineServiceInitThread;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * ClassName: PineServiceInitThreadExecution
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class PineServiceInitThreadExecution extends AbstractPineServiceInitThread {

    public PineServiceInitThreadExecution(String pineServiceUrl) {
        super(pineServiceUrl);
    }

    private static final Logger logger = Logger.getLogger(PineServiceInitThreadExecution.class.getName());


    @Override
    protected void pineServiceInit(String pineServiceUrl) {

        try {
            HummingbirdServerInit.init(pineServiceUrl, IdleStateConstant.READ_TIMEOUT.getValue(), IdleStateConstant.OTHER_TIMEOUT.getValue(),
                    new PineDecoder(), new PineEncode(), new PineServiceHandler());
        } catch (IOException e) {
            String logMsg = "SparrowServerInit Error";
            logger.log(Level.WARNING, logMsg, e);
        }
    }
}
