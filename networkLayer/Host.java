package networkLayer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class Host implements Runnable{

    public IP hostIP;
    public LinkedList<IP> hostInterfaces = new LinkedList<>();
    public boolean p_IN;
    public boolean p_OUT;
    public BlockingQueue<String> hostReceiveQueue = new PriorityBlockingQueue<>();
    public static ConcurrentHashMap<IP,BlockingQueue<String>> hostsQueue = new ConcurrentHashMap<>();

    public Host (IP hostIP, LinkedList<IP> hostInterfaces){
        this.hostIP = hostIP;
        this.hostInterfaces = hostInterfaces;
        hostsQueue.put(hostIP,hostReceiveQueue);
    }

    public void sendPacket(IP sender, IP receiver, String datagram){
        for (int i = 0; i < hostInterfaces.size(); i++){
            if (receiver.equals(hostInterfaces.get(i))){
                System.out.println("We found route in interfaces!");
                try{
                    hostsQueue.get(receiver).put(datagram);
                }
                catch (Exception e){
                    System.out.println("We got troubles with sending");
                }
            }
        }
    }

    public void run(){
        while (true){
            try {
                Thread.sleep(1000);
                String datagram = hostsQueue.get(this.hostIP).take();
                System.out.println("We got new datagram - " + datagram);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
