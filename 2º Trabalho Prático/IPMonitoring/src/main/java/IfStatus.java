import java.util.concurrent.TimeUnit;

public class IfStatus implements Runnable{
    private static SNMPClient snmp;
    private static boolean keepRunning = true;
    private int index;
    private String macAddress;
    private int polling;
    private Double lastVal;


    public IfStatus(String mac, int index){
        this.macAddress = mac;
        this.index = index;
        this.polling = 5;
    }

    public void setPolling(int x){
        this.polling=x;
    }

    public String getPhysAddress(){
        return this.macAddress;
    }

    public static void setSnmp (SNMPClient a) { snmp = a; }

    public static void kill(){
        keepRunning = false;
    }
    public static void activate(){
        keepRunning = true;
    }

    public void updatePolling(double traffic){
        if((lastVal - traffic > 50 || lastVal - traffic < -50 ) && polling > 0) setPolling( polling - 1);
        else if((lastVal - traffic < 10 && lastVal - traffic > -10) && polling < 10) setPolling( polling +1);
    }

    public void run(){
        while(keepRunning) {
            double traffic = snmp.getTraffic(macAddress, index);
            if(lastVal == null){
                lastVal=traffic;
            }
            else{
                updatePolling(traffic);
                lastVal = traffic;
            }
            try {
                TimeUnit.SECONDS.sleep(polling);
            } catch (InterruptedException e) {
                System.out.println("Exiting");
            }
        }
    }
}
