# 3x-ui-wrapper

[![JitPack](https://jitpack.io/v/megoRU/3x-ui-wrapper.svg)](https://jitpack.io/#megoRU/3x-ui-wrapper)

Java API-обёртка для панели [3x-ui](https://github.com/MHSanaei/3x-ui), разработанная [@megoRU](https://github.com/megoRU).
Позволяет полноценно взаимодействовать с панелью 3x-ui через HTTP API.

---

## 📦 Установка (Maven)

Добавьте репозиторий JitPack и зависимость:

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
    <version>2.4.0</version>
</dependency>
```

---

## 🚀 Примеры использования

### ➖ Удаление конфигурации клиента

```java
ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder()
        .setHost("https://your-host:port")
        .setToken("TestTokenPhQgU9Cr84AqJ9f0hyPE")
        .enableDevMode()
        .build();

boolean deleted = threeUIAPI.deleteClient("email");

System.out.println("Клиент удалён: " + deleted);
```

### ➕ Создание конфигурации клиента

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

System.out.println("Клиент создан: " + created);
```

---

## 📄 Лицензия

Данная библиотека распространяется по лицензии MIT.