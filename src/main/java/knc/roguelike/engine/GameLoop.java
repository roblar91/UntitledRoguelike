/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.engine;

import javafx.animation.AnimationTimer;

/**
 * The main game loop.
 */
class GameLoop extends AnimationTimer {
    private Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void handle(long now) {
        var update = false;
        while(game.hasActionsQueued()) {
            try {
                game.getNextAction().execute();
                update = true;
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if(update) {
            game.getViewPort().updateAll();
        }
        game.getKeyboardHandler().setInputDefault();
    }
}
