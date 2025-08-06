package DataLinkLayer;

import NewtorkLayer.Handler.IP;

public class MAC {
    public String mac ;

    public MAC(String macAdress){
        this.mac = macAdress;
    }

    public String toString() {
        return mac;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MAC)) return false;
        MAC other = (MAC) obj;
        return this.mac.equals(other.mac);
    }
}
