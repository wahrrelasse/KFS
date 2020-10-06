package de.kfs.db.structure;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractBike {

    /**
     * Abstract Class representing a Bike
     */



    protected String internalNumber;
    protected String frameNumber;
    protected BikeKey bikeKey;
    protected InformationWrapper additionalInfo;

    public BikeKey getBikeKey() {
        return bikeKey;
    }

    public void setBikeKey(BikeKey bikeKey) {
        this.bikeKey = bikeKey;
    }



    /**
     * @return the number used to identify a bike at kfs
     */
    public String getInternalNumber() {
        return internalNumber;
    }

    /**
     * @return the frame number as imprinted on the bike by the original manufacturer
     */
    public String getFrameNumber() {
        return frameNumber;
    }

    /**
     * @return the Wrapper of Information
     */
    public InformationWrapper getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * In case of modifications to a Bike (shouldn't be used often)
     *
     * @param additionalInfo the new Wrapper for additional Information about a Bike
     */
    public void setAdditionalInfo(InformationWrapper additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    /**
     * saves a list of bikes into the filesystem
     *
     * @param file  the file to save to
     * @param bikes list of bikes to be saved
     */
    public static void save(File file, List<AbstractBike> bikes) {

        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(file.getPath())))) {

            out.writeInt(bikes.size()); //lenght first

            for (AbstractBike ab : bikes) {
                if (ab instanceof Bike) {
                    //Identifier: simple classname
                    out.writeUTF(Bike.class.getSimpleName());

                    ((Bike) ab).save(out);
                } else if (ab instanceof EBike) {
                    out.writeUTF(EBike.class.getSimpleName());

                    ((EBike) ab).save(out);
                }


            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            //TODO errorStages
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * save the general information for all bikes (no duplication in Bike & E-Bike)
     *
     * @param out  the outputstream to save to
     * @param bike to be saved
     * @throws IOException gets caught where out is created
     */
    public static void save(DataOutputStream out, AbstractBike bike) throws IOException {

        //Key
        out.writeUTF(bike.bikeKey.getFrameKey());
        out.writeUTF(bike.bikeKey.getBpKey());


        //InformationWrapper
        out.writeUTF(bike.additionalInfo.getManufacturer());
        out.writeUTF(bike.additionalInfo.getModelName());
        out.writeInt(bike.additionalInfo.getTireDiameter());
        out.writeInt(bike.additionalInfo.getFrameHeigth());
        //General
        out.writeUTF(bike.internalNumber);
        out.writeUTF(bike.frameNumber);


        }


}
