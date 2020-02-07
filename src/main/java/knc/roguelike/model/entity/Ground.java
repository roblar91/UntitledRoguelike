/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Ground extends Entity {
    private final Color background;

    public Ground(Image sprite) {
        this(sprite, Color.BLACK);
    }

    public Ground(Image sprite, Color background) {
        super(sprite);
        this.background = background;
    }
}
