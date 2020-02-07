package knc.roguelike.model.world;

public class Area {
    private Tile[][] tiles;

    public Area(int sizeX, int sizeY){
        this.tiles = new Tile[sizeX][sizeY];
    }

    public Tile getTile(int posX, int posY) {
        return tiles[posX][posY];
    }
}
