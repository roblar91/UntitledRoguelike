/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

/**
 * A component represents a characteristic of an object.
 */
public interface Component {
    Type getType();
}
