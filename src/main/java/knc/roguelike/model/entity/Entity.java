/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity;

import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Entity {
    private ImageView spriteView;
    private Image spriteImage;
    private Color color;

    public Entity(Image spriteImage, Color color) {
        this.spriteView = new ImageView(spriteImage);
        this.spriteImage = spriteImage;
        this.color = color;

        this.spriteView.fitHeightProperty().addListener((obs, oldV, newV) -> applyColorBlend());
    }

    private void applyColorBlend() {
        if(color != null) {
            var clip = new ImageView(spriteImage);
            clip.setFitHeight(spriteView.getFitHeight());
            clip.setFitWidth(spriteView.getFitWidth());
            spriteView.setClip(clip);

            var blackout = new ColorAdjust();
            blackout.setBrightness(-1);
            blackout.setSaturation(-1);
            blackout.setHue(-1);

            var coloring = new ColorInput(0, 0, spriteView.getFitWidth(), spriteView.getFitHeight(), color);

            var blend = new Blend(BlendMode.ADD, blackout, coloring);

            this.spriteView.setEffect(blend);
        }
    }

    public ImageView getSpriteView() {
        return spriteView;
    }

    public void setSpriteView(ImageView spriteView) {
        this.spriteView = spriteView;
    }
}
