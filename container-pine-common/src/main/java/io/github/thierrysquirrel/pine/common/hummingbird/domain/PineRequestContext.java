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

import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.Command;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.Modular;

import java.util.Map;

/**
 * ClassName: PineRequestContext
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class PineRequestContext {
    private Command command;
    private Modular modular;
    private Map<String, String> attachment;
    private PineRequest pineRequest;
    private PineResponse pineResponse;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Modular getModular() {
        return modular;
    }

    public void setModular(Modular modular) {
        this.modular = modular;
    }

    public Map<String, String> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, String> attachment) {
        this.attachment = attachment;
    }

    public PineRequest getPineRequest() {
        return pineRequest;
    }

    public void setPineRequest(PineRequest pineRequest) {
        this.pineRequest = pineRequest;
    }

    public PineResponse getPineResponse() {
        return pineResponse;
    }

    public void setPineResponse(PineResponse pineResponse) {
        this.pineResponse = pineResponse;
    }

    @Override
    public String toString() {
        return "PineRequestContext{" +
                "command=" + command +
                ", modular=" + modular +
                ", attachment=" + attachment +
                ", pineRequest=" + pineRequest +
                ", pineResponse=" + pineResponse +
                '}';
    }
}
