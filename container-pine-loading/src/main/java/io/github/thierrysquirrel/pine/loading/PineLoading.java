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

package io.github.thierrysquirrel.pine.loading;

import io.github.thierrysquirrel.container.scanner.annotation.ScannerPackage;
import io.github.thierrysquirrel.container.scanner.registration.InterfaceManualRegistration;
import io.github.thierrysquirrel.pine.loading.constant.PineLoadingConstant;
import io.github.thierrysquirrel.pine.registration.PineRegistration;

import java.util.List;
import java.util.Map;

/**
 * ClassName: PineLoading
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
@ScannerPackage(packageName = "io.github.thierrysquirrel.pine.hummingbird.service.event")
public class PineLoading implements InterfaceManualRegistration {

    /**
     * ServiceUrl
     */
    private String serviceUrl = PineLoadingConstant.DEFAULT_SERVICE_URL;
    /**
     * ClusterServiceUrl
     */
    private String clusterServiceUrl;
    /**
     * Heartbeat interval
     */
    private int heartbeatTime = PineLoadingConstant.DEFAULT_HEARTBEAT_TIME;
    /**
     * Reject the service after how many times the service is not received
     */
    private int maxNumberHeartbeatTimeouts = PineLoadingConstant.DEFAULT_MAX_NUMBER_HEARTBEAT_TIMEOUTS;

    @Override
    public void scannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        PineRegistration.pineRegistrationScannerAll(scannerClassList, registrationMap);
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getClusterServiceUrl() {
        return clusterServiceUrl;
    }

    public void setClusterServiceUrl(String clusterServiceUrl) {
        this.clusterServiceUrl = clusterServiceUrl;
    }

    public int getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(int heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    public int getMaxNumberHeartbeatTimeouts() {
        return maxNumberHeartbeatTimeouts;
    }

    public void setMaxNumberHeartbeatTimeouts(int maxNumberHeartbeatTimeouts) {
        this.maxNumberHeartbeatTimeouts = maxNumberHeartbeatTimeouts;
    }

    @Override
    public String toString() {
        return "PineLoading{" +
                "serviceUrl='" + serviceUrl + '\'' +
                ", clusterServiceUrl='" + clusterServiceUrl + '\'' +
                ", heartbeatTime=" + heartbeatTime +
                ", maxNumberHeartbeatTimeouts=" + maxNumberHeartbeatTimeouts +
                '}';
    }
}
