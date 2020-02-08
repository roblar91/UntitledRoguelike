/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Creature extends Entity {
    private Attitude attitude;

    public Creature(Image sprite, Attitude attitude) {
        this(sprite, attitude, null);
    }

    public Creature(Image sprite, Attitude attitude, Color color) {
        super(sprite, color);
        this.attitude = attitude;
    }

}
