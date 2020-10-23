package de.kfs.db.structure;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public class SellStatus {

    private Date boughtOn;
    private boolean sold;


    /**
     * creates sellStatus with todays Date
     */
    public SellStatus() {
        boughtOn = new Date(System.currentTimeMillis());
        sold = false;
    }
    private SellStatus(Date boughtOn, boolean sold) {
        this.boughtOn = boughtOn;
        this.sold = sold;
    }



    public static void save(DataOutputStream out, SellStatus ss) throws IOException {
        out.writeLong(ss.boughtOn.getTime());
        out.writeBoolean(ss.sold);


    }
    public static SellStatus load(DataInputStream in) throws IOException {
        return new SellStatus(new Date(in.readLong()), in.readBoolean());
    }
}
