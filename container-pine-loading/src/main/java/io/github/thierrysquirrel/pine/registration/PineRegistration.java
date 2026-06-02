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

package io.github.thierrysquirrel.pine.registration;

import io.github.thierrysquirrel.pine.annotation.PineServiceEvent;
import io.github.thierrysquirrel.pine.annotation.PineServiceHandler;
import io.github.thierrysquirrel.pine.common.hummingbird.core.domain.MethodContainer;
import io.github.thierrysquirrel.pine.common.hummingbird.core.factory.EventExecutionContainerFactory;
import io.github.thierrysquirrel.pine.common.hummingbird.core.factory.MethodContainerFactory;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.Command;
import io.github.thierrysquirrel.pine.common.hummingbird.domain.constant.Modular;
import io.github.thierrysquirrel.pine.loading.PineLoading;
import io.github.thierrysquirrel.pine.hummingbird.service.core.factory.constant.ThreadPoolFactoryConstant;
import io.github.thierrysquirrel.pine.hummingbird.service.core.thread.execution.PineServiceHeartbeatThreadExecution;
import io.github.thierrysquirrel.pine.hummingbird.service.core.thread.execution.PineServiceInitThreadExecution;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: PineRegistration
 * Description:
 * date: 2026/6/3
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class PineRegistration {
    private PineRegistration() {
    }

    private static final Logger logger = Logger.getLogger(PineRegistration.class.getName());


    public static void pineRegistrationScannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        PineLoading pineLoading = (PineLoading) registrationMap.get(PineLoading.class);
        for (Class<?> thisClass : scannerClassList) {
            PineServiceHandler pineServiceHandler = thisClass.getAnnotation(PineServiceHandler.class);
            if (Objects.isNull(pineServiceHandler)) {
                continue;
            }

            Object pineHandler = newInstance(thisClass);
            registrationMap.put(thisClass, pineHandler);
            for (Method method : thisClass.getMethods()) {
                PineServiceEvent pineServiceEvent = method.getAnnotation(PineServiceEvent.class);
                if (Objects.isNull(pineServiceEvent)) {
                    continue;
                }
                Modular modular = pineServiceEvent.modular();
                Command command = pineServiceEvent.command();
                MethodContainer methodContainer = MethodContainerFactory.getMethodContainer(method, pineHandler);
                EventExecutionContainerFactory.setMethodContainer(modular, command, methodContainer);
            }

        }


        int heartbeatTime = pineLoading.getHeartbeatTime();
        PineServiceHeartbeatThreadExecution pineServiceHeartbeatThreadExecution = new PineServiceHeartbeatThreadExecution();
        ThreadPoolFactoryConstant.PINE_SERVICE_HEARTBEAT_THREAD_POOL.execute(pineServiceHeartbeatThreadExecution, heartbeatTime);

        PineServiceInitThreadExecution pineServiceInitThreadExecution = new PineServiceInitThreadExecution(pineLoading.getServiceUrl());
        ThreadPoolFactoryConstant.PINE_SERVICE_INIT_THREAD_POOL.execute(pineServiceInitThreadExecution);
    }

    private static Object newInstance(Class<?> thisClass) {
        Object object = null;
        try {
            object = thisClass.getDeclaredConstructor().newInstance();
        } catch (Throwable e) {
            String logMsg = "newInstance Error";
            logger.log(Level.WARNING, logMsg, e);
        }
        return object;
    }
}
