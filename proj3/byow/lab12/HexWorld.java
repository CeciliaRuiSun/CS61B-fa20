package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        int x1 = WIDTH / 2 - 1;
        int y1 = HEIGHT - 6;
        int s = 3;
        // middle
        addHexagon(world, x1,y1,s, Tileset.TREE);
        addHexagon(world, x1,y1 - 2*s,s, Tileset.MOUNTAIN);
        addHexagon(world, x1,y1 - 4*s,s, Tileset.MOUNTAIN);
        addHexagon(world, x1,y1 - 6*s,s, Tileset.MOUNTAIN);
        addHexagon(world, x1,y1 - 8*s,s, Tileset.MOUNTAIN);

        // middle left 1
        addHexagon(world, x1 - (2*s - 1),y1 - s,s, Tileset.GRASS);
        addHexagon(world, x1 - (2*s - 1),y1 - 3*s,s, Tileset.MOUNTAIN);
        addHexagon(world, x1 - (2*s - 1),y1 - 5*s,s, Tileset.MOUNTAIN);
        addHexagon(world, x1 - (2*s - 1),y1 - 7*s,s, Tileset.FLOWER);

        // middle right 1
        addHexagon(world, x1 + 2*s - 1,y1 - s,s, Tileset.FLOWER);
        addHexagon(world, x1 + 2*s - 1,y1 - 3*s,s, Tileset.SAND);
        addHexagon(world, x1 + 2*s - 1,y1 - 5*s,s, Tileset.TREE);
        addHexagon(world, x1 + 2*s - 1,y1 - 7*s,s, Tileset.MOUNTAIN);

        // middle left 2
        addHexagon(world, x1 - 2*(2*s - 1),y1 - 2*s,s, Tileset.MOUNTAIN);
        addHexagon(world, x1 - 2*(2*s - 1),y1 - 4*s,s, Tileset.GRASS);
        addHexagon(world, x1 - 2*(2*s - 1),y1 - 6*s,s, Tileset.GRASS);

        // middle right 2
        addHexagon(world, x1 + 2*(2*s - 1),y1 - 2*s,s, Tileset.FLOWER);
        addHexagon(world, x1 + 2*(2*s - 1),y1 - 4*s,s, Tileset.TREE);
        addHexagon(world, x1 + 2*(2*s - 1),y1 - 6*s,s, Tileset.SAND);

        // draws the world to the screen
        ter.renderFrame(world);
    }

    private static void addHexagon(TETile[][] world, int x, int y, int size, TETile icon){
        int xbegin = x;
        int ybegin = y;
        for(int h = ybegin; h < ybegin + size; h ++){
            for(int w = xbegin - (h - ybegin); w < xbegin + size + (h - ybegin); w++) {
                world[w][h] = icon;
            }
        }

        for(int h = ybegin + size; h < ybegin + 2 * size; h ++){
            for(int w = xbegin - (ybegin + 2*size - h - 1); w < xbegin + size + (ybegin + 2*size - h - 1);w++){
                world[w][h] = icon;
            }
        }
    }


}
