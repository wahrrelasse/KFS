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


    /**
     *
     * Usage: if needed to display information within a single String
     * @return a nicely formatted String to display Information
     */
    @Override
    public String toString() {

        String res = "";

        return res;
    }
}
