/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Item extends Entity {
    public Item(Image sprite) {
        this(sprite, null);
    }

    public Item(Image sprite, Color color) {
        super(sprite, color);
    }
}
