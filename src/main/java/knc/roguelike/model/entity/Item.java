package knc.roguelike.model.entity;

import javafx.scene.image.Image;
import knc.roguelike.model.world.Area;

public class Item extends Entity {
    public Item(Image sprite, Area area, int posX, int posY) {
        super(sprite, area, posX, posY);
    }
}
