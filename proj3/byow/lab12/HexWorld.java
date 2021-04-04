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

        // draws the world to the screen
        ter.renderFrame(world);
    }

    private void addHexagon(TETile[][] world, int x, int size){
        int xbegin = x;
        int ybegin = HEIGHT - 1 - 2 * size;
        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j++) {
                world[xbegin - i ][ybegin + i] = Tileset.WALL;
            }
        }
    }

}
