package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {

    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }

        int xbegin_room = 5, ybegin_room = 5, w_room = 5, h_room = 7;
        int xbegin_room2 = 15, ybegin_room2 = 15, w_room2 = 6, h_room2 = 8;
        int y_hallway = 6, x_hallway = 16, w_hallway = 2;

        drawRec(finalWorldFrame, xbegin_room, ybegin_room, w_room, h_room, Tileset.WALL);
        drawRec(finalWorldFrame, xbegin_room + 1, ybegin_room + 1, w_room-2, h_room-2, Tileset.GRASS);
        drawRec(finalWorldFrame, xbegin_room2, ybegin_room2, w_room2, h_room2, Tileset.WALL);
        drawRec(finalWorldFrame, xbegin_room2+1, ybegin_room2+1, w_room2-2, h_room2-2, Tileset.GRASS);

        // hallway
        drawRec(finalWorldFrame, xbegin_room+w_room-1, ybegin_room, w_room, h_room, Tileset.WALL);
        

        ter.renderFrame(finalWorldFrame);

        return finalWorldFrame;
    }

    private void drawRec(TETile[][] world, int x, int y, int w, int h, TETile icon){
        for(int i = x ; i < x + w; i++){
            for(int j = y; j < y + h; j++){
                world[i][j] = icon;
            }
        }
    }
}
