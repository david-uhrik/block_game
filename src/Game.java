/**
 * Created by david on 10/5/16.
 */

import java.util.*;

public class Game {

    public Level lvl;

    public static boolean playing = true;

    public Game() {
        welcome();

        lvl = new Level();

        while (playing) {
            System.out.println(lvl.toString());

            Scanner keyboard = new Scanner(System.in);
            char direction = keyboard.next(".").charAt(0);

            if (direction == 'x') break;
            if (direction == 'h') help();

            lvl.updateLevel(direction);

        }

        System.out.println("thanks for playing");
    }

    public void welcome() {
        System.out.println("WELCOME AT block_game");
        System.out.println();
        System.out.println();
        System.out.println("to move around use the keys w, s, a and d");
        System.out.println("to quit the game press x");
        System.out.println("for help press h");
        System.out.println();
        System.out.println();
    }

    public void help() {
        System.out.println("to move around use the keys w, s, a and d");
        System.out.println("to quit the game press x");
    }

    public static void main(String[] args) {
        Game aaa = new Game();

    }
}