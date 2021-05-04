package GameLogic;

public class Star {
    private Coordinates point;
    private static int count = 0 ;

    public Star( int x , int y ) {
        this.point = new Coordinates( x , y );
        count++;  /* Any time we use this constructor , it means that we added a new star object , and the total star count should became ++ ! */
    }

    public static int getCount() {
        return count;
    }
    public static void decreaseCount(){
        count--;
    }
}
