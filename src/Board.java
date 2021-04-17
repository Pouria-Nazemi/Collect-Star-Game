import java.util.Scanner;

public class Board {
    private int width;
    private int height;
    private Object[][] Board;

    public Board() {
        gamePreparation();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setBoard(Object obj,int x , int y) {
        this.Board[x][y] = obj;
    }

    /*public Object[][] getBoard() {
        return Board;
    }*/
    private Boolean boardDimentionControl(int num){
        return num >= 2;
    }

    private void gamePreparation(){
        System.out.println("Enter the dimention of the board of the game ");
        Scanner input = new Scanner(System.in);
        System.out.print("Width: ");
        int width = input.nextInt();
        while(!boardDimentionControl(width)){
            System.out.print("Enter a positive number larger than 1: ");
            width = input.nextInt();
        }
        this.setWidth(width);
        System.out.print("\nHeight: ");
        int height = input.nextInt();
        while(!boardDimentionControl(height)){
            System.out.print("Enter a positive number larger than 1: ");
            height = input.nextInt();
        }
        this.setHeight(height);
        this.Board = new Object[height][width];
        System.out.println("Adding objects of the game");
        System.out.println("Choose the object that you want to create when finished type 0 ");
        System.out.println("\t 1.Wall\n\t 2.Star\n\t 3.SpeedLimiter\n\t 0.Finish");
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
                case 0: {
                    break;
                }
                default:{
                    System.out.println("Enter correct number");
                }
            }
            System.out.println("Choose the object that you want to create when finished type 0 ");
            System.out.println("\t 1.Wall\n\t 2.Star\n\t 3.SpeedLimiter\n\t 0.Finish");
            objectCreator = input.nextInt();  
        }
        System.out.println("Enter the position of the Player 1: ");
        System.out.print("X: ");
        X = input.nextInt()-1;
        System.out.print("\nY: ");
        Y = input.nextInt()-1;
        Player.getP1().setPointOfPlayer(X,Y);
        this.setBoard(Player.getP1(),X,Y);

        System.out.println("Enter the position of the Player 2: ");
        System.out.print("X: ");
        X = input.nextInt()-1;
        System.out.print("\nY: ");
        Y = input.nextInt()-1;
        Player.getP2().setPointOfPlayer(X,Y);
        this.setBoard(Player.getP2(),X,Y);

    }
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
                        case "Star":
                            System.out.print("S");
                            break;
                        case "Wall":
                            System.out.print("W");
                            break;
                        case "SpeedLimiter":
                            SpeedLimiter sl = (SpeedLimiter) Board[row][col];
                            System.out.print(sl.getLimitingValue());
                            break;
                        case "Player":
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

    public Object getBoardElement(int x,int y){
        return Board[x][y];
    }
}
