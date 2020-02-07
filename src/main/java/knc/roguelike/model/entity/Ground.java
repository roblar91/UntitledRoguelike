package knc.roguelike.model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import knc.roguelike.model.world.Area;

public class Ground extends Entity {
    private final Color background;

    public Ground(Image sprite, Area area, int posX, int posY) {
        this(sprite, area, posX, posY, Color.BLACK);
    }

    public Ground(Image sprite, Area area, int posX, int posY, Color background) {
        super(sprite, area, posX, posY);
        this.background = background;
    }
}
