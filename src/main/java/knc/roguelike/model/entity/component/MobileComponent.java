/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

public class MobileComponent extends Component {
    @Override
    public Type getType() {
        return Type.MOBILE;
    }
}
