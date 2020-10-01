package de.kfs.db.structure;

import java.io.File;
import java.util.List;

public interface Savable<T extends AbstractBike> {
    public void save(File file, List<T> list);
}
