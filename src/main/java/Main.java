import org.mego.entity.api.ClientTraffics;
import org.mego.impl.ThreeUIAPI;
import org.mego.entity.exceptions.UnsuccessfulHttpException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws UnsuccessfulHttpException, IOException {
        ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder().enableDevMode()
                .setHost("http://188.64.13.237:2053")
                .setLogin("mego")
                .setPassword("z1g1vw89z1g1vw89")
                .enableDevMode()
                .build();

        ClientTraffics erb1utg3 = threeUIAPI.getClientTraffics("erb1utg3");
        System.out.println(erb1utg3.getEmail());


//        Client client = Client.builder()
//                .email("testuser123")
//                .method("aes-256-gcm")
//                .enable(true)
//                .subId("fdsfs432423")
//                .password("APC8He0NRDfQp40BaLujKNmSANoOaJovQeWDVdsf")
//                .build();
//
//        System.out.println(client.getFlow());

//        Client client = new Client(clientSettings, 2);

//        threeUIAPI.createClient(2, client);


//        threeUIAPI.getOnline();

//        List<String> online = threeUIAPI.getOnline();
//
//        System.out.println(online.size());
//
//        for (int i = 0; i < online.size(); i++) {
//            System.out.println(online.get(i));
//        }
    }
}
