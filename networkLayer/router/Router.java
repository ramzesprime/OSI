package networkLayer.router;

import app.ColorManager;
import networkLayer.Datagram;
import networkLayer.Host;
import networkLayer.IP;

import java.util.LinkedList;

public class Router extends Host implements Runnable{

    ColorManager mycolor = new ColorManager(2);

    private final String whoOK = mycolor.get_whoOK();
    private final String whoMID = mycolor.get_whoMID();
    private final String whoSHIT = mycolor.get_whoSHIT();

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
