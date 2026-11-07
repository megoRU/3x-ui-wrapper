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
    <version>2.5.0</version>
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
## Изменения 2.5.0

- `updateClient` сохраняет текущие поля клиента и применяет только заданные значения.
  Числовые поля и `enable` в `ClientSettings` теперь nullable: `null` означает не менять,
  явный `0`/`false` означает установить значение. Обновление выполняет GET, затем POST;
  конкурентные изменения одного клиента необходимо сериализовать на стороне приложения.
- `tgId` использует 64-битный тип: `Long` в обновлении и `long` при создании.
  После обновления зависимости пересоберите приложение (изменились сигнатуры модели).
- `setClientEnable(email, enable)` переключает только состояние клиента, не меняя лимиты,
  срок и реквизиты. `setClientsEnable(emails, enable)` возвращает `changed` и `skipped`;
  проверяйте `skipped`, даже если API вернул `success: true`.
- `deleteClient(email, true)` сохраняет статистику; обычный `deleteClient(email)` удаляет её.
  Удаление затрагивает все привязанные inbound. Для одного inbound используйте `detachClient`.
- Бэкапы проверяются по сигнатуре SQLite/PostgreSQL и сохраняются в уникальные временные
  файлы `.db`/`.dump`. Вызывающий код должен переместить или удалить возвращённый файл.
  JSON с ошибкой и HTML больше не сохраняются как успешные бэкапы.
- Ошибка обновления/удаления не гарантирует отсутствие изменений: часть inbound могла
  обработать запрос. Перед повтором проверяйте фактическое состояние панели.

```java
threeUIAPI.setClientEnable("email", false);
threeUIAPI.setClientEnable("email", true);
threeUIAPI.deleteClient("email", true); // сохранить статистику
```