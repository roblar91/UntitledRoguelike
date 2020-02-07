package knc.roguelike.model.entity;

import javafx.scene.image.Image;
import knc.roguelike.model.world.Area;

public class Creature extends Entity {
    private Attitude attitude;

    public Creature(Image sprite, Area area, int posX, int posY, Attitude attitude) {
        super(sprite, area, posX, posY);
        this.attitude = attitude;
    }
}
