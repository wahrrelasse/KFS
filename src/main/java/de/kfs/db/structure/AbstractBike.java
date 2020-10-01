package de.kfs.db.structure;

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
}
