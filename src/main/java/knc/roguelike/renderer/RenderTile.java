/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.renderer;

import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.component.Type;
import knc.roguelike.model.world.Tile;

/**
 * A graphical representation of a {@link knc.roguelike.model.world.Tile} meant to be used by {@link knc.roguelike.renderer.Renderer}.
 */
class RenderTile extends Pane {
    /**
     *
     * @param size The horizontal and vertical size of the tile in pixels
     */
    RenderTile(int size) {
        setPrefSize(size, size);
        setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
    }

    /**
     * Render the provided {@link knc.roguelike.model.world.Tile} by reading the associated entities.
     * Rendering priority is: Creature > Item > Terrain. Background is always applied if present.
     * @param tile The tile that should be rendered
     */
    void setTile(Tile tile) {
        getChildren().clear();

        if(tile == null) {
            return;
        }

        setBackground(tile.getBackground());

        Entity entity;

        if(tile.hasEntityWithComponent(Type.LIVING)){
            entity = tile.getEntityByComponent(Type.LIVING);
        } else if(tile.hasEntityWithComponent(Type.TERRAIN)){
            entity = tile.getEntityByComponent(Type.TERRAIN);
        } else {
            return;
        }

        var imageView = entity.getImageView();
        imageView.setFitWidth(getPrefWidth());
        imageView.setFitHeight(getPrefHeight());
        getChildren().add(imageView);
    }
}