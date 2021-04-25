package GameLogic;

public class Star {
    private Coordinates point;
    static int count=0;

    public Star(int x,int y) {
        this.point = new Coordinates(x,y);
        count++;
    }
}
