/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world.generation;

interface Algorithm {
    TerrainType[][] generateBlueprint(int depth);
}
