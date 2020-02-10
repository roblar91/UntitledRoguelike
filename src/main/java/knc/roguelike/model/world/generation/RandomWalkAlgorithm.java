/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.world.generation;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import knc.roguelike.model.entity.Entity;
import knc.roguelike.model.entity.Position;
import knc.roguelike.model.entity.component.*;
import knc.roguelike.model.world.Area;

public class RandomWalkAlgorithm implements Algorithm {
    @Override
    public Area generateArea(int depth) {
        int sizeX = Generator.rng.nextInt(50) + 25;
        int sizeY = Generator.rng.nextInt(70) + 50;
        int openSpace = 0;
        int openSpaceTarget = (sizeX * sizeY) * (Generator.rng.nextInt(30) + 10) / 100;

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

        // Use the blueprint to create tiles
        var wallImage = new Image("sprites/curses/curses_16x16_35.png");
        var groundImage = new Image("sprites/curses/curses_16x16_250.png");
        var entranceImage = new Image("sprites/curses/curses_16x16_62.png");
        var exitImage = new Image("sprites/curses/curses_16x16_60.png");
        var area = new Area(sizeX, sizeY);

        for(int x=0; x<sizeX; x++) {
            for(int y=0; y<sizeY; y++) {
                switch(blueprint[x][y]){
                    case WALL:
                        createWall(wallImage, area, x, y);
                        break;
                    case GROUND:
                        createGround(groundImage, area, x, y);
                        break;
                    case ENTRANCE:
                        createEntrance(entranceImage, area, x, y);
                        break;
                    case EXIT:
                        createExit(exitImage, area, x, y);
                        break;
                }
            }
        }

        // Add entrance

        return area;
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

    private void createExit(Image image, Area area, int posX, int posY) {
        var position = new Position(area, posX, posY);
        var entity = new Entity(position);
        entity.addComponent(new SpriteComponent(image));
        entity.addComponent(new TerrainComponent());
        entity.addComponent(new ExitComponent());
        entity.addComponent(new BackgroundComponent(Color.DARKSLATEBLUE));
        area.getTile(posX, posY).addEntity(entity);
    }

    private void createEntrance(Image image, Area area, int posX, int posY) {
        var position = new Position(area, posX, posY);
        var entity = new Entity(position);
        entity.addComponent(new SpriteComponent(image));
        entity.addComponent(new TerrainComponent());
        entity.addComponent(new EntranceComponent());
        entity.addComponent(new BackgroundComponent(Color.DARKSLATEBLUE));
        area.getTile(posX, posY).addEntity(entity);
    }

    private void createWall(Image image, Area area, int posX, int posY) {
        var position = new Position(area, posX, posY);
        var entity = new Entity(position);
        entity.addComponent(new SpriteComponent(image, Color.DARKSLATEGRAY));
        entity.addComponent(new BackgroundComponent(Color.BLACK));
        entity.addComponent(new SolidComponent());
        entity.addComponent(new TerrainComponent());
        area.getTile(posX, posY).addEntity(entity);
    }

    private void createGround(Image image, Area area, int posX, int posY) {
        var position = new Position(area, posX, posY);
        var entity = new Entity(position);
        entity.addComponent(new SpriteComponent(image));
        entity.addComponent(new TerrainComponent());
        entity.addComponent(new BackgroundComponent(Color.DARKSLATEBLUE));
        area.getTile(posX, posY).addEntity(entity);
    }

    private enum TerrainType {
        NONE,
        WALL,
        GROUND,
        ENTRANCE,
        EXIT;
    }
}
