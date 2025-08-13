package networkLayer;

public class Datagram {
    /* TODO THIS BULLSHIT IS USLESS FOR NOW
    private String version;
    private String IHL;
    private String TOS;
    private String totalLength;
    private String Identification;
    private int flags;
    private int fragmentsOffset;
    private String Protocol;
    private int checksum;
    private String options;
     */

    public IP source;
    public IP destination;
    public String data;
    public int ttl;

    public Datagram(IP source, IP destination, String data, int ttl) {
        this.source = source;
        this.destination = destination;
        this.data = data;
        this.ttl = ttl;
    }
}
