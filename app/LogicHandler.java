package app;

import networkLayer.Host;
import networkLayer.IP;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class LogicHandler {

    private LinkedList<String> allIp = new LinkedList<>();
    private LinkedList<String> allRoutes = new LinkedList<>();
    private LinkedList<Host> allHosts = new LinkedList<>();

    Host myHostObject;

    public void addIp(IP ip){
        allIp.add(ip.toString());
    }

    public void addIp(String ip){
        allIp.add(ip);
    }

    private void addIps(){
        System.out.println("Enter available IP's");
        Scanner sc = new Scanner(System.in);
        allIp.add(sc.nextLine());
        Main.menu();
    }

    private Scanner sc = new Scanner(System.in);

    private void addNewHost() {

        String ip;
        LinkedList<IP> interfaces = new LinkedList<>();
        String gateway = null;

        if (allIp.isEmpty() || allRoutes.isEmpty()) {
            System.out.println("There is no IPs and Routers");
            Main.menu();
            return;
        }

        System.out.println("Choose an IP adress for your host\n");
        int num = 0;
        for (String s : allIp) {
            num++;
            System.out.println(num + ") " + s + " ");
        }
        String input = sc.nextLine();
        int choiceIP = Integer.parseInt(input) - 1;
        ip = allIp.get(choiceIP);

        System.out.println("Choose interfaces for your host\n");
        num = 0;
        for (String s : allIp) {
            num++;
            System.out.println(num + ") " + s + " ");
        }
        input = sc.nextLine();
        int choiceInterface = Integer.parseInt(input) - 1;
        if (Objects.equals(allIp.get(choiceInterface), ip)) {
            System.out.println("Host got no interfaces");
        } else {
            interfaces.add(new IP(allIp.get(choiceInterface)));
        }

        System.out.println("Choose gateway for your host\n");
        num = 0;
        for (String s : allRoutes) {
            num++;
            System.out.println(num + ") " + s + " ");
        }
        input = sc.nextLine();
        int choiceGateway = Integer.parseInt(input) - 1;
        if (Objects.equals(allRoutes.get(choiceGateway), ip)) {
            System.out.println("Host got no router");
        } else {
            gateway = allRoutes.get(choiceGateway);
        }

        if (gateway != null) {
            allHosts.add(new Host(new IP(ip), interfaces, new IP(gateway)));
        } else {
            System.out.println("Host not created because gateway is missing");
        }

        Main.menu();
    }

    private void sendDatagram() {
        if (allHosts.size() < 2) {
            System.out.println("Need at least 2 hosts to send datagram");
            Main.menu();
            return;
        }

        System.out.println("Choose sender host:");
        for (int i = 0; i < allHosts.size(); i++) {
            System.out.println((i + 1) + ") " + allHosts.get(i).getHostIP());
        }
        int senderIndex = Integer.parseInt(sc.nextLine()) - 1;
        Host sender = allHosts.get(senderIndex);

        System.out.println("Choose receiver host:");
        for (int i = 0; i < allHosts.size(); i++) {
            if (i == senderIndex) continue;
            System.out.println((i + 1) + ") " + allHosts.get(i).getHostIP());
        }
        int receiverIndex = Integer.parseInt(sc.nextLine()) - 1;
        Host receiver = allHosts.get(receiverIndex);

        System.out.println("Enter message to send:");
        String message = sc.nextLine();

        try {
            sender.sendPacket(sender.getHostIP(), receiver.getHostIP(), message);
            System.out.println("Datagram sent from " + sender.getHostIP() + " to " + receiver.getHostIP());
        } catch (InterruptedException e) {
            System.out.println("Error sending datagram: " + e.getMessage());
        }

        Main.menu();
    }


    private void addNewRouter(){
        System.out.println("Enter available IP's for router");
        Scanner sc = new Scanner(System.in);
        allRoutes.add(sc.nextLine());
        Main.menu();
    }

    private void addIpsViaFile(){
        try {
            FileReader.fillFileIps();
            int i = 0;
            while (i != FileReader.fileIPs.size()){
                allIp.add(FileReader.fileIPs.get(i));
                System.out.println("IP's added successfully!");
                i++;
            }
            Main.menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("IP's add error");
        Main.menu();
    }

    private void browseHost(){
        System.out.println("There are all hosts:\n");
        int num = 1;
        for (Host s : allHosts) {
            System.out.println("Host " + num + " hosts ips = " + s.hostIP.toString());
            num++;
        }
        Main.menu();
    }

    private void browseRoute(){
        System.out.println("There are all routers:\n");
    }

    public void __init__menuChoices__(){
        menuChoices.put("1",this::addIps);
        menuChoices.put("2",this::addNewHost);
        menuChoices.put("3",this::addNewRouter);
        menuChoices.put("4",this::browseHost);
        menuChoices.put("5",this::browseRoute);
        menuChoices.put("6",this::addIpsViaFile);
        menuChoices.put("7",this::sendDatagram);
    }

    public HashMap<String,Runnable> menuChoices = new HashMap<>();
}
