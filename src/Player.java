
public class Player {
    private static Player p1;
    private static Player p2;
    private int score = 0;
    private int[] limit;
    private Coordinates point;

    private Player(){
    }

    public static Player getP1() {
        if (p1==null)
            p1=new Player();
        return p1;
    }
    public static Player getP2() {
        if (p2==null)
            p2=new Player();
        return p2;
    }

    public void getBoard(){
        //Game.getBoard().Board;
        
    }
}
