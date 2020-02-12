/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world;

import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Pane;
import knc.roguelike.engine.Options;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.component.Type;

/**
 * An {@link Area} represents a level in the game world and stores all {@link Tile}s relevant to it.
 */
public class Area extends Pane {
    private Tile[][] tiles;
    private int cameraX = 0;
    private int cameraY = 0;
    private Entity followTarget;
    private ChangeListener<Number> followListener;

    /**
     * @param columns The number of columns of tiles the area should contain
     * @param rows The number of rows of tiles the area should contain
     */
    public Area(int columns, int rows) {
        this.tiles = new Tile[columns][rows];

        for(int x=0; x<tiles.length; x++) {
            for(int y=0; y<tiles[0].length; y++) {
                var tile = new Tile(x, y);
                tiles[x][y] = tile;
            }
        }

        ChangeListener<Number> dimensionChangeListener = (obs, oldV, newV) -> {
            if(followTarget != null)
                setCameraCenter(followTarget.position.posX.get(), followTarget.position.posY.get());
        };
        Options.actualViewWidth.addListener(dimensionChangeListener);
        Options.actualViewHeight.addListener(dimensionChangeListener);
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

    /**
     * Returns the {@link Tile} where the entrance is located.
     * @return The tile with the entrance
     */
    public Entity getEntrance() {
        for(int x=0; x<tiles.length; x++) {
            for(int y=0; y<tiles[0].length; y++) {
                if(tiles[x][y].hasEntityWithComponent(Type.ENTRANCE)){
                    return tiles[x][y].getEntityByComponent(Type.ENTRANCE);
                }
            }
        }

        return null;
    }

    /**
     * Sets the top-left point of the camera to a tile in the associated {@link Area}.
     * @param x The horizontal index
     * @param y The vertical index
     */
    public void setCamera(int x, int y) {
        cameraX = x;
        cameraY = y;
        reposition();
    }

    /**
     * Sets the camera to center in on a tile in the associated {@link Area}.
     * @param x The horizontal index
     * @param y The vertical index
     */
    public void setCameraCenter(int x, int y) {
        cameraX = x - Options.actualViewWidth.get() / Options.tileSize.get() / 2;
        cameraY = y - Options.actualViewHeight.get() / Options.tileSize.get() / 2;
        reposition();
    }

    /**
     * Moves the view the specified amount of tiles.
     * @param dX The number of tiles to move horizontally
     * @param dY The number of tiles to move vertically
     */
    public void moveCamera(int dX, int dY) {
        cameraX += dX;
        cameraY += dY;
        reposition();
    }

    /**
     * Sets the camera to follow a specific {@link Entity}.
     * Passing in a null parameter will cause the camera to stop following.
     * @param entity The target to follow
     */
    public void setCameraFollowTarget(Entity entity) {
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
    }

    /**
     * Gets the target currently being followed by the camera, if any.
     * @return Current follow target
     */
    public Entity getCameraFollowTarget() {
        return followTarget;
    }

    private void reposition() {
        setTranslateX(-(cameraX * Options.tileSize.get()));
        setTranslateY(-(cameraY * Options.tileSize.get()));
    }
}
