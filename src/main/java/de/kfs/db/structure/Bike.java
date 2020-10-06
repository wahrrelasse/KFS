package de.kfs.db.structure;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Bike extends AbstractBike {


    /**
     * save one bike using an already created output stream
     * @param out the OutputStream to write to
     * @throws IOException will be caught where out was created
     */
    public void save(DataOutputStream out) throws IOException {

        AbstractBike.save(out, this);

    }


}
