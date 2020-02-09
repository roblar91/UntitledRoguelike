/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.view;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Control;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.world.Area;

/**
 * A Renderer is used to render part of an {@link Area}.
 */
public class ViewPort extends Pane{
    private final Background defaultBackground;
    private final ViewTile[][] viewTiles;
    private final Area area;
    private final int horizontalTiles;
    private final int verticalTiles;
    private int cameraX;
    private int cameraY;
    private Entity followTarget;
    private ChangeListener<Number> followListener;

    /**
     * @param area The {@link Area} that should be rendered
     * @param horizontalTiles Number of horizontal tiles that should be rendered
     * @param verticalTiles Number of vertical tiles that should be rendered
     * @param tileSize Size of each tile, in pixels
     */
    public ViewPort(Area area, int horizontalTiles, int verticalTiles, int tileSize) {
        this.defaultBackground = new Background(new BackgroundFill(Color.BLACK, null, null));
        this.setPrefSize(horizontalTiles * tileSize, verticalTiles * tileSize);
        this.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        this.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);

        this.viewTiles = new ViewTile[horizontalTiles][verticalTiles];
        for(int x=0; x<horizontalTiles; x++) {
            for(int y=0; y<verticalTiles; y++) {
                var tile = new ViewTile(tileSize, defaultBackground);
                tile.setTranslateX(x * tileSize);
                tile.setTranslateY(y * tileSize);
                viewTiles[x][y] = tile;
                getChildren().add(tile);
            }
        }

        this.area = area;
        this.horizontalTiles = horizontalTiles;
        this.verticalTiles = verticalTiles;
        this.cameraX = 0;
        this.cameraY = 0;
        this.setBackground(defaultBackground);
    }

    /**
     * Sets the top-left point of the camera to a tile in the associated {@link Area}.
     * @param x The horizontal index
     * @param y The vertical index
     */
    public void setCamera(int x, int y) {
        cameraX = x;
        cameraY = y;
    }

    /**
     * Sets the camera to center in on a tile in the associated {@link Area}.
     * @param x The horizontal index
     * @param y The vertical index
     */
    public void setCameraCenter(int x, int y) {
        cameraX = x - horizontalTiles / 2;
        cameraY = y - verticalTiles / 2;
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
     * Updates each tile with the current state of the associated {@link Area}.
     */
    public void updateAll() {
        for(int x = 0; x< horizontalTiles; x++) {
            for(int y = 0; y< verticalTiles; y++) {
                var tile = area.getTile(x+cameraX, y+cameraY);
                viewTiles[x][y].setTile(tile);
            }
        }
    }

    /**
     * Sets the camera to follow a specific {@link Entity}.
     * Passing in a null parameter will cause the camera to stop following.
     * @param entity The target to follow
     */
    public void setFollowTarget(Entity entity) {
        if(followTarget != null) {
            followTarget.position.posX.removeListener(followListener);
            followTarget.position.posY.removeListener(followListener);
        }

        followTarget = null;
        followListener = null;

        if(entity == null) {
            return;
        }

        followTarget = entity;
        followListener = (obs, oldV, newV) -> {
            setCameraCenter(entity.position.posX.get(), entity.position.posY.get());
        };

        entity.position.posX.addListener(followListener);
        entity.position.posY.addListener(followListener);

        setCameraCenter(entity.position.posX.get(), entity.position.posY.get());
        updateAll();
    }

    /**
     * Gets the target currently being followed by the camera, if any.
     * @return Current follow target
     */
    public Entity getFollowTarget() {
        return followTarget;
    }
}
