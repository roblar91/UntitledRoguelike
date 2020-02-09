/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.engine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import knc.roguelike.action.Action;
import knc.roguelike.input.KeyboardHandler;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.Position;
import knc.roguelike.model.entity.component.*;
import knc.roguelike.model.world.Area;
import knc.roguelike.view.ViewPort;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Game extends Application {
    private Queue<Action> actionQueue = new LinkedBlockingQueue<>();
    private Stage stage;
    private Scene currentScene;
    private ViewPort viewPort;
    private Area currentArea;
    private Entity player;
    private KeyboardHandler keyboardHandler;
    private Pane mainPane;
    private Pane statusPane;
    private Pane actionBar;

    public void start(Stage stage) throws Exception {
        this.stage = stage;

        createTestArea();
        initView();
        initInput();

        new GameLoop(this).start();
    }

    public boolean hasActionsQueued() {
        return !actionQueue.isEmpty();
    }

    public void queueAction(Action action) {
        actionQueue.add(action);
    }

    public Action getNextAction() {
        return actionQueue.remove();
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

    private void initView() {
        viewPort = new ViewPort(currentArea, 81, 51, 16);
        viewPort.setFollowTarget(player);
        viewPort.updateAll();

        mainPane = new AnchorPane(viewPort);

        var con = new TextArea("Welcome to Untitled Roguelike!");
        con.setEditable(false);
        con.setDisable(true);
        AnchorPane.setLeftAnchor(con, 0.0);
        AnchorPane.setBottomAnchor(con, 0.0);
        mainPane.getChildren().add(con);

        actionBar = new Pane();
        actionBar.setPrefHeight(150);
        actionBar.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, null, null)));

        statusPane = new Pane();
        statusPane.setPrefWidth(300);
        statusPane.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, null, null)));

        var right = new VBox(mainPane, actionBar);
        var root = new HBox(statusPane, right);

        currentScene = new Scene(root);
        currentScene.getStylesheets().add("css/console.css");

        stage.setScene(currentScene);
        stage.setTitle("Untitled Roguelike");
        stage.show();
    }

    private void initInput() {
        keyboardHandler = new KeyboardHandler(this);
        keyboardHandler.setInputDefault();
    }

    private void createTestArea() {
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
        wall1.addComponent(new BackgroundComponent(Color.LIGHTGRAY));
        wall1.addComponent(new SolidComponent());
        wall1.addComponent(new TerrainComponent());
        tiles[wallPosition1.posX.get()][wallPosition1.posY.get()].removeAllEntities();
        tiles[wallPosition1.posX.get()][wallPosition1.posY.get()].addEntity(wall1);

        var wallPosition2 = new Position(currentArea, 2, 4);
        var wall2 = new Entity(wallPosition2);
        wall2.addComponent(new SpriteComponent(wallImage));
        wall2.addComponent(new BackgroundComponent(Color.LIGHTGRAY));
        wall2.addComponent(new SolidComponent());
        wall2.addComponent(new TerrainComponent());
        tiles[wallPosition2.posX.get()][wallPosition2.posY.get()].removeAllEntities();
        tiles[wallPosition2.posX.get()][wallPosition2.posY.get()].addEntity(wall2);

        var wallPosition3 = new Position(currentArea, 1, 5);
        var wall3 = new Entity(wallPosition3);
        wall3.addComponent(new SpriteComponent(wallImage));
        wall3.addComponent(new BackgroundComponent(Color.LIGHTGRAY));
        wall3.addComponent(new SolidComponent());
        wall3.addComponent(new TerrainComponent());
        tiles[wallPosition3.posX.get()][wallPosition3.posY.get()].removeAllEntities();
        tiles[wallPosition3.posX.get()][wallPosition3.posY.get()].addEntity(wall3);
    }
}
