/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.action;

import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.world.Area;

public class MoveAction implements Action {
    private final Area actor;
    private final Entity target;
    private final int dX;
    private final int dY;

    public MoveAction(Area actor, Entity target, int dX, int dY) {
        this.actor = actor;
        this.target = target;
        this.dX = dX;
        this.dY = dY;
    }

    @Override
    public void execute() {
        actor.move(target, dX, dY);
    }
}
