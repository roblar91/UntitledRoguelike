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
import knc.roguelike.engine.Options;
import knc.roguelike.model.entity.Entity;

public class SpriteComponent extends Component {
    private ImageView imageView;
    private Image image;
    private Color color;

    public SpriteComponent(Entity owner, Image image) {
        this(owner, image, null);
    }

    public SpriteComponent(Entity owner, Image image, Color color) {
        super(owner);
        this.image = image;
        this.imageView = new ImageView(image);
        this.color = color;

        Options.tileSize.addListener((obs, oldV, newV) -> resize());
        resize();
    }

    public ImageView getImageView() {
        return imageView;
    }

    private void resize() {
        imageView.setFitHeight(Options.tileSize.get());
        imageView.setFitWidth(Options.tileSize.get());
        applyColorBlend();
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

    @Override
    public Type getType() {
        return Type.SPRITE;
    }
}
