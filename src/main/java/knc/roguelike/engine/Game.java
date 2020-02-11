/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import knc.roguelike.model.entity.action.Action;
import knc.roguelike.input.KeyboardHandler;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.Position;
import knc.roguelike.model.entity.component.*;
import knc.roguelike.model.world.Area;
import knc.roguelike.model.world.generation.Generator;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Game extends Application {
    private Queue<Action> actionQueue = new LinkedBlockingQueue<>();
    private Stage stage;
    private Scene currentScene;
    private Area currentArea;
    private Entity player;
    private KeyboardHandler keyboardHandler;
    private Controller controller;
    private Parent root;

    public void start(Stage stage) throws Exception {
        this.stage = stage;

        initArea();
        initView();
        initInput();

        currentArea.setCameraFollowTarget(player);

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
        player.addComponent(new PlayerComponent());
        player.addComponent(new HealthComponent());
        player.addComponent(new AttributesComponent());

        currentArea.getTile(playerPosition.posX.get(), playerPosition.posY.get()).addEntity(player);
    }

    private void initView() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("/fxml/root.fxml"));
        root = loader.load();
        controller = loader.getController();
        controller.initialize(currentArea);

        currentScene = new Scene(root);
        currentScene.getStylesheets().add("css/console.css");

        Options.actualViewHeight.bind(controller.mainPane.heightProperty());
        Options.actualViewWidth.bind(controller.mainPane.widthProperty());

        stage.setScene(currentScene);
        stage.setTitle("Untitled Roguelike");
        stage.show();
    }

    private void initInput() {
        keyboardHandler = new KeyboardHandler(this);
        keyboardHandler.setInputDefault();
    }
}
