/**
 * Created by david on 10/5/16.
 */

import java.lang.*;

public class Level {

    public Block[][] board;

    public int[] upBlocks;

    public int[] downBlocks;

    public int[] badBlocks;

    public int myPosition;

    public final int FINISH = 19;

    public Level() {
        board = new Block[3][10];

        setBoard();
    }

    public void setBoard() {
        myPosition = 10;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Block(Block.State.FREE);
            }
        }

        board[1][0].setState(Block.State.PLAYER);
        board[1][9].setState(Block.State.FINISH);

        lvl1();
    }

    public void lvl1() {
        upBlocks = new int[2];
        badBlocks = new int[6];

        board[0][1].setState(Block.State.HARD);
        board[1][2].setState(Block.State.HARD);
        board[1][4].setState(Block.State.HARD);
        board[2][4].setState(Block.State.HARD);

        board[2][6].setState(Block.State.UP);
        board[1][6].setState(Block.State.UP);

        badBlocks[0] = 1;
        badBlocks[1] = 12;
        badBlocks[2] = 14;
        badBlocks[3] = 24;

        badBlocks[4] = 26;
        badBlocks[5] = 16;

        upBlocks[0] = 26;
        upBlocks[1] = 16;
    }

    public void restart() {
        setBoard();
    }

    public Block getBlock(int position) {
        if (position < 10) {
            return board[0][position];
        } else if (position < 20) {
            return board[1][position % 10];
        } else {
            return board[2][position % 10];
        }
    }

    public void setBlock(int position, Block.State state) {
        getBlock(position).setState(state);
    }

    public void setBlocks(int[] category, Block.State state) {
        for (int position = 0; position < category.length; position++) {
            setBlock(category[position], state);
        }
    }

    public void moveUpAll() {
        setBlocks(upBlocks, Block.State.FREE);

        for (int i = 0; i < upBlocks.length; i++) {
            upBlocks[i] = (upBlocks[i] + 20) % 30;
            badBlocks[badBlocks.length - 1 - i] = (badBlocks[badBlocks.length - 1 - i] + 20) % 30;
        }

        setBlocks(upBlocks, Block.State.UP);
    }

    public void moveDownAll() {
        setBlocks(downBlocks, Block.State.FREE);

        for (int i = 0; i < downBlocks.length; i++) {
            downBlocks[i] = (downBlocks[i] + 20) % 30;
            badBlocks[badBlocks.length - 1 - i] = (badBlocks[badBlocks.length - 1 - i] + 20) % 30;
        }

        setBlocks(downBlocks, Block.State.UP);
    }

    public void moveUpAllSide() {

    }

    public void changeVisibility() {

    }


    public void updateLevel(char direction) {
        int nextPosition = myPosition;

        setBlock(myPosition, Block.State.FREE);

        switch (direction) {
            case 'w':
                nextPosition = (myPosition + 20) % 30;
                moveUpAll();
                break;
            case 's':
                nextPosition = (myPosition + 10) % 30;
                moveUpAll();
                break;

            case 'a':
                if (myPosition % 10 != 0) {
                    nextPosition = myPosition - 1;
                }
                break;
            case 'd':
                if (myPosition % 10 != 9) {
                    nextPosition = myPosition + 1;
                }
                break;
        }

        for (int i = 0; i < badBlocks.length; i++) {
            if (badBlocks[i] == nextPosition) {
                restart();
                return;
            }
        }

        myPosition = nextPosition;

        setBlock(myPosition, Block.State.PLAYER);

        if (myPosition == FINISH) {
            System.out.println("YOU WON!");
            Game.playing = false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("+");

        for (int i = 0; i < 10; i++) {
            sb.append("-");
        }

        sb.append("+\n");

        for (int i = 0; i < 3; i++) {
            sb.append("|");
            for (int j = 0; j < 10; j++) {
                sb.append(board[i][j].toString());
            }
            sb.append("|\n");
        }

        sb.append("+");

        for (int i = 0; i < 10; i++) {
            sb.append("-");
        }

        sb.append("+\n");

        return sb.toString();
    }

    public static void main(String[] args) {

        Level lvl = new Level();

        System.out.println(lvl.toString());

        System.out.println(lvl.upBlocks.toString());
    }
}