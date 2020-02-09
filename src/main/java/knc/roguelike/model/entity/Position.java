/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import knc.roguelike.model.world.Area;

public class Position {
    public Area area;
    public IntegerProperty posX;
    public IntegerProperty posY;

    public Position(Area area, int posX, int posY) {
        this.area = area;
        this.posX = new SimpleIntegerProperty(posX);
        this.posY = new SimpleIntegerProperty(posY);
    }

    public void move(int dX, int dY) {
        posX.set(posX.get() + dX);
        posY.set(posY.get() + dY);
    }
}
