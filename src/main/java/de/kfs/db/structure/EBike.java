package de.kfs.db.structure;

import javax.imageio.IIOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class EBike extends AbstractBike{
    private boolean backPedalBreak;
    private EngineType engineType;



    private EBike (AbstractBike ab) {
        this.internalNumber = ab.frameNumber;
        this.frameNumber = ab.frameNumber;
        this.bikeKey = ab.bikeKey;
        this.additionalInfo = ab.additionalInfo;
    }
    public EBike (String iN, BikeKey bk, String fN, InformationWrapper info) {
        internalNumber = iN;
        bikeKey = bk;
        frameNumber = fN;
        additionalInfo = info;
    }
    /**
     *
     * @return the type of Engine this EBike has
     */
    public EngineType getEngineType() {
        return engineType;
    }

    /**
     *
     * @return true if the Bike has a back pedal break
     */
    public boolean hasBackPedalBreak() {
        return backPedalBreak;
    }

    public void setBackPedalBreak(boolean backPedalBreak) {
        this.backPedalBreak = backPedalBreak;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public void save(DataOutputStream out) throws IOException {

        AbstractBike.save(out, this);
        //specific ebike Info
        out.writeBoolean(backPedalBreak);
        out.writeUTF(engineType.name);



    }

    /**
     *
     * @param in the used inputstream
     * @return new Ebike from the inputstream
     * @throws IOException catched where in is created
     */
    public static EBike load (DataInputStream in) throws IOException {

        EBike res = new EBike(AbstractBike.load(in));
        res.backPedalBreak = in.readBoolean();
        res.engineType = EngineType.valueOf(in.readUTF());

        return res;
    }

}
