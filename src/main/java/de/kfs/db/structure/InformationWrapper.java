package de.kfs.db.structure;

public class InformationWrapper {

    /**
     * This class wraps specific information about a Bike such as
     * frame height, tire diameter, manufacturer
     * and has utility to return these values into different types
     * of Tables
     */

    private String manufacturer;
    private String color;
    private int frameHeigth;
    private int tireDiameter;

    public InformationWrapper(String manufacturer, String color, int frameHeigth, int tireDiameter) {


        if(manufacturer.trim().isEmpty()) {
            this.manufacturer = "DEFAULT";
        } else {
            this.manufacturer = manufacturer;
        }
        if(manufacturer.trim().isEmpty()) {
            this.color = "egal";
        } else {
            this.color = color;
        }

        this.frameHeigth = frameHeigth;

        this.tireDiameter = tireDiameter;
    }



    public String getManufacturer() {
        return manufacturer;
    }

    public String getColor() {
        return color;
    }

    public int getFrameHeigth() {
        return frameHeigth;
    }

    public int getTireDiameter() {
        return tireDiameter;
    }

    public void merge(InformationWrapper other) {

    }
    /**
     *
     * Usage: if needed to display information within a single String
     * @return a nicely formatted String to display Information
     */
    @Override
    public String toString() {

        String res = manufacturer + "; " + color + "; " +  tireDiameter + "; " + frameHeigth;

        return res;
    }
}
