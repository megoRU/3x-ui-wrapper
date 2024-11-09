# 3x-ui-wrapper

An API wrapper for [3x-ui-wrapper](https://github.com/MHSanaei/3x-ui) written in Java by @megoRU

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
   <version>1.7.7</version>
</dependency>
```
## Preparing to upgrade 1.6.1 from previous versions

```html
inboundId now you need to specify it in the Client. 
If not specified, an exception will be thrown
```

## Examples

### Delete config

```java
public class Main {
    public static void main(String[] args) {
        ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder().enableDevMode()
                .setHost("http://45.15.158.18:2053")
                .setLogin("admin")
                .setPassword("admin")
                .enableDevMode()
                .build();

        Boolean b = threeUIAPI.deleteClient(1, "af444bd8-9b9b-46c4-9fcd-971153852d89"); //or email 432fdgd
        System.out.println(b);
    }
}
```

### Create config
```java
public class Main {
    public static void main(String[] args) {
        ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder().enableDevMode()
                .setHost("http://45.15.158.18:2053")
                .setLogin("admin")
                .setPassword("admin")
                .enableDevMode()
                .build();

        Client client = new Client.Builder()
                .email("testuser123")
                .method("aes-256-gcm")
                .enable(true)
                .subId("fdsfs432423")
                .inboundId(1)
                .password("APC8He0NRDfQp40BaLujKNmSANoOaJovQeWDVdsf")
                .build();

        Boolean status = threeUIAPI.addClient(client);
        System.out.println("status: " + status); //status:  true
    }
}
```
