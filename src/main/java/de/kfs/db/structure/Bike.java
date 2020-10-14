package de.kfs.db.structure;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Bike extends AbstractBike {


    private Bike (AbstractBike ab) {

        this.internalNumber = ab.internalNumber;
        this.frameNumber = ab.frameNumber;
        this.bikeKey = ab.bikeKey;
        this.additionalInfo = ab.additionalInfo;
    }
    public Bike(String iN,  String fN, BikeKey bk, InformationWrapper info) {
        this.internalNumber = iN;
        this.bikeKey = bk;
        this.frameNumber = fN;
        this.additionalInfo = info;
    }
    /**
     * save one bike using an already created output stream
     * @param out the OutputStream to write to
     * @throws IOException will be caught where out was created
     */
    public void save(DataOutputStream out) throws IOException {

        AbstractBike.save(out, this);

    }

    /**
     *
     * @param in the used inputstream
     * @return new bike from the inputstream
     * @throws IOException catched where in is created
     */
    public static Bike load(DataInputStream in) throws IOException {

        //Casting here like return (Bike) AbstractBike.load(in); probably doesn't work
       return new Bike(AbstractBike.load(in));
    }

}
