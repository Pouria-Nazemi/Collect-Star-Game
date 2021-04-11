public class Game {
    public static Board board;

    public static void setBoard(Board board) {
        Game.board = board;
    }

    public static Board getBoard() {
        return board;
    }
    

    public static void main(String[] args) {
        Board board = new Board();
        setBoard(board);
        Player p1 = Player.getP1();
       // p1.getBoard(); ino pouria testi gozashte
        board.showBoard();
    }

    
}
