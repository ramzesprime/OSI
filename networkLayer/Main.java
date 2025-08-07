package networkLayer;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // IP адреса
        IP ip1 = new IP("1.1.1.1");
        IP ip2 = new IP("2.2.2.2");

        LinkedList<IP> interfaces1 = new LinkedList<>();
        interfaces1.add(ip2);

        LinkedList<IP> interfaces2 = new LinkedList<>();
        interfaces2.add(ip1);

        Host host1 = new Host(ip1, interfaces1);
        Host host2 = new Host(ip2, interfaces2);

        Thread thread1 = new Thread(host1);
        Thread thread2 = new Thread(host2);
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        host1.sendPacket(ip1, ip2, "Hello FUCK YOURSELF from Host1 to Host2");

        host2.sendPacket(ip2, ip1, "Reply FUCK MYSELF from Host2 to Host1");
    }
}
