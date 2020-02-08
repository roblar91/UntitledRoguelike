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

    public Creature(Image image, Attitude attitude) {
        this(image, attitude, null);
    }

    public Creature(Image image, Attitude attitude, Color color) {
        super(image, color);
        this.attitude = attitude;
    }

}
