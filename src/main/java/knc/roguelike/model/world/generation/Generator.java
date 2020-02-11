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

import java.util.Random;

public final class Generator {
    public static final Random rng = new Random();
    private static final int TOTAL_ALGORITHM_COUNT = 1;
    private Generator(){}

    public static Area getArea(int depth) {
        var algorithm = getRandomAlgorithm();
        var blueprint = algorithm.generateBlueprint(depth);
        return generateArea(blueprint);
    }

    private static Algorithm getRandomAlgorithm() {
        int i = rng.nextInt(TOTAL_ALGORITHM_COUNT);

        switch(i){
            case 0:
                return new RandomWalkAlgorithm();
            case 1:
                return new BinarySearchTreeAlgorithm();
            default:
                return new RandomWalkAlgorithm();
        }
    }

    private static Area generateArea(TerrainType[][] blueprint) {
        var sizeX = blueprint.length;
        var sizeY = blueprint[0].length;
        var area = new Area(sizeX, sizeY);

        for(int x=0; x<sizeX; x++) {
            for(int y=0; y<sizeY; y++) {
                switch(blueprint[x][y]){
                    case WALL:
                        createWall(area, x, y);
                        break;
                    case GROUND:
                        createGround(area, x, y);
                        break;
                    case ENTRANCE:
                        createEntrance(area, x, y);
                        break;
                    case EXIT:
                        createExit(area, x, y);
                        break;
                }
            }
        }

        return area;
    }


    private static void createExit(Area area, int posX, int posY) {
        var position = new Position(area, posX, posY);
        var entity = new Entity(position);
        entity.addComponent(new SpriteComponent(new Image("sprites/curses/curses_16x16_60.png")));
        entity.addComponent(new TerrainComponent());
        entity.addComponent(new ExitComponent());
        entity.addComponent(new BackgroundComponent(Color.DARKSLATEBLUE));
        area.getTile(posX, posY).addEntity(entity);
    }

    private static void createEntrance(Area area, int posX, int posY) {
        var position = new Position(area, posX, posY);
        var entity = new Entity(position);
        entity.addComponent(new SpriteComponent(new Image("sprites/curses/curses_16x16_62.png")));
        entity.addComponent(new TerrainComponent());
        entity.addComponent(new EntranceComponent());
        entity.addComponent(new BackgroundComponent(Color.DARKSLATEBLUE));
        area.getTile(posX, posY).addEntity(entity);
    }

    private static void createWall(Area area, int posX, int posY) {
        var position = new Position(area, posX, posY);
        var entity = new Entity(position);
        entity.addComponent(new SpriteComponent(new Image("sprites/curses/curses_16x16_35.png"), Color.DARKSLATEGRAY));
        entity.addComponent(new BackgroundComponent(Color.BLACK));
        entity.addComponent(new SolidComponent());
        entity.addComponent(new TerrainComponent());
        area.getTile(posX, posY).addEntity(entity);
    }

    private static void createGround(Area area, int posX, int posY) {
        var position = new Position(area, posX, posY);
        var entity = new Entity(position);
        entity.addComponent(new SpriteComponent(new Image("sprites/curses/curses_16x16_250.png")));
        entity.addComponent(new TerrainComponent());
        entity.addComponent(new BackgroundComponent(Color.DARKSLATEBLUE));
        area.getTile(posX, posY).addEntity(entity);
    }
}
