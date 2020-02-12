/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.engine;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public final class Options {
    public static IntegerProperty tileSize = new SimpleIntegerProperty(24);
    public static IntegerProperty actualViewHeight = new SimpleIntegerProperty();
    public static IntegerProperty actualViewWidth = new SimpleIntegerProperty();

    public static IntegerProperty targetTotalHeight = new SimpleIntegerProperty(800);
    public static IntegerProperty targetTotalWidth = new SimpleIntegerProperty(1200);

    private Options() {}
}
