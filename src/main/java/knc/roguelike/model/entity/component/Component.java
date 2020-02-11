/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

import knc.roguelike.model.entity.Entity;

/**
 * A {@link Component} represents a characteristic of an object.
 */
public abstract class Component {
    private Entity owner;

    abstract public Type getType();

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public Entity getOwner() {
        return this.owner;
    }
}
