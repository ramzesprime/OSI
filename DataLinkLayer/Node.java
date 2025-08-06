package DataLinkLayer;

import java.util.LinkedList;

public class Node {
    MAC mac;
    LinkedList<MAC> interfaces;

    public  Node(MAC mac, LinkedList<MAC> interfaces){
        this.mac = mac;
        this.interfaces = interfaces;
    }

    public  Node(String mac, LinkedList<MAC> interfaces){
        this.mac = new MAC(mac);
        this.interfaces = interfaces;
    }

    public void searchLocalRoute(MAC consumer, MAC producer, String message){
        for (int i = 0 ; i < interfaces.size(); i++){
            if (consumer.equals(producer)){
                System.out.println("NODE find local route");
                sendFrame(new Frame(message));
            }
            else{
                System.out.println("NODE did not found route, calling NET layer");
                //NOTIFY NET LAYER
            }
        }
    }

    public static void ARPprotocol(){
        //NOTIFY ALL THREADS;
    }

    public void sendFrame(Frame frame){
        //NOTIFY ANOTHER NODE THREAD
    }
}
