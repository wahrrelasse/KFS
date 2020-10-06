package de.kfs.db.structure;


import java.io.DataOutputStream;
import java.io.IOException;

public class Bike extends AbstractBike {


    /**
     * save one bike using an already created output stream
     * @param out the OutputStream to write to
     * @throws IOException will be caught where out was created
     */
    public void save(DataOutputStream out) throws IOException {

        out.writeUTF(internalNumber);
        out.writeUTF(frameNumber);
        //Key
        out.writeUTF(bikeKey.getFrameKey());
        out.writeUTF(bikeKey.getBpKey());
        //Information wrapper
        out.writeUTF(additionalInfo.getManufacturer());
        out.writeUTF(additionalInfo.getModelName());
        out.writeInt(additionalInfo.getTireDiameter());
        out.writeInt(additionalInfo.getFrameHeigth());

    }
}
