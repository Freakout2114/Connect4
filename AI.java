
public abstract class AI 
{
    protected int playerNumber;
    
    public abstract int getNextMove(int[][] board);
    
    AI() {
    }
    
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    
}