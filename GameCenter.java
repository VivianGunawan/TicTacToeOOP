import java.util.InputMismatchException;
import java.util.Scanner;
public class GameCenter{
    //Fields
    private int games;
    private Player[] playersRound;
    private int numPlayers;
    private Player[] players;
    private ScoreSheet scoreSheet;
    Scanner scan = new Scanner(System.in);

    //Constructor
    private GameCenter(){
        System.out.println("Welcome to the Game Center");
        System.out.println("How many distinct players are participating? (int:2->10)");
        try{
            this.numPlayers = scan.nextInt();
            scan.nextLine();
            while(this.numPlayers<2||this.numPlayers>10){
               System.out.println("Out of Range");
               System.out.print("Please enter valid number of players (int:2->10)");
               this.numPlayers = scan.nextInt();
               scan.nextLine();
            }
        }
        catch(InputMismatchException e){
            System.out.println("You have entered invalid data");
        }
        this.players = new Player[this.numPlayers];
        for (int i=0; i<numPlayers; i++){
            Player temp = new Player();
            System.out.println("Player's "+i+" name? (String)");
            try {
                String n = scan.nextLine();
                temp.setName(n);
                while(checkDuplicatePlayersName(temp,i)){
                    System.out.println("The player's name is already taken, please re-input a distict player's name (String)");
                    n = scan.nextLine();
                    temp.setName(n);
                }
            } catch (InputMismatchException e) {
                System.out.println("You have entered invalid data");
            }
            this.players[i]=temp;
        }
        this.scoreSheet = new ScoreSheet(this.numPlayers, this.players);
        startRound();
    }
    //Methods
    private boolean checkDuplicatePlayersName(Player temp,int i){
        for(int j=0; j<i; j++){
            if(players[j].getName().equals(temp.getName())){
                return true;
            }
        }
        return false;
    }
    private void startRound(){
        this.playersRound = new Player[2];
        this.games+=1;
        System.out.println("Welcome to game: " + this.games);
        System.out.println("Which two players are participating in game " + this.games +" ?");
        for (int i=0; i<this.numPlayers; i++){
            System.out.println(i+" :"+ this.players[i].getName());
        }
        System.out.println("Input first player's number (int:0->number of players-1)");
        try{
            int choice = scan.nextInt();
            while(choice<0||choice>this.numPlayers-1){
                System.out.println("Out of Range");
                System.out.print("Please enter a valid player number (int:0->number of players-1)");
                choice = scan.nextInt();
            }
            this.playersRound[0]=this.players[choice];
        }
        catch(InputMismatchException e){
            System.out.println("You have entered invalid data");
        }
        System.out.println("Input second player's number (int:0-number of players)");
        try{
            int choice = scan.nextInt();
            while(choice<0||choice>this.numPlayers-1||this.players[choice].equals(playersRound[0])){
                System.out.println("Out of Range");
                System.out.print("Please enter a valid player number) (int:0->number of players)");
                choice = scan.nextInt();
            }
            this.playersRound[1]=this.players[choice];
        }
        catch(InputMismatchException e){
            System.out.println("You have entered invalid data");
        }
        System.out.println("Which game do you want to play? Tic Tac Toe (TTT) or Order and Chaos(OC) (String: TTT/OC)");
        String choice = scan.next();
        while(!(choice.equals("TTT")||choice.equals("OC"))){
            System.out.println("Which game do you want to play? Tic Tac Toe (TTT) or Order and Chaos(OC) (String: TTT/OC)");
            choice = scan.next();
        }
        if (choice.equals("TTT")){
            new TTTGameEngine(playersRound,scoreSheet);
            roundOver();
        }
        else if(choice.equals("OC")) {
            new OCGameEngine(playersRound,scoreSheet);
            roundOver();
        }
        return;
    }
    private void roundOver(){
        scoreSheet.display();
        System.out.println("Another Game? (Y/N)");
        String choice = scan.next();
            while(!(choice.equals("Y")||choice.equals("N"))){
                System.out.println("Another Game? (Y/N)");
                choice = scan.next();
            }
            if (choice.equals("Y")){
                startRound();
            }
            else {
                return;
            }
    }
    public static void main(String[] args) {
        new GameCenter();
    }
}