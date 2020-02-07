/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class Ground extends Entity {
    private Background background;

    public Ground(Image sprite) {
        this(sprite, Color.BLACK);
    }

    public Ground(Image sprite, Color backgroundColor) {
        super(sprite);
        setBackgroundColor(backgroundColor);
    }

    public Background getBackground() {
        return background;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.background = new Background(new BackgroundFill(backgroundColor, null, null));;
    }
}
