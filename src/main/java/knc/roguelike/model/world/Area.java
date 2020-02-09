/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world;

/**
 * An {@link Area} represents a level in the game world and stores all {@link Tile}s relevant to it.
 */
public class Area {
    private Tile[][] tiles;

    /**
     * @param sizeX The number of columns of tiles the area should contain
     * @param sizeY The number of rows of tiles the area should contain
     */
    public Area(int sizeX, int sizeY) {
        this.tiles = new Tile[sizeX][sizeY];

        for(int x=0; x<tiles.length; x++) {
            for(int y=0; y<tiles[0].length; y++) {
                tiles[x][y] = new Tile();
            }
        }
    }

    /**
     * Gets all {@link Tile}s relevant to this area.
     * @return The tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Gets the {@link Tile} at the specified coordinate
     * @param posX The X coordinate
     * @param posY The Y coordinate
     * @return The tile
     */
    public Tile getTile(int posX, int posY) {
        try {
            return tiles[posX][posY];
        } catch(ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
