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
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.Position;
import knc.roguelike.model.entity.component.*;
import knc.roguelike.model.world.Area;
import knc.roguelike.renderer.Renderer;


public class Game extends Application {
    private Stage stage;
    private Scene currentScene;
    private Renderer gamePane;
    private Area currentArea;
    private Entity player;
    private KeyboardHandler keyboardHandler;

    public void start(Stage stage) throws Exception {

        // Create a test area
        currentArea = new Area(50, 50);
        var tiles = currentArea.getTiles();
        for(int x=0; x < tiles.length; x++) {
            for(int y=0; y < tiles[0].length; y++) {
                var position = new Position(currentArea, x, y);
                var entity = new Entity(position);
                entity.addComponent(new SpriteComponent(new Image("sprites/curses/curses_16x16_250.png")));
                entity.addComponent(new TerrainComponent());
                entity.addComponent(new BackgroundComponent(Color.BROWN));
                tiles[x][y].addEntity(entity);
            }
        }

        var playerPosition = new Position(currentArea, 5, 5);
        player = new Entity(playerPosition);
        player.addComponent(new SpriteComponent(new Image("sprites/curses/curses_16x16_1.png"), Color.YELLOW));
        player.addComponent(new LivingComponent());
        player.addComponent(new SolidComponent());
        tiles[player.position.posX][player.position.posY].addEntity(player);

        gamePane = new Renderer(currentArea, 25, 25, 32);
        gamePane.renderAll();
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
                    gamePane.renderAll();
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

    public Entity getPlayer() {
        return player;
    }
}
