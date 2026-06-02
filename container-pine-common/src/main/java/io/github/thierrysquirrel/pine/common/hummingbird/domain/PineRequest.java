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

package io.github.thierrysquirrel.pine.common.hummingbird.domain;

import java.util.Arrays;

/**
 * ClassName: PineRequest
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class PineRequest {
    private Object[] parameters;

    private Object[] rpcParameters;

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Object[] getRpcParameters() {
        return rpcParameters;
    }

    public void setRpcParameters(Object[] rpcParameters) {
        this.rpcParameters = rpcParameters;
    }

    @Override
    public String toString() {
        return "PineRequest{" +
                "parameters=" + Arrays.toString(parameters) +
                ", rpcParameters=" + Arrays.toString(rpcParameters) +
                '}';
    }
}
