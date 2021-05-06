package GameLogic;

public class Game {

    public static Board board;

    public static void setBoard(Board board) {
        Game.board = board;
    }

    /* This is used because of a better access to the board's options - THE IMPORTANT POINT IS THE "STATIC" FORM  */
    public static Board getBoardInstance() {
        return board;
    }

    /* This was used on the first version of the game - while we were not working on graphics */
    public static void main(String[] args) {

    }
}
