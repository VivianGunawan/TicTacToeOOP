public class Checker{
    //Field
    private char checker;
    //Constructor
    public Checker(){
        this.checker = '\0';
    }
    //Methods
    public char getChecker(){
        return this.checker;
    }
    //allows players to change their checker
    public void setChecker(char c){
        this.checker = c;
    }
    @Override
    public boolean equals(Object o){
        if (o==this){
            return true;
        }
        if (!(o instanceof Checker)){
            return false;
        }
        Checker other= (Checker) o;
        return checker==(other.checker);
    }
}