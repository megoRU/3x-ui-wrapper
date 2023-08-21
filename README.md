# 3x-ui-wrapper

An API wrapper for [3x-ui](https://github.com/MHSanaei/3x-ui) written in Java by @megoRU

### Maven

https://jitpack.io/#megoRU/3x-ui-wrapper

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
    <version>1.0</version>
</dependency>
```

## Examples

### Get all Clients (peers)

```java
public class Main {
    public static void main(String[] args) {
        ThreeUIAPI wgEasyAPI = new ThreeUIAPI.Builder()
                .setLogin("login")
                .setPassword("password")
                .setHost("https://vpn3.megoru.ru")
                .build();
        
        
        
    }
}
```

### Disable Client (peer)

```java
public class Main {
    public static void main(String[] args) {
        WgEasyAPI api = new WgEasyAPI.Builder()
                .password("password")
                .domain("vpn.megoru.ru")
                .build();
        try {
            Status status = api.disableClient("139987fc-266a-45bb-b3c4-3e1d8d2e180c");
        } catch (UnsuccessfulHttpException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

### Create Client (peer)

```java
public class Main {
    public static void main(String[] args) {
        WgEasyAPI api = new WgEasyAPI.Builder()
                .password("password")
                .domain("vpn.megoru.ru")
                .build();
        try {
            Create create = api.createClient("mego");
            System.out.println(create.getCreatedAt()); //2023-01-12T18:20:12
        } catch (UnsuccessfulHttpException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

## Dependencies

1. [Gson](https://github.com/google/gson)
2. [Apache HttpClient](https://github.com/apache/httpcomponents-client)
3. [JSON-java](https://github.com/stleary/JSON-java)
4. [okhttp](https://github.com/square/okhttp)