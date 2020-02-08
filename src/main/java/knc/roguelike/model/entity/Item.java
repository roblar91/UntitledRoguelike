/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Item extends Entity {
    public Item(Image image) {
        this(image, null);
    }

    public Item(Image image, Color color) {
        super(image, color);
    }
}
