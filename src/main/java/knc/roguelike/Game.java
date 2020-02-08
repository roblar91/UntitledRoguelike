/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import knc.roguelike.input.KeyboardHandler;
import knc.roguelike.model.entity.Attitude;
import knc.roguelike.model.entity.Creature;
import knc.roguelike.model.entity.Ground;
import knc.roguelike.model.world.Area;
import knc.roguelike.model.world.Tile;
import knc.roguelike.renderer.Renderer;

public class Game extends Application {
    public void start(Stage stage) throws Exception {
        var area = createTestArea();
        var root = new Renderer(area, 25, 25, 32);
        root.render();
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Untitled Roguelike");
        stage.show();
        var keyboardHandler = new KeyboardHandler(scene);
    }

    private Area createTestArea() {
        var area = new Area(50, 50);
        var player = new Creature(new Image("sprites/curses/curses_16x16_1.png"), Attitude.PLAYER, Color.YELLOW);
        var tiles = area.getTiles();
        for(Tile[] column: tiles){
            for(Tile tile: column){
                var ground = new Ground(new Image("sprites/curses/curses_16x16_250.png"));
                ground.setBackgroundColor(Color.BROWN);
                tile.setGround(ground);
            }
        }
        area.getTile(2, 2).setCreature(player);
        return area;
    }
}
