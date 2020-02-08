/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.renderer;

import javafx.scene.control.Control;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import knc.roguelike.model.world.Area;

/**
 * A Renderer is used to render part of an {@link knc.roguelike.model.world.Area}.
 */
public class Renderer extends Pane{
    private final RenderTile[][] renderTiles;
    private final Area area;
    private final int horizontalTiles;
    private final int verticalTiles;
    private int cameraX;
    private int cameraY;

    /**
     *
     * @param area The {@link knc.roguelike.model.world.Area} that should be rendered
     * @param horizontalTiles Number of horizontal tiles that should be rendered
     * @param verticalTiles Number of vertical tiles that should be rendered
     * @param tileSize Size of each tile, in pixels
     */
    public Renderer(Area area, int horizontalTiles, int verticalTiles, int tileSize) {
        this.setPrefSize(horizontalTiles * tileSize, verticalTiles * tileSize);
        this.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        this.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);

        this.renderTiles = new RenderTile[horizontalTiles][verticalTiles];
        for(int x=0; x<horizontalTiles; x++) {
            for(int y=0; y<verticalTiles; y++) {
                var tile = new RenderTile(tileSize);
                tile.setTranslateX(x * tileSize);
                tile.setTranslateY(y * tileSize);
                renderTiles[x][y] = tile;
                getChildren().add(tile);
            }
        }

        this.area = area;
        this.horizontalTiles = horizontalTiles;
        this.verticalTiles = verticalTiles;
        this.cameraX = 0;
        this.cameraY = 0;
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }

    /**
     * Sets the top-left point of the camera to a tile in the associated {@link knc.roguelike.model.world.Area}.
     * @param x The horizontal index
     * @param y The vertical index
     */
    public void setCamera(int x, int y) {
        cameraX = x;
        cameraY = y;
    }

    /**
     * Moves the view the specified amount of tiles.
     * @param dX The number of tiles to move horizontally
     * @param dY The number of tiles to move vertically
     */
    public void moveCamera(int dX, int dY) {
        cameraX += dX;
        cameraY += dY;
    }

    /**
     * Updates each tile with the current state of the associated {@link knc.roguelike.model.world.Area}.
     */
    public void render() {
        for(int x = 0; x< horizontalTiles; x++) {
            for(int y = 0; y< verticalTiles; y++) {
                var tile = area.getTile(x+cameraX, y+cameraY);
                renderTiles[x][y].setTile(tile);
            }
        }
    }
}
