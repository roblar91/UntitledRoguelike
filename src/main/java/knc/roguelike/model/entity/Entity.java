/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import knc.roguelike.exception.IllegalActionException;
import knc.roguelike.model.entity.component.Component;
import knc.roguelike.model.entity.component.Type;

import java.util.HashSet;
import java.util.Set;

/**
 * An {@link Entity} is any object in the world.
 * The characteristics of an Entity is specified by assigning various {@link Component} to it.
 */
public class Entity {
    private Set<Component> components = new HashSet<>();
    public Position position;

    /**
     * @param position The location of this Entity in the game world
     */
    public Entity(Position position) {
        this.position = position;
    }

    /**
     * Assigns a {@link Component} to this Entity.
     * @param component The component to assign
     */
    public void addComponent(Component component) {
        components.add(component);
    }

    /**
     * Checks if the specified {@link Component} {@link Type} is present in this Entity.
     * @param type The type to check for
     * @return True if component is present
     */
    public boolean hasComponent(Type type) {
        return getComponent(type) != null;
    }

    /**
     * Gets a {@link Component} of the specified {@link Type}. If not present, will return null.
     * @param type The type to return
     * @return The component
     */
    public Component getComponent(Type type) {
        for(Component component : components) {
            if(component.getType() == type) {
                return component;
            }
        }

        return null;
    }

    /**
     * Attempts to move this Entity in its current area. If the move is not possible an exception is thrown.
     * @param dX The change in X coordinates
     * @param dY The change in Y coordinates
     * @throws IllegalActionException Thrown if the move is not possible
     */
    public void move(int dX, int dY) throws IllegalActionException {
        if(!hasComponent(Type.MOBILE)) {
            throw new IllegalActionException("Unable to move");
        }

        var originTile = position.area.getTile(position.posX.get(), position.posY.get());
        var targetTile = position.area.getTile(position.posX.get() + dX, position.posY.get() + dY);

        if(targetTile == null) {
            throw new IllegalActionException("Out of bounds");
        }

        if(targetTile.hasEntityWithComponent(Type.SOLID)) {
            // todo: check for interactive objects or npcs
            throw new IllegalActionException("Tile is solid");
        }

        originTile.removeEntity(this);
        targetTile.addEntity(this);
        position.move(dX, dY);
    }
}
