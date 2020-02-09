/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import knc.roguelike.model.world.Area;

/**
 * Position is meant for storing the positional data of an {@link Entity}.
 * The X and Y coordinates are stored as {@link IntegerProperty} so that they can be used for data binding.
 */
public class Position {
    public Area area;
    public IntegerProperty posX;
    public IntegerProperty posY;

    /**
     * @param area The area this position refers to
     * @param posX The X coordinate
     * @param posY The Y coordinate
     */
    public Position(Area area, int posX, int posY) {
        this.area = area;
        this.posX = new SimpleIntegerProperty(posX);
        this.posY = new SimpleIntegerProperty(posY);
    }

    /**
     * Updates the coordinates relatively.
     * @param dX The change in X coordinates
     * @param dY The change in Y coordinates
     */
    public void move(int dX, int dY) {
        posX.set(posX.get() + dX);
        posY.set(posY.get() + dY);
    }
}
