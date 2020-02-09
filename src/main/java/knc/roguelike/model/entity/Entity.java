/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import knc.roguelike.exception.IllegalActionException;
import knc.roguelike.model.entity.component.Component;
import knc.roguelike.model.entity.component.Type;

import java.util.ArrayList;

public class Entity {
    private ArrayList<Component> components = new ArrayList<>();
    public Position position;

    public Entity(Position position) {
        this.position = position;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public boolean hasComponent(Type type) {
        return getComponent(type) != null;
    }

    public Component getComponent(Type type) {
        for(Component component : components) {
            if(component.getType() == type) {
                return component;
            }
        }

        return null;
    }

    public void move(int dX, int dY) throws IllegalActionException {
        if(!hasComponent(Type.MOBILE)) {
            throw new IllegalActionException("Unable to move");
        }

        var originTile = position.area.getTile(position.posX, position.posY);
        var targetTile = position.area.getTile(position.posX + dX, position.posY + dY);

        if(targetTile == null) {
            throw new IllegalActionException("Out of bounds");
        }

        if(targetTile.hasEntityWithComponent(Type.SOLID)) {
            // todo: check for interactive objects or npcs
            throw new IllegalActionException("Tile is solid");
        }

        originTile.removeEntity(this);
        targetTile.addEntity(this);
        position.posX += dX;
        position.posY += dY;
    }
}
