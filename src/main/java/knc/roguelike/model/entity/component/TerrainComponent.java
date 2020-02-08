/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

public class TerrainComponent implements Component {
    private boolean isSolid;

    public TerrainComponent(boolean isSolid) {
        this.isSolid = isSolid;
    }

    @Override
    public Type getType() {
        return Type.TERRAIN;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }
}
