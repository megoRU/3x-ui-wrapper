import org.mego.entity.api.Client;
import org.mego.entity.api.ClientTraffics;
import org.mego.entity.enums.FlowEnum;
import org.mego.impl.ThreeUIAPI;
import org.mego.entity.exceptions.UnsuccessfulHttpException;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws UnsuccessfulHttpException, IOException, IllegalAccessException {
        ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder().enableDevMode()
                .setHost("http://188.64.13.237:2053")
                .setLogin("mego")
                .setPassword("z1g1vw89z1g1vw89")
                .enableDevMode()
                .build();

//        ClientTraffics erb1utg3 = threeUIAPI.getClientTraffics("erb1utg3");
//        System.out.println(erb1utg3.getDown());

//        Boolean status = threeUIAPI.deleteClient(2, "testuser3333");
//        System.out.println(status);


        List<String> online = threeUIAPI.getOnline();
        System.out.println(online.size());

        Client client = new Client.Builder()
                .email("testuser123")
                .method("aes-256-gcm")
                .enable(false)
                .subId("fdsfs432423")
                .inboundId(2)
                .password("APC8He0NRDfQp40BaLujKNmSANoOaJovQeWDVdsf")
                .build();

//        Boolean b = threeUIAPI.addClient(client);
//        System.out.println("b: " + b);
//
//        Boolean b1 = threeUIAPI.updateClient(client);
//        System.out.println("b1: " + b1);


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
