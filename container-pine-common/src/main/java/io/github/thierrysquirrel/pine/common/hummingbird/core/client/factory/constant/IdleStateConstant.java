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

package io.github.thierrysquirrel.pine.common.hummingbird.core.client.factory.constant;

/**
 * ClassName: IdleStateConstant
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public enum IdleStateConstant {
    /**
     * ReadTimeout
     */
    READ_TIMEOUT(8192),
    /**
     * WriteTimeout
     */
    WRITE_TIMEOUT(8192),
    /**
     * OtherTimeout
     */
    OTHER_TIMEOUT(0);
    private final long value;

    IdleStateConstant(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
