/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.engine;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import knc.roguelike.model.world.Area;

public class Controller {
    @FXML
    public Pane mainPane;
    @FXML
    public Pane statusPane;
    @FXML
    public Pane actionPane;

    public TextArea console;

    public void initialize(Area area) {
        mainPane.getChildren().add(area);

        var clip = new Rectangle();
        clip.widthProperty().bind(mainPane.widthProperty());
        clip.heightProperty().bind(mainPane.heightProperty());
        mainPane.setClip(clip);

        console = new TextArea("Welcome to Untitled Roguelike!");
        console.setEditable(false);
        console.setDisable(true);
        AnchorPane.setLeftAnchor(console, 0.0);
        AnchorPane.setBottomAnchor(console, 0.0);
        mainPane.getChildren().add(console);
    }
}
