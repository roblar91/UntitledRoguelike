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
    private final Entity owner;

    public Component(Entity owner) {
        this.owner = owner;
    }

    abstract public Type getType();

    public Entity getOwner() {
        return this.owner;
    }
}
