import java.util.InputMismatchException;
import java.util.Scanner;

public class OCGameEngine{
    //Fields
    private Player[] players;
    private Checker[] checkers;
    private ScoreSheet scoreSheet;
    private Board board;
    private int turns;
    private int size = 6;
    Scanner scan = new Scanner(System.in);
    //Constructor
    public OCGameEngine(Player[] p, ScoreSheet s){
        this.players = p;
        this.checkers = new Checker[2];
        this.scoreSheet = s;
        this.board = new Board(this.size, this.size);
        this.turns=0;
        startRound();
    }
    //Methods
    private void startRound(){
        System.out.println("Choose two checkers to play with");
        System.out.println("First checker? (single character)");
        Checker a = new Checker();
        try {
            char c = scan.next(".").charAt(0);
            a.setChecker(c);
            checkers[0] = a;
            scan.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("You have entered invalid data");
        }
        System.out.println("Second Checker? (single character)");
        Checker b = new Checker();
        try {
            char c = scan.next(".").charAt(0);
            b.setChecker(c);
            checkers[1]=b;
            scan.nextLine();
            while(checkers[0].equals(checkers[1])){
                System.out.println("The checker is already taken, please re-input a distinct checker");
                c = scan.next(".").charAt(0);
                b.setChecker(c);
                checkers[1]=b;
                scan.nextLine();
            }
        } catch (Exception e) {
            System.out.println("You have entered invalid data");
        }
        System.out.println("is "+ players[0].getName() +" or "+ players[1].getName()+" Order? (String: names)");
        try{
        String choice = scan.nextLine();
        while(!(choice.equals(players[0].getName())||choice.equals(players[1].getName()))){
            System.out.println("is "+ players[0].getName() +" or "+ players[1].getName()+" playing Order? (String: names)");
            choice = scan.nextLine();
        }
        if(choice.equals(players[1].getName())){
           Player temp = players[0];
           players[0] = players[1];
           players[1] = temp;
        }
    }catch(InputMismatchException e){
        System.out.println("You have entered invalid data");
    }
    System.out.println(players[0].getName() + " is Order");
    System.out.println(players[1].getName() + " is Chaos");
    board.clear();
    board.display();
    System.out.println("Game is starting");
    while (this.turns!=this.size*this.size){
        Player currentPlayer = players[turns%2];
        turn(currentPlayer);
        if (check()){
            scoreSheet.addWin(players[0]);
            scoreSheet.addLoss(players[1]);
            System.out.println("Order has won this game!");
                return;
        }
        this.turns+=1;
    }
    System.out.println("Chaos has won this game! ");
    scoreSheet.addWin(players[1]);
    scoreSheet.addLoss(players[0]);
    return;
    }
    private void turn(Player cp){
        System.out.println(cp.getName()+"'s turn");
        System.out.println("Which checker do you want to play? " + this.checkers[0].getChecker() + " or " + this.checkers[1].getChecker() +" (single character)");
        try {
            char c = scan.next(".").charAt(0);
            while(!(c==this.checkers[0].getChecker()||c==this.checkers[1].getChecker())){
                System.out.println("Which checker do you want to play? " + this.checkers[0].getChecker() + " or " + this.checkers[1].getChecker()+" (single character)");
                c = scan.next(".").charAt(0);
            }
            if (c==this.checkers[0].getChecker()){
                cp.removeChecker();
                cp.setChecker(this.checkers[0]);
            }
            else if (c==this.checkers[1].getChecker()){
                cp.removeChecker();
                cp.setChecker(this.checkers[1]);
            }
        } catch (InputMismatchException e) {
            System.out.println("You have entered invalid data");
        }
        int box;
        boolean flag = false;
        while (flag==false){
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter selected box: (int)");
            try{
                box = scan.nextInt();
                while(box<=0||box>this.size*this.size){
                    System.out.println("Out of Range");
                    System.out.print("Please enter selected box: (int)");
                    box = scan.nextInt();
                }
                flag = board.place(cp.getChecker(),((box-1)/size),((box-1)%size));
            }catch(InputMismatchException e){
                System.out.println("You have entered invalid data");
            }
        }
        board.display();
        return;
    }
    private boolean check(){
        return checkvertical()||checkhorizontal()||checkdiagonalright()||checkdiagonalleft();
    }
    private boolean checkhorizontal(){
        for (int r= 0; r<this.size; r++){
            for (int c = 0; c<this.size - 4; c++){
                if (board.see(r, c).getChecker()!='\0' && board.see(r, c).equals(board.see(r, c+1)) && board.see(r, c).equals(board.see(r, c+2))&& board.see(r, c).equals(board.see(r, c+3))&& board.see(r, c).equals(board.see(r, c+4))){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkvertical(){
        for (int c= 0; c<this.size; c++){
            for (int r = 0; r<this.size - 4; r++){
                if (board.see(r, c).getChecker()!='\0' && board.see(r, c).equals(board.see(r+1, c)) && board.see(r, c).equals(board.see(r+2, c))&& board.see(r, c).equals(board.see(r+3, c))&& board.see(r, c).equals(board.see(r+4, c))){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkdiagonalright(){
        for (int r = 0; r < this.size - 4; r++){
            for (int c = 0; c < this.size - 4 ; c++){
                if(board.see(r, c).getChecker()!='\0' && board.see(r, c).equals(board.see(r+1, c+1)) && board.see(r, c).equals(board.see(r+2, c+2))&& board.see(r, c).equals(board.see(r+3, c+3))&& board.see(r, c).equals(board.see(r+4, c+4))){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkdiagonalleft(){
        for (int r = 0; r < this.size - 4; r++){
            for (int c = 4; c < this.size; c++){
                if(board.see(r, c).getChecker()!='\0' && board.see(r, c).equals(board.see(r+1, c-1)) && board.see(r, c).equals(board.see(r+2, c-2))&& board.see(r, c).equals(board.see(r+3, c-3))&& board.see(r, c).equals(board.see(r+4, c-4))){
                    return true;
                }
            }
        }
        return false;
    }
    
}