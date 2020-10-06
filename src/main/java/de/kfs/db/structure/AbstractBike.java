package de.kfs.db.structure;

import java.io.*;
import java.util.List;

public abstract class AbstractBike {

    /**
     * Abstract Class representing a Bike
     */

    protected String internalNumber;
    protected String frameNumber;
    protected InformationWrapper additionalInfo;
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
     *
     * @return the Wrapper of Information
     */
    public InformationWrapper getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * In case of modifications to a Bike (shouldn't be used often)
     * @param additionalInfo the new Wrapper for additional Information about a Bike
     */
    public void setAdditionalInfo(InformationWrapper additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    /**
     * saves a list of bikes into the filesystem
     * @param file the file to save to
     * @param bikes list of bikes to be saved
     */
    public static void save(File file, List<AbstractBike> bikes) {

       try(DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file.getPath())))) {

           out.writeInt(bikes.size()); //lenght first

           for(AbstractBike ab : bikes) {
               if(ab instanceof Bike) {
                   Bike b = (Bike) ab;
                   b.save(out);
               }

           }
       } catch (FileNotFoundException e) {

           e.printStackTrace();
           //TODO errorStages
       } catch (IOException e) {

           e.printStackTrace();
       }
    }
}
