/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world;

import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.component.Type;

import java.util.ArrayList;

public class Tile {
    private ArrayList<Entity> entities = new ArrayList<>();

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public boolean hasEntityWithComponent(Type type) {
        return getEntityByComponent(type) != null;
    }

    public Entity getEntityByComponent(Type type) {
        for(Entity entity: entities) {
            if(entity.getComponent(type) != null) {
                return entity;
            }
        }

        return null;
    }

    public void removeAllEntities() {
        this.entities.clear();
    }
}

