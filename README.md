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
    <version>2.2.8</version>
</dependency>
```

---

## 🚀 Examples

### ➖ Delete client config

```java
ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder()
        .setHost("https://your-host:port")
        .setToken("TestTokenPhQgU9Cr84AqJ9f0hyPE")
        .enableDevMode()
        .build();

boolean deleted = threeUIAPI.deleteClient("email");

System.out.println("Client deleted: " + deleted);
```

### ➕ Create client config

```java
ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder()
        .setHost("https://your-host:port")
        .setToken("TestTokenPhQgU9Cr84AqJ9f0hyPE")
        .enableDevMode()
        .build();

ClientBuilder clientBuilder = new ClientBuilder();
clientBuilder.setEmail("TestEmail");
clientBuilder.setId(UUID.randomUUID().toString());
clientBuilder.setLimitIp(3);
clientBuilder.setFlow(FlowEnum.XLTS_RPRX_VISION);
clientBuilder.setEnable(true);

Client client = new Client();
client.setClient(clientBuilder);
client.setInboundId(1);

boolean created = threeUIAPI.addClient(client);

System.out.println("Client created: " + created);
```

---

## 📄 License

This wrapper is distributed under the MIT License.
