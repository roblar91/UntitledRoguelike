/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class SpriteComponent implements Component {
    private ImageView imageView;
    private Image image;
    private Color color;

    public SpriteComponent(Image image) {
        this.image = image;
        this.imageView = new ImageView(image);
    }

    public SpriteComponent(Image image, Color color) {
        this(image);
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

    @Override
    public Type getType() {
        return Type.SPRITE;
    }
}
