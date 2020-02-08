/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.renderer;

import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import knc.roguelike.model.entity.Entity;
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
     * Render the provided {@link knc.roguelike.model.world.Tile} by getting the {@link knc.roguelike.model.entity.Ground},
     * {@link knc.roguelike.model.entity.Creature} and {@link knc.roguelike.model.entity.Item} contained in the provided tile.
     * Rendering priority is: Creature > Item > Ground. Background is always applied from Ground if present.
     * @param tile The tile that should be displayed
     */
    void setTile(Tile tile) {
        getChildren().clear();

        if(tile == null) {
            return;
        }

        if(tile.hasGround()){
            setBackground(tile.getGround().getBackground());
        }

        Entity entity;

        if(tile.hasCreature()){
            entity = tile.getCreature();
        } else if(tile.hasItems()){
            entity = tile.getTopItem();
        } else if(tile.hasGround()){
            entity = tile.getGround();
        } else {
            return;
        }

        var imageView = entity.getImageView();
        imageView.setFitWidth(getPrefWidth());
        imageView.setFitHeight(getPrefHeight());
        getChildren().add(imageView);
    }
}
