package app;

public class ColorManager {

    private String whoOK ;
    private String whoMID ;
    private String whoSHIT ;

    public  ColorManager(int type){
        if (type == 1){
            whoOK = "\u001B[32m" + "//HOST//" + "\u001B[32m";
            whoMID = "\u001B[33m" + "//HOST//" + "\u001B[33m";
            whoSHIT = "\u001B[31m" + "//HOST//" + "\u001B[31m";
        }
        else if (type == 2){
            whoOK = "\u001B[32m" + "//ROUTER//" + "\u001B[32m";
            whoMID = "\u001B[33m" + "//ROUTER//" + "\u001B[33m";
            whoSHIT = "\u001B[31m" + "//ROUTER//" + "\u001B[31m";
        }
    }

    public String get_whoOK(){
        return whoOK;
    }

    public String get_whoMID(){
        return whoMID;
    }

    public String get_whoSHIT(){
        return whoSHIT;
    }

}
