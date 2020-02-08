/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

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

    public Component getComponent(Type type) {
        for(Component component : components) {
            if(component.getType() == type) {
                return component;
            }
        }

        return null;
    }
}
