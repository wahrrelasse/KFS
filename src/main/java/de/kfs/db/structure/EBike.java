package de.kfs.db.structure;

import javax.imageio.IIOException;
import java.io.DataOutputStream;
import java.io.IOException;

public class EBike extends AbstractBike{


    private boolean backPedalBreak;
    private EngineType engineType;

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

    public void save(DataOutputStream out) throws IOException {

        AbstractBike.save(out, this);
        //specific ebike Info
        out.writeBoolean(backPedalBreak);
        out.writeUTF(engineType.name);



    }
}
