
public class Player {
    private static Player p1=null;
    private static Player p2=null;
    private int score = 0;
    private int[] limit;
    private Coordinates point;

    private Player(){

    }

    public void setPointOfPlayer(int x, int y) {
        this.point = new Coordinates(x,y);
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

    public void updateBoard(){//test
        //Game.getBoardInstance().setBoard(new Wall(3,1),3,1);
        //Game.getBoardInstance().setBoard(null,3,1);
    }
}
