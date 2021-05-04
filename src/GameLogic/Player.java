package GameLogic;

import java.util.ArrayList;

public class Player {
    private static Player p1 = null;
    private static Player p2 = null;
    private int score = 0;
    private ArrayList <Integer> limit = new ArrayList<>();
    private Coordinates point;

    public ArrayList<Integer> getLimit() {
        return limit;
    }

    public Coordinates getPointOfPlayer() {
        return point;
    }

    private Player() {

    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        this.score++;
    }


    public void setPointOfPlayer( int x, int y ) {
        this.point = new Coordinates(x, y);
    }

    /* Just ONE p1 can be available */
    public static Player getP1() {
        if (p1 == null)
            p1 = new Player();
        return p1;
    }

    /* Just ONE p2 can be available */
    public static Player getP2() {
        if ( p2 == null )
            p2 = new Player();
        return p2;
    }

    public void Move( int xGoal , int yGoal ) {
        if(this.getLimit().size() != 0) {
            this.getLimit().remove(0);
        }
        Game.getBoardInstance().setBoard( null , this.getPointOfPlayer().x , this.getPointOfPlayer().y );
        this.setPointOfPlayer( xGoal , yGoal );
        Game.getBoardInstance().setBoard( this , xGoal,yGoal );
    }
}
