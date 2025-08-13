package networkLayer;

public class IP {
    private String ip;

    public IP(String ip){
        this.ip = ip;
    }

    public  String toString(){
        return ip;
    }

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

    public String get_ip(){
        return ip;
    }

    public boolean fuck(Object obj){
        return get_ip() != null;
    }

}
