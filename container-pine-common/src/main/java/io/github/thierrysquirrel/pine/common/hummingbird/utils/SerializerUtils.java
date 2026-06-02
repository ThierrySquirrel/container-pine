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

package io.github.thierrysquirrel.pine.common.hummingbird.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: SerializerUtils
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class SerializerUtils {
    private SerializerUtils() {
    }

    private static final Logger logger = Logger.getLogger(SerializerUtils.class.getName());


    public static <T> byte[] serialize(T object) {
        ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

        byte[] bytes = null;
        try {
            bytes = objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            String logMsg = "serialize Error";
            logger.log(Level.WARNING, logMsg, e);
        }

        return bytes;
    }

    public static <T> T deSerialize(byte[] bytes, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());
        T domain = null;
        try {
            domain = objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            String logMsg = "deSerialize Error";
            logger.log(Level.WARNING, logMsg, e);
        }
        return domain;
    }
}
