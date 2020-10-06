package de.kfs.db.structure;

public class InformationWrapper {

    /**
     * This class wraps specific information about a Bike such as
     * frame height, tire diameter, manufacturer
     * and has utility to return these values into different types
     * of Tables
     */

    private String manufacturer;
    private String modelName;
    private int frameHeigth;
    private int tireDiameter;

    public InformationWrapper(String manufacturer, String modelName, int frameHeigth, int tireDiameter) {
        this.manufacturer = manufacturer;
        this.modelName = modelName;
        this.frameHeigth = frameHeigth;
        this.tireDiameter = tireDiameter;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public int getFrameHeigth() {
        return frameHeigth;
    }

    public int getTireDiameter() {
        return tireDiameter;
    }

    /**
     *
     * Usage: if needed to display information within a single String
     * @return a nicely formatted String to display Information
     */
    @Override
    public String toString() {

        String res = manufacturer + "; " + modelName + "; " +  tireDiameter + "; " + frameHeigth;

        return res;
    }
}
