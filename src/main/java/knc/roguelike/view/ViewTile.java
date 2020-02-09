/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.view;

import javafx.scene.control.Control;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.component.BackgroundComponent;
import knc.roguelike.model.entity.component.SpriteComponent;
import knc.roguelike.model.entity.component.Type;
import knc.roguelike.model.world.Tile;

/**
 * A graphical representation of a {@link Tile} meant to be used by {@link ViewPort}.
 */
class ViewTile extends StackPane {
    private final Background defaultBackground;

    /**
     * @param size The horizontal and vertical size of the tile in pixels
     */
    ViewTile(int size, Background defaultBackground) {
        this.defaultBackground = defaultBackground;
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
            setBackground(defaultBackground);
            return;
        }

        if(tile.hasEntityWithComponent(Type.BACKGROUND)){
            var terrain = tile.getEntityByComponent(Type.BACKGROUND);
            var bg = (BackgroundComponent) terrain.getComponent(Type.BACKGROUND);
            setBackground(bg.getBackground());
        } else {
            setBackground(defaultBackground);
        }

        if(tile.hasEntityWithComponent(Type.ALIVE)){
            addImageViewFromEntity(tile.getEntityByComponent(Type.ALIVE));
        } else if(tile.hasEntityWithComponent(Type.TERRAIN)){
            addImageViewFromEntity(tile.getEntityByComponent(Type.TERRAIN));
        }
    }

    private void addImageViewFromEntity(Entity entity) {
        var sprite = (SpriteComponent) entity.getComponent(Type.SPRITE);
        var imageView = sprite.getImageView();
        imageView.setFitWidth(getPrefWidth());
        imageView.setFitHeight(getPrefHeight());
        getChildren().add(imageView);
    }
}