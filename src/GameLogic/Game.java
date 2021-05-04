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
        Board board = new Board();
        setBoard(board);
        Player p1 = Player.getP1();
        Player p2 = Player.getP2();
        board.showBoard();
        if(p1.getScore() > p2.getScore()){
            System.out.println("GameLogic.Player 1 Win");
            System.out.println("Score: " + p1.getScore());
        }
        else if(p1.getScore() < p2.getScore()){
            System.out.println("GameLogic.Player 2 Win");
            System.out.println("Score: " + p2.getScore());
        }
        else {
            System.out.println("Draw");
            System.out.println("Score: " + p1.getScore());
        }
    }
}
