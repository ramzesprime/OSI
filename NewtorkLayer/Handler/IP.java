package NewtorkLayer.Handler;

public class IP {
    private String ip;
    static IP BROADCASTip = new IP("255.255.255.255");

    public IP(String ip){
        this.ip = ip;
    }

    public  String toString(){
        return ip;
    }

    //ебаные обьедки
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IP)) return false;
        IP other = (IP) obj;
        return this.ip.equals(other.ip);
    }

    @Override
    public int hashCode() {
        return ip.hashCode();
    }

}
