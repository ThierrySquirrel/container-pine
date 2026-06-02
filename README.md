# pine

Pine

[中文](./README_zh_CN.md)

Support function:

- [x] Service Cluster
- [x] Heartbeat detection
- [x] Get client registration list

# Service Cluster：

Cluster strategy adopts synchronous client registration information mode  
The first pine server that receives client information, automatically synchronizes to other pine servers

# Heartbeat detection：

Regularly check whether each service survives  
If the service does not survive for a long time, it will be rejected.

# Get client registration list

Common service cluster solutions  
Provide client registration information to extend the domain

## Quick Start

```xml
<!--Adding dependencies to pom. XML-->
<dependency>
    <artifactId>container-pine-loading</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>1.0.0.0-RELEASE</version>
</dependency>
``` 

### configuration file

 ```properties
 ## Java.ClassLoading
Class.forName=io.github.thierrysquirrel.pine.loading.PineLoading
Method.setServiceUrl.String=127.0.0.1:6060 # This is required for service startup
Method.clusterServiceUrl.String=127.0.0.1:6060,127.0.0.1:6061,127.0.0.1:6062 # If you need a cluster, please fill in this way
Method.heartbeatTime.int=4096
Method.maxNumberHeartbeatTimeouts.int=3
 ```

# Start Pine

 ```java

public class Demo {
    public static void main(String[] args) {
        PingInit.init(Demo.class);
    }
}
 ```
 

 
