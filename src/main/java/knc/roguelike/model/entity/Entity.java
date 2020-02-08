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
import knc.roguelike.model.entity.component.Component;
import knc.roguelike.model.entity.component.Type;
import knc.roguelike.model.world.Area;

import java.util.ArrayList;

public class Entity {
    private ArrayList<Component> components = new ArrayList<>();
    private ImageView imageView;
    private Position position;
    private Image image;
    private Color color;

    public Entity(Position position, Image image) {
        this.position = position;
        this.image = image;
        this.imageView = new ImageView(image);
    }

    public Entity(Position position, Image image, Color color) {
        this(position, image);
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

    public void addComponent(Component component) {
        components.add(component);
    }

    public Component getComponent(Type type) {
        for(Component component : components) {
            if(component.getType() == type) {
                return component;
            }
        }

        return null;
    }

    public Area getArea() {
        return position.area;
    }

    public int getPosX() {
        return position.posX;
    }

    public int getPosY() {
        return position.posY;
    }

    public void setPosition(int x, int y) {
        this.position.posX = x;
        this.position.posY = y;
    }
}
