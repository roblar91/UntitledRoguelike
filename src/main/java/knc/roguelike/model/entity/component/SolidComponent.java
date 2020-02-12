/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

import knc.roguelike.model.entity.Entity;

public class SolidComponent extends Component {
    public SolidComponent(Entity owner) {
        super(owner);
    }

    @Override
    public Type getType() {
        return Type.SOLID;
    }
}
