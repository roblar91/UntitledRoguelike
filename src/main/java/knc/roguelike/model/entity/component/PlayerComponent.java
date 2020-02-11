/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

public class PlayerComponent extends Component {
    @Override
    public Type getType() {
        return Type.PLAYER;
    }
}
