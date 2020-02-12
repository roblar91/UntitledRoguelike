/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

import knc.roguelike.model.entity.Entity;

public class HealthComponent extends Component {
    private int maxHP;
    private int currentHP;

    public HealthComponent(Entity owner, int maxHP) {
        super(owner);
        this.maxHP = maxHP;
        this.currentHP = maxHP;
    }

    public HealthComponent(Entity owner) {
        this(owner, 100);
    }

    @Override
    public Type getType() {
        return Type.HEALTH;
    }
}
