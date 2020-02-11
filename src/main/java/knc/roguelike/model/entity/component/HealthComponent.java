/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

public class HealthComponent extends Component {
    private int maxHP;
    private int currentHP;

    public HealthComponent(int maxHP) {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
    }

    public HealthComponent() {
        this(100);
    }

    @Override
    public Type getType() {
        return Type.HEALTH;
    }
}
