
class Board {
    private int[][] board;
    private int boardWidth;
    private int boardHeight;
    
    Board(int width, int height) {
        boardWidth = width;
        boardHeight = height;
        initialiseBoard();
    }
    
    Board(int[][] boardCopy) {
        board = new int[boardCopy.length][boardCopy[0].length];
        for (int x = 0; x < boardCopy.length; x++)
        for (int y = 0; y < boardCopy[0].length; y++)
            board[x][y] = boardCopy[x][y];
    }
    
    // Initialise a new Board[width][height].
    private void initialiseBoard() {
        board = new int[boardWidth][boardHeight];
        
        for (int x = 0; x < boardWidth; x++) 
            for (int y = 0; y < boardHeight; y++) 
                board[x][y] = 0;
    }
    
    public Board getBoard() {
        return new Board(board);
    }
    
    public int getToken(int x, int y) {
        return board[x][y];
    }
    
    // Display the current Board state, O = player 1, X = Player 2.
    public void displayBoard() {
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
    
    // Place a Disk in the given column, if the column is not valid ignore the move.
    public boolean placeDisk(int column, int playersTurn) {
        boolean validMove = false;
        
        // Starting from the bottom of the board, move upwards until an empty space is found
        // and put the current playersTurn token in that spot.
        for (int y = boardHeight - 1; y >= 0 && !validMove; y--) {
            if (board[column][y] == 0) {
                board[column][y] = playersTurn;
                validMove = true;
            }
        }
        
        if (!validMove)
            System.out.println("Invalid move by Player " + playersTurn + ". Column " + column + " is already full.");
        
        return validMove;
    }
    
    // Check if either player has won.
    public boolean checkWin() {
        System.out.println("Checking Win");
        
        if (checkDiagonalLeft() || checkDiagonalRight() || checkHorizontal() || checkVertical()) 
            return true;
        
        return false;
    }
    
    // Checks if the top row of the game has an empty space.
    public boolean checkFreeSpaces() {
        for (int x = 0; x < boardWidth; x++)
            if (board[x][0] == 0)
                return true;
        
        return false;
    }
    
    // Check for horizontal win by either player.
    public boolean checkHorizontal() {
        int currentStreak;
        
        for (int player = 1; player <= 2; player++) {   // Cycle through both players to see if either has won.
            for (int y = 0; y < boardHeight; y++) {     // From top to bottom
                currentStreak = 0;                      // Each row reset the current streak (Aiming for 4 in a row)
                
                for (int x = 0; x < boardWidth; x++) {  // From left to right
                    if (board[x][y] == player)          // If the current token == current player tested, increment streak.
                        currentStreak++;
                    else                                // Otherwise reset the streak and try again.
                        currentStreak = 0;
                    
                    if (currentStreak == 4) {           // If the streak hits 4, the current player has won.
                        System.out.println("Player " + player + " gets a horizontal win! (" + x + ", " + y + ")");
                        return true;
                    }
                } // End for x
            } // End for y
        }// End for player
        
        return false;
    }    
    
    public boolean checkVertical() {
        int currentStreak;
        
        for (int player = 1; player <= 2; player++) {   // Cycle through both players to see if either has won.
            for (int x = 0; x < boardWidth; x++) {      // From left to right
                currentStreak = 0;                      // Each row reset the current streak (Aiming for 4 in a row)
                
                for (int y = 0; y < boardHeight; y++) { // From top to bottom,
                    if (board[x][y] == player)          // If the current token == current player tested, increment streak.
                        currentStreak++;
                    else                                // Otherwise reset the streak and try again.
                        currentStreak = 0;
                    
                    if (currentStreak == 4) {           // If the streak hits 4, the current player has won.
                        System.out.println("Player " + player + " gets a horizontal win! (" + x + ", " + y + ")");
                        return true;
                    }
                } // End for y
            } // End for x
        }// End for player
        
        return false;
    }    
    
    public boolean checkDiagonalRight() {
        int currentStreak;
        
        for (int player = 1; player <= 2; player++) {       // Cycle through both players to see if either has won.
            for (int x = 0; x < boardWidth - 4; x++) {      // Left to right, but stop 4 from the right because diagonal would exceeds board width
                for (int y = 0; y < boardHeight - 4; y++) { // Top to bottom, but stop 4 from the bottom because diagonal would exceeds board height 
                    currentStreak = 0;
                    for (int i = 0; i < 4; i++) {           // Check down the diagonal 4 spaces and check if the streak == 4
                        if (board[x + i][y + i] == player)
                            currentStreak++;
                        else
                            currentStreak = 0;
                    }
                    
                    if (currentStreak == 4) {
                        System.out.println("Player " + player + " gets a diagonal right win! (" + x + ", " + y + ")");
                        return true;
                    }
                } // End for y
            } // End for x
        }// End for player
        
        return false;
    }
    
    public boolean checkDiagonalLeft() {
        int currentStreak = 0;
        
        for (int player = 1; player <= 2; player++) {       // Cycle through both players to see if either has won.
            for (int x = 4; x < boardWidth; x++) {          // Left to right, but start 4 from the left because diagonal would exceeds board width
                for (int y = 0; y < boardHeight - 4; y++) { // Top to bottom, but stop 4 from the bottom because diagonal would exceeds board height 
                    currentStreak = 0;
                    for (int i = 0; i < 4; i++) {           // Check down the diagonal 4 spaces and check if the streak == 4
                        if (board[x - i][y + i] == player)
                            currentStreak++;
                        else
                            currentStreak = 0;
                    }
                    
                    if (currentStreak == 4) {
                        System.out.println("Player " + player + " gets a diagonal left win! (" + x + ", " + y + ")");
                        return true;
                    }
                } // End for y
            } // End for x
        }// End for player
        
        return false;
    }
    
    public boolean isColumnFull(int column) {
        return board[column][0] != 0;
    }
    
    public int getBoardWidth() {
        return board.length;
    }
    
    public int getBoardHeight() {
        return board[0].length;
    }
}