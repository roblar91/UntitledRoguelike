/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

import knc.roguelike.model.entity.Entity;

public class MobileComponent extends Component {
    public MobileComponent(Entity owner) {
        super(owner);
    }

    @Override
    public Type getType() {
        return Type.MOBILE;
    }
}
