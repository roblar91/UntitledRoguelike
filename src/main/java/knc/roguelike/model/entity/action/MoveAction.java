/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.action;

import knc.roguelike.exception.IllegalActionException;
import knc.roguelike.model.entity.Entity;

public class MoveAction implements Action {
    private final Entity target;
    private final int dX;
    private final int dY;

    public MoveAction(Entity target, int dX, int dY) {
        this.target = target;
        this.dX = dX;
        this.dY = dY;
    }

    @Override
    public void execute() throws IllegalActionException {
        target.move(dX, dY);
    }
}
