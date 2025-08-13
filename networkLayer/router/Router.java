package networkLayer.router;

import networkLayer.Datagram;
import networkLayer.Host;
import networkLayer.IP;

import java.util.LinkedList;

public class Router extends Host implements Runnable{

    private final String whoOK = "\u001B[32m" + "//ROUTER//" + "\u001B[32m";
    private final String whoMID = "\u001B[33m" + "//ROUTER//" + "\u001B[33m";
    private final String whoSHIT = "\u001B[31m" + "//ROUTER//" + "\u001B[31m";

    public Router (IP hostIP, LinkedList<IP> hostInterfaces, IP gateway){
        super(hostIP,hostInterfaces,gateway);
    }

    public void run(){
        while (true){
            try {
                Thread.sleep(1000);
                Datagram datagram = hostsQueue.get(this.hostIP).take();
                System.out.println(whoOK +"Router - " + this.hostIP + " gets new datagram " + datagram.source.toString());
                sendPacket(datagram.source, datagram.destination, datagram.data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
