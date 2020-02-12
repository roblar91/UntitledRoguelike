/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import knc.roguelike.model.entity.Entity;

public class BackgroundComponent extends Component {
    private Background background;

    public BackgroundComponent(Entity owner) {
        this(owner, Color.BLACK);
    }

    public BackgroundComponent(Entity owner, Color backgroundColor) {
        super(owner);
        setBackgroundColor(backgroundColor);
    }

    public void setBackgroundColor(Color color) {
        background = new Background(new BackgroundFill(color, null, null));
    }

    public Background getBackground() {
        return background;
    }

    @Override
    public Type getType() {
        return Type.BACKGROUND;
    }
}
