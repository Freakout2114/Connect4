import java.util.Random;

public class HarryAI extends AI {
    
    HarryAI() {
    }
    
    public int getNextMove(Board board) {
        System.out.println("Thinking of next move");
        
        Random rand = new Random();

        int x = rand.nextInt(board.getBoardWidth()); 
        int y = 0;
        while(board.isColumnFull(x));
            x = rand.nextInt(board.getBoardWidth());
        
        System.out.println("Placing Token in (" + x + ", " + y + ")");
        
        return x;
    }
}