package app;

import java.util.Scanner;

public class    Main {

    private static final LogicHandler myLogicHandler = new LogicHandler();

    public static void menu(){
        System.out.println("""
                ============================================================
                      NETWORK MODEL\s
                ============================================================"""
        );

        System.out.println("""
                MENU
                1.Add new IP's;
                2.Add new host
                3.Add new router
                4.Browse all hosts
                5.Browse all routes
                6.Add IP's via txt
                7.Send datagram
                9.Exit
                """
        );


        Scanner myScan = new Scanner(System.in);
        String consumerInput = myScan.nextLine();
        if (consumerInput.equals("9")){
            return;
        }
        myLogicHandler.__init__menuChoices__();
        Runnable action =myLogicHandler.menuChoices.get(consumerInput);
        if (action != null){
            action.run();
        }
    }

    public static void main(String[] args){

          menu();




    }
}
