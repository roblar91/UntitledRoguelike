package knc.roguelike.model.world;

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
}
