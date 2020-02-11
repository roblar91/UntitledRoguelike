/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import knc.roguelike.engine.Game;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.component.BackgroundComponent;
import knc.roguelike.model.entity.component.SpriteComponent;
import knc.roguelike.model.entity.component.Type;

import java.util.ArrayList;

/**
 * A {@link Tile} is a unit of space in the game world.
 */
public class Tile extends Pane {
    private int column;
    private int row;
    private ArrayList<Entity> entities = new ArrayList<>();

    public Tile(int column, int row) {
        this.column = column;
        this.row = row;
        this.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        this.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);

        Game.viewTileSize.addListener((observable, oldValue, newValue) -> updatePositionAndSize());
        updatePositionAndSize();
    }

    /**
     * Assigns an {@link Entity} to this Tile
     * @param entity The entity to assign
     */
    public void addEntity(Entity entity) {
        entities.add(entity);

        if(entity.hasComponent(Type.BACKGROUND)){
            var background = (BackgroundComponent) entity.getComponent(Type.BACKGROUND);
            setBackground(background.getBackground());
        }

        if(entity.hasComponent(Type.SPRITE)){
            var sprite = (SpriteComponent) entity.getComponent(Type.SPRITE);
            sprite.setSize(Game.viewTileSize.get());
            getChildren().add(sprite.getImageView());
        }
    }

    /**
     * Removes an {@link Entity} from this Tile
     * @param entity The entity to remove
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * Return true if there is any {@link Entity} at this Tile containing the specified {@link Type}.
     * @param type The type to look for
     * @return True if component found
     */
    public boolean hasEntityWithComponent(Type type) {
        return getEntityByComponent(type) != null;
    }

    /**
     * Returns an {@link Entity} containing the specified {@link Type} if there is one present at this Tile.
     * Returns null if none exists.
     * @param type The type to look for
     * @return The entity containing the type
     */
    public Entity getEntityByComponent(Type type) {
        for(Entity entity: entities) {
            if(entity.getComponent(type) != null) {
                return entity;
            }
        }

        return null;
    }

    /**
     * Removes all {@link Entity}s from this Tile.
     */
    public void removeAllEntities() {
        this.entities.clear();
    }

    private void updatePositionAndSize() {
        setTranslateX(Game.viewTileSize.get() * column);
        setTranslateY(Game.viewTileSize.get() * row);
        setPrefSize(Game.viewTileSize.get(), Game.viewTileSize.get());

        entities.forEach(entity -> {
            if(entity.hasComponent(Type.SPRITE)){
                var sprite = (SpriteComponent) entity.getComponent(Type.SPRITE);
                sprite.setSize(Game.viewTileSize.get());
            }
        });

    }
}

