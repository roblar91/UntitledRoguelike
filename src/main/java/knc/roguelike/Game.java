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
import knc.roguelike.view.ViewPort;


public class Game extends Application {
    private Stage stage;
    private Scene currentScene;
    private ViewPort viewPort;
    private Area currentArea;
    private Entity player;
    private KeyboardHandler keyboardHandler;

    public void start(Stage stage) throws Exception {

        // Create a test area
        currentArea = new Area(10, 10);
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
        player.addComponent(new AliveComponent());
        player.addComponent(new SolidComponent());
        player.addComponent(new MobileComponent());
        tiles[player.position.posX.get()][player.position.posY.get()].addEntity(player);

        var wallImage = new Image("sprites/curses/curses_16x16_35.png");
        var wallPosition1 = new Position(currentArea, 2, 3);
        var wall1 = new Entity(wallPosition1);
        wall1.addComponent(new SpriteComponent(wallImage));
        wall1.addComponent(new BackgroundComponent(Color.GRAY));
        wall1.addComponent(new SolidComponent());
        wall1.addComponent(new TerrainComponent());
        tiles[wallPosition1.posX.get()][wallPosition1.posY.get()].removeAllEntities();
        tiles[wallPosition1.posX.get()][wallPosition1.posY.get()].addEntity(wall1);

        var wallPosition2 = new Position(currentArea, 2, 4);
        var wall2 = new Entity(wallPosition2);
        wall2.addComponent(new SpriteComponent(wallImage));
        wall2.addComponent(new BackgroundComponent(Color.GRAY));
        wall2.addComponent(new SolidComponent());
        wall2.addComponent(new TerrainComponent());
        tiles[wallPosition2.posX.get()][wallPosition2.posY.get()].removeAllEntities();
        tiles[wallPosition2.posX.get()][wallPosition2.posY.get()].addEntity(wall2);

        var wallPosition3 = new Position(currentArea, 1, 5);
        var wall3 = new Entity(wallPosition3);
        wall3.addComponent(new SpriteComponent(wallImage));
        wall3.addComponent(new BackgroundComponent(Color.GRAY));
        wall3.addComponent(new SolidComponent());
        wall3.addComponent(new TerrainComponent());
        tiles[wallPosition3.posX.get()][wallPosition3.posY.get()].removeAllEntities();
        tiles[wallPosition3.posX.get()][wallPosition3.posY.get()].addEntity(wall3);


        viewPort = new ViewPort(currentArea, 25, 25, 32);
        viewPort.setFollowTarget(player);
        viewPort.updateAll();
        currentScene = new Scene(viewPort);

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
                    try {
                        ActionQueue.actions.poll().execute();
                        update = true;
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                if(update) {
                    viewPort.updateAll();
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

    public ViewPort getViewPort() {
        return viewPort;
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public Entity getPlayer() {
        return player;
    }

    public KeyboardHandler getKeyboardHandler() {
        return keyboardHandler;
    }
}
