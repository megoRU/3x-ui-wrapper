# 3x-ui-wrapper

[![JitPack](https://jitpack.io/v/megoRU/3x-ui-wrapper.svg)](https://jitpack.io/#megoRU/3x-ui-wrapper)

Java API wrapper for [3x-ui](https://github.com/MHSanaei/3x-ui), developed by [@megoRU](https://github.com/megoRU).
Supports full interaction with the 3x-ui panel via HTTP API.

---

## 📦 Installation (Maven)

Add JitPack repository and dependency:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.megoRU</groupId>
    <artifactId>3x-ui-wrapper</artifactId>
    <version>2.2.4</version>
</dependency>
```

---

## 🚀 Examples

### ➖ Delete config

```java
ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder()
        .setHost("https://your-host:port")
        .setToken("TestTokenPhQgU9Cr84AqJ9f0hyPE")
        .enableDevMode()
        .build();

boolean success = threeUIAPI.deleteClient("uuid-or-email");
System.out.println("Deleted: " + success);
```

### ➕ Create config

```java
ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder()
        .setHost("https://your-host:port")
        .setToken("TestTokenPhQgU9Cr84AqJ9f0hyPE")
        .enableDevMode()
        .build();

AddClient.ClientBuilder addClientBuilder = new AddClient.ClientBuilder();
        addClientBuilder.setEmail("TestEmail");
        addClientBuilder.setId(UUID.randomUUID().toString());
        addClientBuilder.setLimitIp(3);
        addClientBuilder.setFlow(FlowEnum.XLTS_RPRX_VISION);
        addClientBuilder.setEnable(true);

AddClient addClient = new AddClient(addClientBuilder, new int[]{1});

boolean success = threeUIAPI.addClient(addClient);
System.out.println("Created: " + success);
```

---

## 📄 License

This wrapper is distributed under the MIT License.
