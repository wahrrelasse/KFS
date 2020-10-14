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

    private BikeKey(String s) {
        this.frameKey = s;
        this.bpKey = "";
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

    /**
     * Method for creating Keys of normal Bikes
     * (They have no battery Pack)
     * @param s the keyNumber
     * @return BikeKey where only frameKey is important
     */
    public static BikeKey createNormalBikeKey(String s) {
        return new BikeKey(s);
    }
}
