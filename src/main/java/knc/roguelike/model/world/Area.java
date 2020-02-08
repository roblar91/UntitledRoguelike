/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world;

import knc.roguelike.model.entity.Creature;
import knc.roguelike.model.entity.Entity;

public class Area {
    private Tile[][] tiles;

    public Area(int sizeX, int sizeY) {
        this.tiles = new Tile[sizeX][sizeY];

        for(int x=0; x<tiles.length; x++) {
            for(int y=0; y<tiles[0].length; y++) {
                tiles[x][y] = new Tile();
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTile(int posX, int posY) {
        try {
            return tiles[posX][posY];
        } catch(ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void move(Entity target, int dX, int dY) {
        var originTile = tiles[target.getPosX()][target.getPosY()];
        var targetTile = tiles[target.getPosX() + dX][target.getPosY() + dY];

        // todo: collision check

        originTile.setCreature(null);
        targetTile.setCreature((Creature) target);
        target.setPosition(target.getPosX() + dX, target.getPosY() + dY);
    }
}
