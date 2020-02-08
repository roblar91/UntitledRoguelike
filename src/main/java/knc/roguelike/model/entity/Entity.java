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
    private ImageView imageView;
    private Image image;
    private Color color;
    private int posX;
    private int posY;

    public Entity(Image image, Color color) {
        this.imageView = new ImageView(image);
        this.image = image;
        this.color = color;

        this.imageView.fitHeightProperty().addListener((obs, oldV, newV) -> applyColorBlend());
        this.applyColorBlend();
    }

    private void applyColorBlend() {
        if(color != null) {
            var clip = new ImageView(image);
            clip.setFitHeight(imageView.getFitHeight());
            clip.setFitWidth(imageView.getFitWidth());
            imageView.setClip(clip);

            var blackout = new ColorAdjust();
            blackout.setBrightness(-1);
            blackout.setSaturation(-1);
            blackout.setHue(-1);

            var coloring = new ColorInput(0, 0, imageView.getFitWidth(), imageView.getFitHeight(), color);

            var blend = new Blend(BlendMode.ADD, blackout, coloring);

            this.imageView.setEffect(blend);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setPosition(int x, int y) {
        posX = x;
        posY = y;
    }

    public void move(int dX, int dY) {
        posX += dX;
        posY += dY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
