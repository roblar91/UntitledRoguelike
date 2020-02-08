/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import knc.roguelike.model.world.Area;

public class Position {
    public Area area;
    public int posX;
    public int posY;

    public Position(Area area, int posX, int posY) {
        this.area = area;
        this.posX = posX;
        this.posY = posY;
    }
}
