public class ScoreSheet{
    //Fields
    private int numplayers;
    private Player[] players;
    private int[] wins;
    private int[] losses;
    private int[] ties;
    private int[] gamesplayed;
    //Constructor
    public ScoreSheet(int n, Player[] p){
        this.numplayers = n;
        this.players = p;
        this. wins = new int[numplayers];
        this. losses = new int[numplayers];
        this. ties = new int[numplayers];
        this. gamesplayed = new int[numplayers];
    }
    //Methods
    public void display(){
        for(int i = 0; i < numplayers; i++){
            System.out.println(players[i].getName()+":   wins: "+ wins[i]+"   losses: "+losses[i]+"   ties: "+ties[i]+"   games played: "+gamesplayed[i]);
        }
    }
    public void addWin(Player p){
        for(int i = 0; i < numplayers; i++){
            if(players[i].equals(p)){
                wins[i]+=1;
                gamesplayed[i]+=1;
            }
        }
    }
    public void addLoss(Player p){
        for(int i = 0; i < numplayers; i++){
            if(players[i].equals(p)){
                losses[i]+=1;
                gamesplayed[i]+=1;
            }
        }
    }
    public void addTie(Player p, Player q){
        for(int i = 0; i < numplayers; i++){
            if(players[i].equals(p)|| players[i].equals(q)){
                ties[i]+=1;
                gamesplayed[i]+=1;
            }
        }
    }
}