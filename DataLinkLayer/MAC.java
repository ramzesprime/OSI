package dataLinkLayer;

public class MAC {
    private String mac;

    public MAC(String mac){
        this.mac = mac;
    }

    public  String toString(){
        return mac;
    }

    //ебаные обьедки
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MAC)) return false;
        MAC other = (MAC) obj;
        return this.mac.equals(other.mac);
    }

    @Override
    public int hashCode() {
        return mac.hashCode();
    }

}
