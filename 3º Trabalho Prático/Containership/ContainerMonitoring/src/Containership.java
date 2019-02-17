import org.snmp4j.agent.mo.MOChangeEvent;
import org.snmp4j.agent.mo.MOChangeListener;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Containership {
    Agent agent;
    Integer contidxrow;

    public Containership(){contidxrow = 1;}

    public void start() throws IOException, InterruptedException{
        agent = new Agent("0.0.0.0/6666");
        agent.start();
        agent.setUpMIB();
        setStartDate();
        addValuesToImageTable(agent.getMyMib().getContImageTableEntry());
        addFlagListener(agent.getMyMib().getContCreateFlag());

        while (true) {
            System.out.println("Agent running...");
            Thread.sleep(5000);
        }
    }

    public void setStartDate(){
        agent.getMyMib().getContainershipUpTime().setValue(new OctetString(
                getCurrentTime()
        ));
    }
    public String getCurrentTime() {

        Date date = new Date();

        String strDateFormat = "dd/MM/yyyy hh:mm:ss";

        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

        return dateFormat.format(date);
    }

    public void addValuesToImageTable(MOTable table){
        table.addRow(table.createRow(
                new OID("1"),new Variable[]{
                        new Integer32(1),
                        new OctetString("ubuntu"),
                        new OctetString("latest")
                }));
        table.addRow(table.createRow(
                new OID("2"),new Variable[]{
                        new Integer32(2),
                        new OctetString("php"),
                        new OctetString("7.2-apache")
                }));
        table.addRow(table.createRow(
                new OID("3"),new Variable[]{
                        new Integer32(3),
                        new OctetString("postgre"),
                        new OctetString("9.5")
                }));
    }

    public void addFlagListener(MOScalar mo){
        mo.addMOChangeListener(new MOChangeListener() {
            public void beforePrepareMOChange(MOChangeEvent moChangeEvent) { }

            public void afterPrepareMOChange(MOChangeEvent moChangeEvent) { }

            public void beforeMOChange(MOChangeEvent moChangeEvent) {
                CONTAINERSHIPv2MIB mib = agent.getMyMib();
                if(moChangeEvent.getNewValue().equals(new Integer32(1)) ){
                    OctetString name = (OctetString) agent.getMyMib().getContName().getValue();
                    Integer32 contImageidx = (Integer32) agent.getMyMib().getContImageIndex().getValue();

                    mib.getContainerTableEntry().addRow(
                            mib.getContainerTableEntry().createRow(
                                    new OID(contidxrow.toString()),
                                    new Variable[]{
                                            new Integer32(contidxrow),
                                            name, contImageidx,
                                            new OctetString("creating"),
                                            new Integer32()
                                    }));
                }

            }

            public void afterMOChange(MOChangeEvent moChangeEvent) {
                CONTAINERSHIPv2MIB mib = agent.getMyMib();
                if(moChangeEvent.getNewValue().equals(new Integer32(1)) ){
                    OctetString name = (OctetString) agent.getMyMib().getContName().getValue();
                    Integer32 contImageidx = (Integer32) agent.getMyMib().getContImageIndex().getValue();

                    Random r = new Random();
                    int cpu = r.nextInt(100);

                    mib.getContainerTableEntry().addRow(
                            mib.getContainerTableEntry().createRow(
                                    new OID(contidxrow.toString()),
                                    new Variable[]{
                                            new Integer32(contidxrow),
                                            name, contImageidx,
                                            new OctetString("up"),
                                            new Integer32(cpu)
                                    }));

                    mib.getContImageIndex().setValue(new Integer32(0));
                    mib.getContName().setValue(new OctetString(""));
                    mib.getContCreateFlag().setValue(new Integer32(0));
                }
            }
        });
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Containership c = new Containership();
        c.start();

    }
}
