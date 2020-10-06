package de.kfs.db.structure;

public enum EngineType {

    /**
     * Enum for different Ebike Motortypes:
     * Bosch: usually middleEngine with a displays like intuvia or purion to use with diagnostic tool
     * Bafang: pretty old middleEngine with a mounted display (and some battery pack issues)
     * Santour: pretty old, but with front wheel drive (thus closer to a true EBike)
     * Shimano: like Bosch, but with their own Software (E-Tube Project AFAIK) and more option in menu
     */

    BOSCH("Bosch"), BAFANG("Bafang"), SANTOUR("Santour"), SHIMANO("Shimano");
    public String name;
    private EngineType(String name) {
        this.name = name;
    }
}
