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

package io.github.thierrysquirrel.pine.hummingbird.service.core.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: HeartbeatFactory
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class HeartbeatFactory {

    private static final Logger logger = Logger.getLogger(HeartbeatFactory.class.getName());

    private HeartbeatFactory() {
    }

    private static Map<String, List<String>> clientServiceNameMap = new ConcurrentHashMap<>();
    private static Map<String, Integer> heartbeatMap = new ConcurrentHashMap<>();

    private static void putClientServiceName(String clientServiceName, String clientServiceUrl) {
        List<String> clientServiceUrlList = clientServiceNameMap.computeIfAbsent(clientServiceName, k -> new ArrayList<>());
        clientServiceUrlList.add(clientServiceUrl);
    }

    public static void getClientServicePing(String clientServiceName, String clientServiceUrl, int maxNumberHeartbeatTimeouts) {
        Integer heartbeatQuantity = heartbeatMap.get(clientServiceUrl);
        if (Objects.isNull(heartbeatQuantity)) {
            heartbeatQuantity = maxNumberHeartbeatTimeouts;
            putClientServiceName(clientServiceName, clientServiceUrl);

            String logMsg = "Service：" + clientServiceName + clientServiceUrl + " Successfully Registered";
            logger.log(Level.WARNING, logMsg);
        }
        ++heartbeatQuantity;
        if (heartbeatQuantity > maxNumberHeartbeatTimeouts) {
            heartbeatQuantity = maxNumberHeartbeatTimeouts;
        }
        heartbeatMap.put(clientServiceUrl, heartbeatQuantity);

    }


    public static void heartbeatDetection() {
        for (Map.Entry<String, List<String>> entry : clientServiceNameMap.entrySet()) {

            List<String> clientServiceUrlList = entry.getValue();
            List<String> clientServiceUrlListBackups = new ArrayList<>(clientServiceUrlList);
            for (String clientServiceUrl : clientServiceUrlListBackups) {
                Integer heartbeatQuantity = heartbeatMap.get(clientServiceUrl);

                --heartbeatQuantity;

                if (heartbeatQuantity <= 0) {
                    heartbeatMap.remove(clientServiceUrl);

                    String logMsg = "Remove:" + clientServiceUrl;
                    logger.log(Level.WARNING, logMsg);

                    clientServiceUrlList.remove(clientServiceUrl);
                    if (clientServiceUrlList.isEmpty()) {
                        clientServiceNameMap.remove(entry.getKey());
                        break;
                    }
                } else {
                    heartbeatMap.put(clientServiceUrl, heartbeatQuantity);
                }

            }

        }
    }

    public static List<String> getClientServiceUrlList(String clientServiceName) {
        return clientServiceNameMap.get(clientServiceName);
    }
}
