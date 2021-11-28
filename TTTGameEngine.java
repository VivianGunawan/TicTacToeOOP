import java.util.InputMismatchException;
import java.util.Scanner;

public class TTTGameEngine{
    //Fields
    private Player[] playersXO;
    private ScoreSheet scoreSheet;
    private int size;
    private Board board;
    private int turns;
    Scanner scan = new Scanner(System.in);
    
    //Constructor
    public TTTGameEngine(Player[] p, ScoreSheet s){
        this.playersXO = p;
        this.scoreSheet = s;
        this.turns=0;
        System.out.println("Welcome to this game of Tic Tac Toe");
        System.out.println("Board Size?(3-20)");
        try{
            this.size = scan.nextInt();
            scan.nextLine();
            while(this.size<3||this.size>20){
                System.out.println("Out of Range");
                System.out.print("Please enter valid board size (3-20)");
                this.size = scan.nextInt();
                scan.nextLine();
            }
        }
        catch(InputMismatchException e){
            System.out.println("You have entered invalid data");
        }
        this.board = new Board(this.size, this.size);
        startRound();
    }
    //Methods
    private void startRound(){
        System.out.println("is "+ playersXO[0].getName() +" or "+ playersXO[1].getName()+" starting first? (String: names)");
        try {
            String choice = scan.nextLine();
            while(!(choice.equals(playersXO[0].getName())||choice.equals(playersXO[1].getName()))){
                System.out.println("is "+ playersXO[0].getName() +" or "+ playersXO[1].getName()+" starting first? (String: names)");
                choice = scan.nextLine();
            }
            if(choice.equals(playersXO[1].getName())){
            Player temp = playersXO[0];
            playersXO[0] = playersXO[1];
            playersXO[1] = temp;
            }
        } catch (InputMismatchException e) {
            System.out.println("You have entered invalid data");
        }
        for(int i=0; i<2;i++){
            System.out.println("Player's "+playersXO[i].getName()+" checker?(single character)");
            try {
                char c = scan.next(".").charAt(0);
                scan.nextLine();
                playersXO[i].setCheckerDirect(c);
                while(playersXO[i].getChecker().equals(playersXO[(i+1)%2].getChecker())){
                    System.out.println("The player's checker is already taken, please re-input a distinct player's checker (single character)");
                    c = scan.next(".").charAt(0);
                    scan.nextLine();
                    playersXO[i].setCheckerDirect(c);
                }
            } catch (Exception e) {
                System.out.println("You have entered invalid data");
            }
        }
        board.clear();
        board.display();
        while (this.turns!=this.size*this.size){
            Player currentPlayer = playersXO[turns%2];
            turn(currentPlayer);

            if (check(currentPlayer.getChecker())){
                scoreSheet.addWin(currentPlayer);
                scoreSheet.addLoss(playersXO[(turns+1)%2]);
                System.out.println(currentPlayer.getName()+" has won this game!");
                playersXO[0].removeChecker();
                playersXO[1].removeChecker();
                return;
            }
            this.turns+=1;
        }
        System.out.println("This game resulted in a tie!");
        scoreSheet.addTie(this.playersXO[0], this.playersXO[1]);
        playersXO[0].removeChecker();
        playersXO[1].removeChecker();
        return;
    }
    private void turn(Player cp){
        System.out.println(cp.getName()+"'s turn");
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
    private boolean checkvertical(Checker checker){
        for(int j=0; j<this.size; j++){
            int count = 0;
            for(int i=0; i<this.size;i++){
                if(board.see(i,j).equals(checker)){
                    count ++;
                    if (count == this.size){
                        return true;
                    }
                }
                else{
                    count = 0;
                    break;
                }
            }
        }
        return false;
    }
    private boolean checkhorizontal(Checker checker){
        for(int i=0; i<this.size; i++){
            int count = 0;
            for(int j=0; j<this.size;j++){
                if(board.see(i,j).equals(checker)){
                    count ++;
                    if (count == this.size){
                        return true;
                    }
                }
                else{
                    count = 0;
                    break;
                }
            }
        }
        return false;
    }
    private boolean checkdiagonalright(Checker checker){
        for(int i=0; i<this.size; i++){
            if(!(board.see(i, i).equals(checker))){
                return false;
            }
        }
        return true;
    }
    private boolean checkdiagonalleft(Checker checker){
        for(int i=0; i<this.size; i++){
            if(!(board.see(i, this.size-1-i).equals(checker))){
                return false;
            }
        }
        return true;
    }
    private boolean check(Checker checker){
        return checkvertical(checker)||checkhorizontal(checker)||checkdiagonalright(checker)||checkdiagonalleft(checker);
    }
}