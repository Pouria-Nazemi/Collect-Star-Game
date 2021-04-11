public class Game {
    public static Board board;

    public static void setBoard(Board board) {
        Game.board = board;
    }

    public static Board getBoardInstance() {
        return board;
    }
    

    public static void main(String[] args) {
        Board board = new Board();
        setBoard(board);
        Player p1 = Player.getP1();
        Player p2 = Player.getP2();
        p1.updateBoard();
        board.showBoard();
    }

    
}
