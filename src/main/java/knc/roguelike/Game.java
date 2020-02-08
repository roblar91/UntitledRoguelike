/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import knc.roguelike.action.ActionQueue;
import knc.roguelike.input.KeyboardHandler;
import knc.roguelike.model.entity.Attitude;
import knc.roguelike.model.entity.Creature;
import knc.roguelike.model.entity.Ground;
import knc.roguelike.model.world.Area;
import knc.roguelike.renderer.Renderer;


public class Game extends Application {
    private Stage stage;
    private Scene currentScene;
    private Renderer gamePane;
    private Area currentArea;
    private Creature player;
    private KeyboardHandler keyboardHandler;

    public void start(Stage stage) throws Exception {
        player = new Creature(new Image("sprites/curses/curses_16x16_1.png"), Attitude.PLAYER, Color.YELLOW);

        // Create a test area
        currentArea = new Area(50, 50);
        var tiles = currentArea.getTiles();
        for(int x=0; x < tiles.length; x++) {
            for(int y=0; y < tiles[0].length; y++) {
                var ground = new Ground(new Image("sprites/curses/curses_16x16_250.png"));
                ground.setBackgroundColor(Color.BROWN);
                ground.setPosition(x, y);
                tiles[x][y].setGround(ground);
            }
        }
        currentArea.getTile(2, 2).setCreature(player);
        player.setPosition(2, 2);

        gamePane = new Renderer(currentArea, 25, 25, 32);
        gamePane.render();
        currentScene = new Scene(gamePane);

        stage.setScene(currentScene);
        stage.setTitle("Untitled Roguelike");
        stage.show();

        keyboardHandler = new KeyboardHandler(this);
        keyboardHandler.setInputDefault();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                var update = false;
                while(!ActionQueue.actions.isEmpty()) {
                    ActionQueue.actions.poll().execute();
                    update = true;
                }
                if(update) {
                    gamePane.render();
                }
                keyboardHandler.setInputDefault();
            }
        }.start();
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public Creature getPlayer() {
        return player;
    }
}
