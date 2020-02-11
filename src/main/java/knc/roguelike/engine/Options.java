/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.engine;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public final class Options {
    public static IntegerProperty viewTileSize = new SimpleIntegerProperty(24);
    public static IntegerProperty viewHeight = new SimpleIntegerProperty(400);
    public static IntegerProperty viewWidth = new SimpleIntegerProperty(600);

    private Options() {}
}
