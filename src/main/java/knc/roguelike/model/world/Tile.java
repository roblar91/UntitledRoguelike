/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world;

import knc.roguelike.model.entity.Creature;
import knc.roguelike.model.entity.Ground;
import knc.roguelike.model.entity.Item;

import java.util.ArrayList;

public class Tile {
    private Ground ground;
    private Creature creature;
    private ArrayList<Item> items;

    public Ground getGround() {
        return ground;
    }

    public boolean hasGround() {
        return ground != null;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public boolean hasCreature() {
        return creature != null;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}

