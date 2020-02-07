package knc.roguelike.model.entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import knc.roguelike.model.world.Area;

public abstract class Entity {
    private ImageView spriteView;
    private Area area;
    private int posX;
    private int posY;

    public Entity(Image spriteImage, Area area, int posX, int posY) {
        this.spriteView = new ImageView(spriteImage);
        this.area = area;
        this.posX = posX;
        this.posY = posY;
    }

    public ImageView getSpriteView() {
        return spriteView;
    }

    public void setSpriteView(ImageView spriteView) {
        this.spriteView = spriteView;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
