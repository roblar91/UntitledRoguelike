/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

import knc.roguelike.model.entity.Entity;

public class HostileComponent extends Component {
    public HostileComponent(Entity owner) {
        super(owner);
    }

    @Override
    public Type getType() {
        return Type.HOSTILE;
    }
}
