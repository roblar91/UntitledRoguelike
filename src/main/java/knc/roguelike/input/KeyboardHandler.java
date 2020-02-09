/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.input;

import javafx.scene.input.KeyCode;
import knc.roguelike.engine.Game;
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
                var move = new MoveAction(game.getPlayer(), 0, -1);
                game.queueAction(move);
            }
            else if(code == KeyCode.DOWN || code == KeyCode.NUMPAD2) {
                var move = new MoveAction(game.getPlayer(), 0, 1);
                game.queueAction(move);
            }
            else if(code == KeyCode.LEFT || code == KeyCode.NUMPAD4) {
                var move = new MoveAction(game.getPlayer(), -1, 0);
                game.queueAction(move);
            }
            else if(code == KeyCode.RIGHT || code == KeyCode.NUMPAD6) {
                var move = new MoveAction(game.getPlayer(), 1, 0);
                game.queueAction(move);
            }
            else if(code == KeyCode.NUMPAD7) {
                var move = new MoveAction(game.getPlayer(), -1, -1);
                game.queueAction(move);
            }
            else if(code == KeyCode.NUMPAD9) {
                var move = new MoveAction(game.getPlayer(), 1, -1);
                game.queueAction(move);
            }
            else if(code == KeyCode.NUMPAD1) {
                var move = new MoveAction(game.getPlayer(), -1, 1);
                game.queueAction(move);
            }
            else if(code == KeyCode.NUMPAD3) {
                var move = new MoveAction(game.getPlayer(), 1, 1);
                game.queueAction(move);
            }
            else if(code == KeyCode.T) {
                if(game.getViewPort().getFollowTarget() == null)
                    game.getViewPort().setFollowTarget(game.getPlayer());
                else
                    game.getViewPort().setFollowTarget(null);
            }

            if(game.hasActionsQueued()) {
                setInputBlocked();
            }
        });
    }
}
