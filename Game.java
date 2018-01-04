
class Game {
    private int boardWidth = 7;
    private int boardHeight = 6;
    private int playersTurn = 1;
    private AI player1;
    private AI player2;
    private Board board;
    
    Game() {
        newGame();
        play();
    }
    
    public void newGame() {
        System.out.println("New Game");
        board = new Board(boardWidth, boardHeight);
        System.out.println("Initialise Players");
        initialisePlayers();
    }
    
    public void initialisePlayers() {
        player1 = new HarryAI();
        player2 = new HarryAI();
        
        player1.setPlayerNumber(1);
        player2.setPlayerNumber(2);
    }
    
    public void play() {
        int round = 0;
        do {
            playersTurn = 3 - playersTurn;
            System.out.println("\nRound: " + round + ", Player " + playersTurn);
            
            if (playersTurn == 1)
                board.placeDisk( player1.getNextMove( board.getBoard() ), playersTurn );
            else
                board.placeDisk( player2.getNextMove( board.getBoard() ), playersTurn );
            board.displayBoard();
            
            round++;
        } while ( !board.checkWin() && board.checkFreeSpaces());
    }
}













