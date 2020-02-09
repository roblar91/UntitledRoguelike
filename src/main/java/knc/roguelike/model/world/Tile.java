/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world;

import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.component.Type;

import java.util.ArrayList;

/**
 * A Tile is a unit of space in the game world.
 */
public class Tile {
    private ArrayList<Entity> entities = new ArrayList<>();

    /**
     * Assigns an {@link Entity} to this Tile
     * @param entity The entity to assign
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
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
}

