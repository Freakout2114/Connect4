import java.util.Random;

public class HarryAI extends AI
{
    int boardWidth = 7;
    int boardHeight = 6;
    
    HarryAI() {
    }
    
    public int getNextMove(int[][] board) {
        System.out.println("Thinking of next move");
        boardWidth = board.length;
        boardHeight = board[0].length;
        
        Random rand = new Random();

        int x = rand.nextInt(board.length);
        int y = 0;
        while(board[x][0] != 0)
            x = rand.nextInt(board.length);
        
        int blockColumn = lookAhead1(board);
        if (blockColumn != -1)
            x = blockColumn;
        
        System.out.println("Coords: " + x + ", " + y);
        
        return x;
    }
    
    public int lookAhead1(int[][] board) {
        System.out.println("LookAhead1");
        int[][] possibleBoard;
        
        for (int player = 0; player <= 1; player++)
        for (int x = 0; x < boardWidth; x++) {
            possibleBoard = new int[boardWidth][board[0].length];
            copyBoard(board, possibleBoard);
            
            System.out.println("Player: " + ((playerNumber + player) % 2 + 1) + " Possible move column: " + x);
            
            placeDisk(possibleBoard, x, ((playerNumber + player) % 2 + 1));
            //displayBoard(possibleBoard);
            
            if (checkWin(possibleBoard)) {
                System.out.println("The other player can win next move!");
                return x;
            }
        }
        
        return -1;
    }
    
    public void copyBoard(int[][] board, int[][] dest) {
        for (int x = 0; x < board.length; x++)
        for (int y = 0; y < board[0].length; y++)
            dest[x][y] = board[x][y];
    }
    
    public void displayBoard(int[][] board) {
        String output;
        
        for (int y = 0; y < boardHeight; y++) {
            output = "# ";
            for (int x = 0; x < boardWidth; x++) {
                switch (board[x][y]) {
                    case 0: output += "  "; break;
                    case 1: output += "O "; break;
                    case 2: output += "X "; break;
                }
            }
            output += "#";
            System.out.println(output);
        }
        
        output = "# ";
        for (int x = 0; x < boardWidth; x++) 
            output += x + " ";
        
        System.out.println(output + "#\n");
    }
    
    public boolean placeDisk(int[][] board, int column, int playersTurn) {
        boolean validMove = false;
        
        for (int y = boardHeight - 1; y >= 0 && !validMove; y--) {
            if (board[column][y] == 0) {
                board[column][y] = playersTurn;
                validMove = true;
            }
        }
        
        if (!validMove)
            System.out.println("Error: Not a valid move. Player " + playersTurn + " Column: " + column);
        
        return validMove;
    }
    
    public boolean checkWin(int[][] board) {
        if (checkDiagonalLeft(board) || checkDiagonalRight(board) || checkHorizontal(board) || checkVertical(board)) 
            return true;
        
        return false;
    }
    
    public boolean checkHorizontal(int[][] board) {
        int currentStreak = 0;
        
        for (int player = 1; player <= 2; player++) {
            for (int y = 0; y < boardHeight; y++) {
                currentStreak = 0;
                
                for (int x = 0; x < boardWidth; x++) {
                    if (board[x][y] == player)
                        currentStreak++;
                    else
                        currentStreak = 0;
                    
                    if (currentStreak == 4) {
                        System.out.println("Player " + player + " gets a horizontal win! (" + x + ", " + y + ")");
                        return true;
                    }
                }
            }
        }
        
        return false;
    }    
    
    public boolean checkVertical(int[][] board) {
        int currentStreak = 0;
        
        for (int player = 1; player <= 2; player++) {
            for (int x = 0; x < boardWidth; x++) {
                currentStreak = 0;
                
                for (int y = 0; y < boardHeight; y++) {
                    if (board[x][y] == player)
                        currentStreak++;
                    else
                        currentStreak = 0;
                    
                    if (currentStreak == 4) {
                        System.out.println("Player " + player + " gets a vertical win! (" + x + ", " + y + ")");
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean checkDiagonalRight(int[][] board) {
        int currentStreak = 0;
        
        for (int player = 1; player <= 2; player++) {
            for (int x = 0; x < boardWidth - 4; x++) {
                for (int y = 0; y < boardHeight - 4; y++) {
                    currentStreak = 0;
                    for (int i = 0; i < 4; i++) {
                        if (board[x + i][y + i] == player)
                            currentStreak++;
                        else
                            currentStreak = 0;
                    }
                    
                    if (currentStreak == 4) {
                        System.out.println("Player " + player + " gets a diagonal right win! (" + x + ", " + y + ")");
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean checkDiagonalLeft(int[][] board) {
        int currentStreak = 0;
        
        for (int player = 1; player <= 2; player++) {
            for (int x = 4; x < boardWidth; x++) {
                for (int y = 0; y < boardHeight - 4; y++) {
                    currentStreak = 0;
                    for (int i = 0; i < 4; i++) {
                        if (board[x - i][y + i] == player)
                            currentStreak++;
                        else
                            currentStreak = 0;
                    }
                    
                    if (currentStreak == 4) {
                        System.out.println("Player " + player + " gets a diagonal left win! (" + x + ", " + y + ")");
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}