/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

import knc.roguelike.model.entity.Entity;

public class PlayerComponent extends Component {
    public PlayerComponent(Entity owner) {
        super(owner);
    }

    @Override
    public Type getType() {
        return Type.PLAYER;
    }
}
