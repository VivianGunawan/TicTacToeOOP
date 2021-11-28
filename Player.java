public class Player{
    //Fields
    private String name;
    private Checker checker;
    //Constructor
    public Player(){
        this.name = null;
        this.checker = new Checker();
    }
    //Methods
    public String getName(){
        return this.name;
    }
    public void setName(String n){
        this.name = n;
    }
    public void removeChecker(){
        this.checker = null;
    }
    public Checker getChecker(){
        return this.checker;
    }
    public void setChecker(Checker c){
        this.checker=c;
    }
    public void setCheckerDirect(char c){
        this.checker.setChecker(c);
    }
    @Override
    public boolean equals(Object o){
        if (o==this){
            return true;
        }
        if (!(o instanceof Player)){
            return false;
        }
        Player other= (Player) o;
        return (name==other.name) && checker.equals(other.checker);
    }
}