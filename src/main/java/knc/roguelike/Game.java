/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import knc.roguelike.model.entity.Attitude;
import knc.roguelike.model.entity.Creature;
import knc.roguelike.model.entity.Ground;
import knc.roguelike.model.world.Area;
import knc.roguelike.model.world.Tile;
import knc.roguelike.renderer.Renderer;

public class Game extends Application {
    public static Stage stage;
    public static Scene scene;
    public static Pane mainPane;
    public static Pane statusPane;
    public static Pane toolPane;

    public void start(Stage stage) throws Exception {
        var area = createTestArea();
        var root = new Renderer(area, 50, 50, 16);
        root.render();
        Game.stage = stage;
        Game.scene = new Scene(root);
        Game.stage.setScene(Game.scene);
        Game.stage.setTitle("Untitled Roguelike");
        Game.stage.setAlwaysOnTop(true);
        Game.stage.show();
    }

    private Area createTestArea() {
        var area = new Area(50, 50);
        var player = new Creature(new Image("sprites/curses/curses_16x16_1.png"), Attitude.PLAYER);
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
