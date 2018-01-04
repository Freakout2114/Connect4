
public abstract class AI {
    protected int playerNumber;
    
    public abstract int getNextMove(Board board);
    
    public AI() {
    }
    
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    
}