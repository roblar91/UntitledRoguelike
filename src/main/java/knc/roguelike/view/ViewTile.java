/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.view;

import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.component.BackgroundComponent;
import knc.roguelike.model.entity.component.SpriteComponent;
import knc.roguelike.model.entity.component.Type;
import knc.roguelike.model.world.Tile;

/**
 * A graphical representation of a {@link Tile} meant to be used by {@link ViewPort}.
 */
class ViewTile extends Pane {
    /**
     *
     * @param size The horizontal and vertical size of the tile in pixels
     */
    ViewTile(int size) {
        setPrefSize(size, size);
        setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
    }

    /**
     * Render the provided {@link Tile} by reading the associated entities.
     * Rendering priority is: Creature > Item > Terrain. Background is always applied if present.
     * @param tile The tile that should be rendered
     */
    void setTile(Tile tile) {
        getChildren().clear();

        if(tile == null) {
            return;
        }

        if(tile.hasEntityWithComponent(Type.BACKGROUND)){
            var terrain = tile.getEntityByComponent(Type.BACKGROUND);
            var bg = (BackgroundComponent) terrain.getComponent(Type.BACKGROUND);
            setBackground(bg.getBackground());
        }

        // todo: render everything with a sprite, maybe?
        Entity entity;

        if(tile.hasEntityWithComponent(Type.ALIVE)){
            entity = tile.getEntityByComponent(Type.ALIVE);
        } else if(tile.hasEntityWithComponent(Type.TERRAIN)){
            entity = tile.getEntityByComponent(Type.TERRAIN);
        } else {
            return;
        }

        var sprite = (SpriteComponent) entity.getComponent(Type.SPRITE);
        var imageView = sprite.getImageView();
        imageView.setFitWidth(getPrefWidth());
        imageView.setFitHeight(getPrefHeight());
        getChildren().add(imageView);
    }
}
