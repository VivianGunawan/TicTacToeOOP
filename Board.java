public class Board{
    //Fields
    private int rowsize;
    private int colsize;
    private Cell[][] board; // For this implementation of board it is 2D
    //Constructor
    public Board(int r, int c){
        this.rowsize=r;
        this.colsize=c;
        this.board = new Cell[rowsize][colsize];
        for (int i = 0; i < this.rowsize; i++) {
            for (int j = 0; j < this.colsize; j++) {
                this.board[i][j] = new Cell();
            }
        }
    }
    //Methods
    public void clear(){
        for (int i =0; i < this.rowsize; i++) {
            for (int j = 0; j < this.colsize; j++) {
                board[i][j].clear();
            }
        }
    }
    public void display(){
        String border ="------+";
        System.out.println("+"+border.repeat(this.colsize));
        for (int i = 0; i < this.rowsize; i++) {
            for (int j = 0; j < this.colsize; j++) {
                if (board[i][j].getIsEmpty()){
                    System.out.printf("| %4s ",Integer.toString(this.colsize*(i%this.rowsize)+((j%this.colsize)+1)));
                }
                else{
                    System.out.printf("| %4s ",board[i][j].getContent().getChecker());
                }
            }
            System.out.print("| ");
            System.out.println();
            System.out.println("+"+border.repeat(this.colsize));;
        }
    }
    public boolean place(Checker a, int r, int c){
        if (!(board[r][c].getIsEmpty())){
            System.out.println("Space is occupied");
            return false;
        }
        else {
            board[r][c].setContent(a);
            return true;
        }
     }
     public Checker see(int r, int c){
        return board[r][c].getContent();
    }
}