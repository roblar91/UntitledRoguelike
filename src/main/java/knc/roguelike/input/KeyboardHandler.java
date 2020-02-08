/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.input;

import javafx.scene.input.KeyCode;
import knc.roguelike.Game;
import knc.roguelike.action.ActionQueue;
import knc.roguelike.action.MoveAction;

public class KeyboardHandler {
    private Game game;

    public KeyboardHandler(Game game) {
        this.game = game;
    }

    public void setInputBlocked() {
        game.getCurrentScene().setOnKeyPressed(keyEvent -> {});
    }

    public void setInputDefault() {
        game.getCurrentScene().setOnKeyPressed(keyEvent -> {
            var code = keyEvent.getCode();

            if(code == KeyCode.ESCAPE) {
                System.exit(0);
            }
            else if(code == KeyCode.UP || code == KeyCode.NUMPAD8) {
                var moveUp = new MoveAction(game.getCurrentArea(), game.getPlayer(), 0, -1);
                ActionQueue.actions.add(moveUp);
            }
            else if(code == KeyCode.DOWN || code == KeyCode.NUMPAD2) {
                var moveUp = new MoveAction(game.getCurrentArea(), game.getPlayer(), 0, 1);
                ActionQueue.actions.add(moveUp);
            }
            else if(code == KeyCode.LEFT || code == KeyCode.NUMPAD4) {
                var moveUp = new MoveAction(game.getCurrentArea(), game.getPlayer(), -1, 0);
                ActionQueue.actions.add(moveUp);
            }
            else if(code == KeyCode.RIGHT || code == KeyCode.NUMPAD6) {
                var moveUp = new MoveAction(game.getCurrentArea(), game.getPlayer(), 1, 0);
                ActionQueue.actions.add(moveUp);
            }
            else if(code == KeyCode.NUMPAD7) {
                var moveUp = new MoveAction(game.getCurrentArea(), game.getPlayer(), -1, -1);
                ActionQueue.actions.add(moveUp);
            }
            else if(code == KeyCode.NUMPAD9) {
                var moveUp = new MoveAction(game.getCurrentArea(), game.getPlayer(), 1, -1);
                ActionQueue.actions.add(moveUp);
            }
            else if(code == KeyCode.NUMPAD1) {
                var moveUp = new MoveAction(game.getCurrentArea(), game.getPlayer(), -1, 1);
                ActionQueue.actions.add(moveUp);
            }
            else if(code == KeyCode.NUMPAD3) {
                var moveUp = new MoveAction(game.getCurrentArea(), game.getPlayer(), 1, 1);
                ActionQueue.actions.add(moveUp);
            }

            if(!ActionQueue.actions.isEmpty()) {
                setInputBlocked();
            }
        });
    }
}
