package NewtorkLayer.Handler.host;

//HOST

import NewtorkLayer.Handler.IP;
import NewtorkLayer.Handler.Packet;
import NewtorkLayer.Handler.host.router.Router;

import java.util.LinkedList;

public class Host implements  Runnable{
    public IP ip;
    public LinkedList<IP> hostInterfaces = new LinkedList<>();
    public IP gateway;
    public Router myRouter;
    public LinkedList<Packet> packetsIN = new LinkedList<>();
    public LinkedList<Packet> packetsOUT = new LinkedList<>();

    public Host(IP ip, IP gateway){
        this.ip = ip;
        this.gateway = gateway;
        Router myRouter = new Router(gateway,null);
    }

    public void addIpInterface(IP ip){
        hostInterfaces.add(ip);
    }

    public void addIpInterface(String ip){
        hostInterfaces.add(new IP(ip));
    }

    public void sendPacket(IP producer, IP consumer, String message){
        Packet packet = new Packet(message);
        for (int i = 0 ; i < hostInterfaces.size(); i++){
            if (producer.equals(hostInterfaces.get(i))){
                System.out.println("HOST we found route in local");
                //TODO MAKE LOCAL
            }
            else {
                System.out.println("HOST did not found route in local");
                myRouter.route(consumer,producer,packet);
                myRouter.packetsIN.add(packet);
                System.out.println("HOST send packet to router");
            }
        }
    }


    public void run(){
        while (true){
            if (!packetsIN.isEmpty()){
                System.out.println("We got packet We got packet to recive"  + this.ip);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (!packetsOUT.isEmpty()){
                System.out.println("We got packet We got packet to send " + this.ip);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
