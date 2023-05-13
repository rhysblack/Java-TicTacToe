public class Player {
    //states
    private int position;
    private boolean win;

    //constructor
    public Player(){

    }

    //behaviour
    public void setPosition(int value){
        this.position = value;
    }

    public int getPosition(){
        return this.position;
    }

    public void setWin(boolean value){
        this.win = value;
    }

    public boolean getWin(){
        return this.win;
    }
}
