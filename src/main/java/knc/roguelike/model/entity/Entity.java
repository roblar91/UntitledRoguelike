package knc.roguelike.model.entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {
    private ImageView spriteView;

    public Entity(Image spriteImage) {
        this.spriteView = new ImageView(spriteImage);
    }

    public ImageView getSpriteView() {
        return spriteView;
    }

    public void setSpriteView(ImageView spriteView) {
        this.spriteView = spriteView;
    }
}
