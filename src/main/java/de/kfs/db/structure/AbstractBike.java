package de.kfs.db.structure;

import de.kfs.db.SceneManager;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Parent;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractBike implements Comparable<AbstractBike> {

    /**
     * Abstract Class representing a Bike
     */



    protected String internalNumber;
    protected String frameNumber;
    protected BikeKey bikeKey;
    protected InformationWrapper additionalInfo;

    /**
     * default constructor
     */
    public AbstractBike() {

    }

    private AbstractBike(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    private AbstractBike (String internalNumber, String frameNumber, BikeKey bk, InformationWrapper info) {
        this.internalNumber = internalNumber;
        this.frameNumber = frameNumber;
        this.bikeKey = bk;
        this.additionalInfo = info;
    }

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


    public Property<String> numberProperty() {
        return new SimpleStringProperty(this.internalNumber);
    }
    public Property<String> frameKeyProperty() {
        return new SimpleStringProperty(this.bikeKey.getFrameKey());
    }
    public Property<String> bpKeyProperty() {
        return new SimpleStringProperty(this.bikeKey.getBpKey());
    }
    public Property<String> frameNumberProperty() {
        return new SimpleStringProperty(this.frameNumber);
    }
    public Property<String> brandProperty() {
        return new SimpleStringProperty(this.additionalInfo.getManufacturer());
    }
    public Property<String> colorProperty() {
        return new SimpleStringProperty(this.additionalInfo.getColor());
    }
    public Property<Number> tireProperty() {
        return new SimpleIntegerProperty(this.additionalInfo.getTireDiameter());
    }
    public Property<Number> frameHProperty() {
        return new SimpleIntegerProperty(this.additionalInfo.getFrameHeigth());
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

            SceneManager.showWarning("File Not Found Exception (save)");
        } catch (IOException e) {
            SceneManager.showSeriousError("IOException (save)");
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
        out.writeUTF(bike.additionalInfo.getColor());
        out.writeInt(bike.additionalInfo.getTireDiameter());
        out.writeInt(bike.additionalInfo.getFrameHeigth());
        //General
        out.writeUTF(bike.internalNumber);
        out.writeUTF(bike.frameNumber);


        }

    /**
     * @param file the file to load from
     * @return all Bike & EBikes of that file
     */
    public static List<AbstractBike> load(File file) {

        AbstractBike[] bikes = null;
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file.getPath())))) {

            //empty file / no list in it --> preventing IOException from being thrown
            if(in.available() < 4) {
                return new ArrayList<>();
            }
            bikes = new AbstractBike[in.readInt()];
            for(int i = 0; i < bikes.length; i++) {
                String next = in.readUTF();
                if(next.equals(Bike.class.getSimpleName())) {
                    bikes[i] = Bike.load(in);
                } else if (next.equals(EBike.class.getSimpleName())) {
                    bikes[i] = EBike.load(in);
                }
            }


        } catch (FileNotFoundException e) {
            SceneManager.showSeriousError("File Not Found Exception (load)");

        } catch (IOException e) {
            SceneManager.showSeriousError("IOException (load)");
        }

        //If for some reason its null
        if(bikes == null) {
            bikes = new AbstractBike[0];
        }
        return new ArrayList<>(Arrays.asList(bikes));

    }

    /**
     *
     * @param in the inputstream
     * @return abstract bike to be used further
     * @throws IOException will be caught where in is created
     */
    public static AbstractBike load(DataInputStream in) throws IOException {

        BikeKey bk = new BikeKey(in.readUTF(), in.readUTF());
        InformationWrapper info = new InformationWrapper(in.readUTF(), in.readUTF(), in.readInt(), in.readInt());

        return new AbstractBike(in.readUTF(), in.readUTF(), bk, info) {

        };




    }
    //Comparison Methods
    public static AbstractBike createDeleteComparisonBike(String internalNumber) {
        return new AbstractBike(internalNumber) {

        };
    }


    /**
     * Overrides Equals based on the comparison of internalNumbers
     * @param other the Object to compare to
     * @return true if other is a bike and they have the same internalNumber
     */
    public boolean equals(Object other) {
        if(other == null) {
            return false;
        }

        if(other instanceof AbstractBike) {
            return ((AbstractBike) other).internalNumber.equals(this.internalNumber);
        }

        return false;
    }
    @Override
    public int compareTo(AbstractBike other) {
        return this.internalNumber.compareToIgnoreCase(other.internalNumber);
    }

}
