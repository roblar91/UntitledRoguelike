/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world.generation;

import knc.roguelike.model.world.Area;

interface Algorithm {
    Area generateArea(int depth);
}
