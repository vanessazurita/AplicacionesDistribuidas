import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import java.net.InetAddress;
import java.time.Instant;
import java.util.Date;

public class NTPClient {
    private static final String NTP_SERVER = "time.google.com";
    private final NTPUDPClient client;
    
    public NTPClient() {
        this.client = new NTPUDPClient();
        client.setDefaultTimeout(10000);
    }
    
    public long getCurrentNetworkTime() throws Exception {
        try {
            InetAddress address = InetAddress.getByName(NTP_SERVER);
            TimeInfo timeInfo = client.getTime(address);
            timeInfo.computeDetails();
            return timeInfo.getMessage().getTransmitTimeStamp().getTime();
        } catch (Exception e) {
            throw new Exception("Error al obtener tiempo NTP: " + e.getMessage());
        } finally {
            client.close();
        }
    }
}