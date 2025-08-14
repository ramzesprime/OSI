package networkLayer;


import app.ColorManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Host implements Runnable{
    public static ConcurrentHashMap<IP,BlockingQueue<Datagram>> hostsQueue = new ConcurrentHashMap<>();
    private IP gateway;
    private final int TTL = 10; // HOPS

    public IP getHostIP() {
        return hostIP;
    }

    public IP getGateway() {
        return gateway;
    }

    public Host (IP hostIP, LinkedList<IP> hostInterfaces, IP gateway){
        this.hostIP = hostIP;
        this.hostInterfaces = hostInterfaces;
        this.gateway = gateway;
        hostsQueue.put(hostIP,hostReceiveQueue);
    }

    ColorManager mycolor = new ColorManager(1);

    public IP hostIP;
    public LinkedList<IP> hostInterfaces;
    public BlockingQueue<Datagram> hostReceiveQueue = new LinkedBlockingQueue<>();

    private final String whoOK = mycolor.get_whoOK();
    private final String whoMID = mycolor.get_whoMID();
    private final String whoSHIT = mycolor.get_whoSHIT();

    public void sendPacket(IP sender, IP receiver, String data) throws InterruptedException {
        if (!hostInterfaces.isEmpty()){
            for (int i = 0; i < hostInterfaces.size(); i++){
                if (receiver.equals(hostInterfaces.get(i))){
                    System.out.println(whoOK + this.hostIP + "We found route in interfaces!");
                    try{
                        hostsQueue.get(receiver).put(new Datagram(sender,receiver,data,TTL));
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                } else {
                    System.out.println(whoSHIT +"We cannot connect to " + receiver + " there is no such IP int interface ");
                    route(sender, receiver, data);
                    break;
                }
            }
        }
        else {
            System.out.println(whoSHIT +"There is no connected interfaces with " + this.hostIP);
            route(sender, receiver, data);
        }

    }

    public void route(IP sender, IP receiver, String data) throws InterruptedException {
        try {
            System.out.println(whoMID + "Attempting connect to Router + " + this.gateway.toString() );
            hostsQueue.get(gateway).put(new Datagram(sender,receiver,data,TTL));
        }catch (Exception NullPointerException){System.out.println(whoSHIT +"There is no router for " + this.hostIP);}

    }

    public void run(){
        while (true){
            try {
                Thread.sleep(1000);
                Datagram datagram = hostsQueue.get(this.hostIP).take();
                System.out.println(whoOK +"We " + this.hostIP +" got new datagram from - " + datagram.source);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
