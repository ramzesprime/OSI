package app;

import networkLayer.Host;
import networkLayer.IP;
import networkLayer.router.Router;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // IP адреса
        IP ip1 = new IP("host1");
        IP ip2 = new IP("host2");
        IP ip3 = new IP("host3");
        IP router = new IP("router");

        LinkedList<IP> interfacesRouter = new LinkedList<>();
        interfacesRouter.add(new IP("@22"));


        LinkedList<IP> interfaces1 = new LinkedList<>();
        interfaces1.add(ip3);

        LinkedList<IP> interfaces2 = new LinkedList<>();
        interfaces1.add(ip3);

        Host host1 = new Host(ip1, interfaces1,router);
        Host host2 = new Host(ip2, interfaces2,router);
        Router router1 = new Router(router, interfacesRouter,null);

        Thread thread1 = new Thread(host1);
        thread1.start();

        Thread thread2 = new Thread(host2);
        thread2.start();

        Thread thread3 = new Thread(router1);
        thread3.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        host1.sendPacket(ip1, ip2, "hello");

    }
}
