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



    public void save(DataOutputStream out) throws IOException {
        out.writeLong(boughtOn.getTime());
        out.writeBoolean(sold);


    }
    public SellStatus load(DataInputStream in) throws IOException {
        return new SellStatus(new Date(in.readLong()), in.readBoolean());
    }
}
