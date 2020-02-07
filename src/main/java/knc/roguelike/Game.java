package knc.roguelike;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
    public static Stage stage;
    public static Scene scene;
    public static Pane mainPane;
    public static Pane statusPane;
    public static Pane toolPane;

    public void start(Stage stage) throws Exception {
        Game.stage = stage;
        var root = new Group();
        Game.scene = new Scene(root);
        Game.stage.setScene(Game.scene);
        Game.stage.setTitle("Untitled Roguelike");
        Game.stage.show();
    }
}
