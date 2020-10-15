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

    /**
     * merge the param into the calling wrapper. Only non-default, not empty
     * values of param will be used to override callings wrapper's attributes
     * defaults: manufacturer "DEFAULT", color "egal", tireDiameter & frameHeight -1
     * @param other Wrapper that has the information to merge into calling wrapper
     */
    public void merge(InformationWrapper other) {
        if(!(other.manufacturer.isEmpty() || other.manufacturer.equals("DEFAULT"))) {
            this.manufacturer = other.manufacturer;
        }
        if (!(other.color.isEmpty() || other.color.equals("egal"))) {
            this.color = other.color;
        }
        if (other.tireDiameter != -1) {
            this.tireDiameter = other.tireDiameter;
        }
        if (other.frameHeigth != -1) {
            this.frameHeigth = other.frameHeigth;
        }
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
