package NewtorkLayer.Handler.host.router;

//ROUTER

import NewtorkLayer.Handler.Packet;
import NewtorkLayer.Handler.host.Host;
import NewtorkLayer.Handler.IP;

import java.util.HashMap;
import java.util.LinkedList;

public class Router extends Host implements  Runnable{
    public static HashMap<IP[],IP> routingTable = new HashMap<>();

    public Router(IP ip, IP gateway){
        super(ip,gateway);
    }

    public void addNewRow(IP who,IP[]whom){
        routingTable.put(whom,who);
    }

    public void route(IP producer, IP consumer, Packet packet){
        IP guess = routingTable.get(consumer);
        if (guess == consumer){
            System.out.println("We found route!" + this.ip);
        }
    }
    public void run(){
        while (true){
            if (!packetsIN.isEmpty()){
                System.out.println("Im router ! We got packet to recive" + this.ip);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (!packetsOUT.isEmpty()){
                System.out.println("Im router ! We got packet to send" + this.ip);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
