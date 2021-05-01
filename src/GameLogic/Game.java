package GameLogic;

public class Game {
    public static Board board;
    /*public static GameLogic.Player player;*/

    public static void setBoard(Board board) {
        Game.board = board;
    }

    public static Board getBoardInstance() {
        return board;
    }

    /*public static GameLogic.Player getPlayer() {
        return player;
    }*/

    public static void main(String[] args) {
        Board board = new Board();
        setBoard(board);
        Player p1 = Player.getP1();
        Player p2 = Player.getP2();
        //p1.updateBoard();
        board.showBoard();
        while(Star.count>0){
            //Player.gettingDestinationAndMove();
        }
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
