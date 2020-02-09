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
import knc.roguelike.model.entity.action.Action;
import knc.roguelike.input.KeyboardHandler;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.Position;
import knc.roguelike.model.entity.component.*;
import knc.roguelike.model.world.Area;
import knc.roguelike.model.world.generation.Generator;
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

        initArea();
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

    private void initArea() {
        currentArea = Generator.getArea(1);
        var entrance = currentArea.getEntrance();
        var playerPosition = new Position(currentArea, entrance.position.posX.get(), entrance.position.posY.get());
        player = new Entity(playerPosition);
        player.addComponent(new SpriteComponent(new Image("sprites/curses/curses_16x16_1.png"), Color.YELLOW));
        player.addComponent(new AliveComponent());
        player.addComponent(new SolidComponent());
        player.addComponent(new MobileComponent());
        currentArea.getTile(playerPosition.posX.get(), playerPosition.posY.get()).addEntity(player);
    }

    private void initView() {
        viewPort = new ViewPort(currentArea, 51, 31, 24);
        viewPort.setFollowTarget(player);

        mainPane = new AnchorPane(viewPort);

        var con = new TextArea("Welcome to Untitled Roguelike!");
        con.setEditable(false);
        con.setDisable(true);
        AnchorPane.setLeftAnchor(con, 0.0);
        AnchorPane.setBottomAnchor(con, 0.0);
        mainPane.getChildren().add(con);

        actionBar = new Pane();
        actionBar.setPrefHeight(150);
        actionBar.setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));

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
}
