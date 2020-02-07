package knc.roguelike.renderer;

import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import knc.roguelike.model.world.Tile;

/**
 * A graphical representation of a {@link knc.roguelike.model.world.Tile} meant to be used by {@link knc.roguelike.renderer.Renderer}.
 */
class ViewTile extends Pane {
    /**
     *
     * @param size The horizontal and vertical size of the tile in pixels
     */
    ViewTile(int size) {
        setPrefSize(size, size);
        setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
    }

    /**
     * Render the provided {@link knc.roguelike.model.world.Tile} by getting the {@link knc.roguelike.model.entity.Ground},
     * {@link knc.roguelike.model.entity.Creature} and {@link knc.roguelike.model.entity.Item} contained in the provided tile.
     * @param tile The tile that should be displayed
     */
    void setTile(Tile tile) {
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
