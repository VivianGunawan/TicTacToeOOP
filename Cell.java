public class Cell{
    //Fields
    private boolean isEmpty;
    private Checker content;
    //Constructor
    public Cell(){
        this.isEmpty = true;
        this.content = new Checker();
    }
    //Methods
    public boolean getIsEmpty(){
        return this.isEmpty;
    }
    public void clear(){
        this.isEmpty = true;
        this.content = new Checker();
    }
    public Checker getContent(){
        return this.content;
    }
    public void setContent(Checker x){
        this.content = x;
        this.isEmpty = false;
    }
    @Override
    public boolean equals(Object o){
        if (o==this){
            return true;
        }
        if (!(o instanceof Cell)){
            return false;
        }
        Cell other= (Cell) o;
        return (isEmpty==other.isEmpty) && content.equals(other.content);
    }
}
