package knc.roguelike.model.world;

import knc.roguelike.model.entity.Creature;
import knc.roguelike.model.entity.Ground;
import knc.roguelike.model.entity.Item;

import java.util.ArrayList;
import java.util.Iterator;

public class Tile {
    private Ground ground;
    private Creature creature;
    private ArrayList<Item> items;

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public Iterator<Item> getItems() {
        return items.iterator();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}

