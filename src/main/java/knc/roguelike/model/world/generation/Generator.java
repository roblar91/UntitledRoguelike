/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world.generation;

import knc.roguelike.model.world.Area;

import java.util.Random;

public final class Generator {
    public static final Random rng = new Random();
    private static final int TOTAL_ALGORITHM_COUNT = 1;
    private Generator(){}

    public static Area getArea(int depth) {
        Algorithm algorithm = getRandomAlgorithm();
        return algorithm.generateArea(depth);
    }

    private static Algorithm getRandomAlgorithm() {
        int i = rng.nextInt(TOTAL_ALGORITHM_COUNT);

        switch(i){
            case 0:
                return new RandomWalkAlgorithm();
            case 1:
                return new CellularAutomataAlgorithm();
            case 2:
                return new BinarySearchTreeAlgorithm();
        }

        return null;
    }
}
