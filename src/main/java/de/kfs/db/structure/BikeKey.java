package de.kfs.db.structure;

public class BikeKey {
    /**
     * Class that wraps a BikeKey
     * probably a bit useless in hindsight
     */
    private String frameKey;
    private String bpKey; //batteryPackKey

    public BikeKey(String fk, String bpKey) {
        this.bpKey = bpKey;
        this.frameKey = fk;
    }

    /**
     *
     * @return Battery Pack Key Number
     */
    public String getBpKey() {
        return bpKey;
    }

    /**
     *
     * @return frameKeyNumber
     */
    public String getFrameKey() {
        return frameKey;
    }
}
