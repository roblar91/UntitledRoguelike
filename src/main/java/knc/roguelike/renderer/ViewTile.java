package knc.roguelike.renderer;

import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import knc.roguelike.model.world.Tile;

public class ViewTile extends Pane {
    public ViewTile(int size) {
        setPrefSize(size, size);
        setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
    }

    public void setTile(Tile tile) {
        getChildren().clear();

        if(tile == null) {
            return;
        }

        if(tile.hasGround()){
            var ground = tile.getGround().getSpriteView();
            ground.setFitWidth(getPrefWidth());
            ground.setFitHeight(getPrefHeight());
            getChildren().add(ground);
        }

        if(tile.hasCreature()){
            var creature = tile.getCreature().getSpriteView();
            creature.setFitWidth(getPrefWidth());
            creature.setFitHeight(getPrefHeight());
            getChildren().add(creature);
        }
    }
}
