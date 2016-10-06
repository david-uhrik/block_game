/**
 * Created by david on 10/5/16.
 */
public class Block {

    public enum State {
        PLAYER, FREE, HARD, UP, DOWN, MINUS, FREE2, FINISH;
    }

    public State state;

    public Block(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String toString() {
        switch (state) {
            case PLAYER:
                return "O";

            case FREE:
                return " ";

            case HARD:
                return "X";

            case UP:
                return "^";

            case DOWN:
                return "▼";

            case MINUS:
                return "—";

            case FREE2:
                return "_";

            case FINISH:
                return "!";

            default:
                return " ";
        }
    }

    public static void main(String[] args) {
        Block bl = new Block(State.DOWN);

        System.out.println(bl.toString());

        //bl.setState(5);

        System.out.println(bl.toString());
    }


}
