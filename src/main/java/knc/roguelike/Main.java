/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike;

import javafx.application.Application;
import knc.roguelike.engine.Game;

public class Main {
    public static void main(String[] args) {
        Application.launch(Game.class, args);
    }
}
