/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.action;

/**
 * An {@link Action} is an encapsulated behaviour that can be executed at a later time.
 */
public interface Action {
    void execute() throws Exception;
}
