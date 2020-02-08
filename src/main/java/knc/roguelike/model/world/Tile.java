/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.component.Type;

import java.util.ArrayList;

public class Tile {
    private ArrayList<Entity> entities = new ArrayList<>();
    private Background background;

    public Tile() {
        this(Color.BLACK);
    }

    public Tile(Color backgroundColor) {
        setBackgroundColor(backgroundColor);
    }

    public void setBackgroundColor(Color color) {
        background = new Background(new BackgroundFill(color, null, null));
    }

    public Background getBackground() {
        return background;
    }

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
}

