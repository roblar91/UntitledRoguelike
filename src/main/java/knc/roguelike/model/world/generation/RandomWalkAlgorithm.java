/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world.generation;

public class RandomWalkAlgorithm implements Algorithm {
    @Override
    public TerrainType[][] generateBlueprint(int depth) {
        int sizeX = Generator.rng.nextInt(25) + 50;
        int sizeY = Generator.rng.nextInt(50) + 70;
        int openSpace = 0;
        int openSpaceTarget = (sizeX * sizeY) * (Generator.rng.nextInt(20) + 20) / 100;

        // Initialize all tiles to walls
        var blueprint = new TerrainType[sizeX][sizeY];
        for(int x=0; x<sizeX; x++) {
            for(int y=0; y<sizeY; y++) {
                blueprint[x][y] = TerrainType.NONE;
            }
        }

        // Make paths
        int currentX = Generator.rng.nextInt(sizeX-2) + 1;
        int currentY = Generator.rng.nextInt(sizeY-2) + 1;
        blueprint[currentX][currentY] = TerrainType.ENTRANCE;

        while(openSpace < openSpaceTarget) {
            if(blueprint[currentX][currentY] == TerrainType.NONE) {
                blueprint[currentX][currentY] = TerrainType.GROUND;
                openSpace++;
                if(openSpace == openSpaceTarget){
                    blueprint[currentX][currentY] = TerrainType.EXIT;
                }
            }

            int direction = Generator.rng.nextInt(4);
            switch(direction) {
                case 0:
                    if(currentX > 1)
                        currentX--;
                    break;
                case 1:
                    if(currentX < sizeX - 2)
                        currentX++;
                    break;
                case 2:
                    if(currentY > 1)
                        currentY--;
                    break;
                case 3:
                    if(currentY < sizeY -2)
                        currentY++;
                    break;
            }
        }

        for(int x=0; x<sizeX; x++) {
            for(int y=0; y<sizeY; y++) {
                if(blueprint[x][y] == TerrainType.GROUND
                    || blueprint[x][y] == TerrainType.ENTRANCE
                    || blueprint[x][y] == TerrainType.EXIT)
                    addWallsAdjacentToGround(blueprint, x, y);
            }
        }

        return blueprint;
    }

    private void addWallsAdjacentToGround(TerrainType[][] blueprint, int x, int y) {
        checkWall(blueprint, x-1, y-1);
        checkWall(blueprint, x-1, y);
        checkWall(blueprint, x-1, y+1);
        checkWall(blueprint, x, y-1);
        checkWall(blueprint, x, y);
        checkWall(blueprint, x, y+1);
        checkWall(blueprint, x+1, y-1);
        checkWall(blueprint, x+1, y);
        checkWall(blueprint, x+1, y+1);
    }

    private void checkWall(TerrainType[][] blueprint, int x, int y) {
        if(blueprint[x][y] == TerrainType.NONE) {
            blueprint[x][y] = TerrainType.WALL;
        }
    }

}
