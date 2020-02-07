/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import javafx.scene.image.Image;

public class Creature extends Entity {
    private Attitude attitude;

    public Creature(Image sprite, Attitude attitude) {
        super(sprite);
        this.attitude = attitude;
    }
}
