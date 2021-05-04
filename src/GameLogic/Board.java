package GameLogic;
import java.util.Scanner;

public class Board {
    private int width;
    private int height;
    private Object[][] Board; /* An array for the beads that helps us be more in touch with the real OOP world! */

    public Board() {
       // gamePreparation();
    }

    public Board(int width,int height){
      this.Board = new Object[height][width];
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setBoard(Object obj,int x , int y) {
        this.Board[x][y] = obj;
    }

    /* This method is not needed for the graphic version */
    private void gamePreparation(){
        System.out.println("Enter the dimension of the board of the game ");
        Scanner input = new Scanner(System.in);
        System.out.print("Width: ");
        int width = input.nextInt();
        while(width<2){
            System.out.print("Enter a positive number larger than 1: ");
            width = input.nextInt();
        }
        this.setWidth(width);
        System.out.print("\nHeight: ");
        int height = input.nextInt();
        while(height<2){
            System.out.print("Enter a positive number larger than 1: ");
            height = input.nextInt();
        }
        this.setHeight(height);
        this.Board = new Object[height][width];
        System.out.println("Adding objects of the game");
        System.out.println("Choose the object that you want to create when finished type 0 ");
        System.out.println("\t 1.GameLogic.Wall\n\t 2.GameLogic.Star\n\t 3.GameLogic.SpeedLimiter\n\t 0.Finish");
        int objectCreator = input.nextInt();
        int X,Y;
        while(objectCreator != 0){
            System.out.println("Enter the position of the object ");
            System.out.print("X: ");
            X = input.nextInt()-1;
            System.out.print("\nY: ");
            Y = input.nextInt()-1;
            switch(objectCreator){
                case 1:{
                    this.setBoard(new Wall(X,Y),X,Y);
                    break;
                }
                case 2:{
                    this.setBoard(new Star(X,Y),X,Y);
                    break;
                }
                case 3: {
                    System.out.print("Enter the valueOfLimit: ");
                    int valueOfLimit = input.nextInt();
                    this.setBoard(new SpeedLimiter(X,Y,valueOfLimit),X,Y);
                    break;
                }
                default:{
                    System.out.println("Enter correct number");
                }
            }
            System.out.println("Choose the object that you want to create when finished type 0 ");
            System.out.println("\t 1.GameLogic.Wall\n\t 2.GameLogic.Star\n\t 3.GameLogic.SpeedLimiter\n\t 0.Finish");
            objectCreator = input.nextInt();  
        }
        System.out.println("Enter the position of the GameLogic.Player 1: ");
        System.out.print("X: ");
        X = input.nextInt()-1;
        System.out.print("\nY: ");
        Y = input.nextInt()-1;
        Player.getP1().setPointOfPlayer(X,Y);
        this.setBoard(Player.getP1(),X,Y);

        System.out.println("Enter the position of the GameLogic.Player 2: ");
        System.out.print("X: ");
        X = input.nextInt()-1;
        System.out.print("\nY: ");
        Y = input.nextInt()-1;
        Player.getP2().setPointOfPlayer(X,Y);
        this.setBoard(Player.getP2(),X,Y);
    }


    /* This method was used to show the board in the first version of this project  */
    public void showBoard(){
        for (int row = 0; row < this.height; row++) {
            for(int i = 0;i<2*this.width;i++){
                System.out.print("-");
            }
            System.out.println();
            for (int col = 0; col < this.width; col++) {
                if(Board[row][col]!=null){
                    System.out.print("|");
                    switch (Board[row][col].getClass().getName()) {
                        case "GameLogic.Star":
                            System.out.print("S");
                            break;
                        case "GameLogic.Wall":
                            System.out.print("W");
                            break;
                        case "GameLogic.SpeedLimiter":
                            SpeedLimiter sl = (SpeedLimiter) Board[row][col];
                            System.out.print(sl.getLimitingValue());
                            break;
                        case "GameLogic.Player":
                            if(Board[row][col].equals(Player.getP1())) {
                                System.out.print("#");
                            }

                            else if (Board[row][col].equals(Player.getP2())){
                                System.out.print("@");
                             }
                            break;
                        default:
                            System.out.print(" ");
                            break;
                    }
                }
                else{
                   System.out.print("| ");
                }
            }
            System.out.println();
        }
    }

    /* With this method we can just search on a specific place on the board and this help us have a more efficient and faster search on the array */
    public Object getBoardElement(int x,int y){
        return Board[x][y];
    }
}
