package knc.roguelike.renderer;

import javafx.scene.control.Control;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import knc.roguelike.model.world.Area;

public class Renderer extends Pane{
    private final ViewTile[][] viewTiles;
    private final Area area;
    private final int cameraSizeX;
    private final int cameraSizeY;
    private int cameraX;
    private int cameraY;

    public Renderer(Area area, int cameraSizeX, int cameraSizeY, int tileSize) {
        this.setPrefSize(cameraSizeX * tileSize, cameraSizeY * tileSize);
        this.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        this.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);

        this.viewTiles = new ViewTile[cameraSizeX][cameraSizeY];
        for(int x=0; x<cameraSizeX; x++) {
            for(int y=0; y<cameraSizeY; y++) {
                var tile = new ViewTile(tileSize);
                tile.setTranslateX(x * tileSize);
                tile.setTranslateY(y * tileSize);
                viewTiles[x][y] = tile;
                getChildren().add(tile);
            }
        }

        this.area = area;
        this.cameraSizeX = cameraSizeX;
        this.cameraSizeY = cameraSizeY;
        this.cameraX = 0;
        this.cameraY = 0;
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }

    public void setCamera(int x, int y) {
        cameraX = x;
        cameraY = y;
    }

    public void moveCamera(int dX, int dY) {
        cameraX += dX;
        cameraY += dY;
    }

    public void render() {
        for(int x=0; x<cameraSizeX; x++) {
            for(int y=0; y<cameraSizeY; y++) {
                var tile = area.getTile(x+cameraX, y+cameraY);
                viewTiles[x][y].setTile(tile);
            }
        }
    }
}
