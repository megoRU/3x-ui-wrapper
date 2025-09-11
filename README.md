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
    <version>2.0.3</version>
</dependency>
```

---

## 🚀 Examples

### ➖ Delete config

```java
ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder()
        .setHost("http://your-host:port")
        .setLogin("admin")
        .setPassword("admin")
        .enableDevMode()
        .build();

boolean success = threeUIAPI.deleteClient(1, "uuid-or-email");
System.out.println("Deleted: " + success);
```

### ➕ Create config

```java
ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder()
        .setHost("http://your-host:port")
        .setLogin("admin")
        .setPassword("admin")
        .enableDevMode()
        .build();

Client client = new Client.Builder()
        .email("testuser123")
        .method("aes-256-gcm")
        .enable(true)
        .subId("your-sub-id")
        .inboundId(1)
        .password("secure-password")
        .build();

boolean success = threeUIAPI.addClient(client);
System.out.println("Created: " + success);
```

---

## 📄 License

This wrapper is distributed under the MIT License.
