import java.util.Scanner;

public class tic { 
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Instructions: Players take turns entering row and column (0-2).");
        System.out.println("Example: '0 1' places your mark in Row 1, Column 2.");

        while(playAgain){
            initializeBoard();
            playGame(scanner);

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    
    private static void showBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println("    0   1   2"); // Column headers
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " "); // Row labels
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println();
    }

    private static void playGame(Scanner scanner) {
        boolean gameEnded = false;

        while (!gameEnded) {
            showBoard();
            System.out.println("Player " + currentPlayer + "'s turn.");

        
            int row = -1, col = -1;
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Enter row and column (e.g., 0 1): ");
                if (scanner.hasNextInt()) {
                    // System.out.println("enter row value");
                    row = scanner.nextInt();
                    // System.out.println("enter col value");
                    col = scanner.nextInt();

                    
                    if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-'){
                        validInput = true;
                    } 
                    else{
                        System.out.println("Invalid move! Cell occupied or out of bounds. Try again.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter numbers only.");
                    scanner.next();// Clear invalid input
                }
            }

            // Update board and check status
            board[row][col] = currentPlayer;
            
            
            if (checkWinner()) {
                showBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (isBoardFull()) {
                showBoard();
                System.out.println("The game is a draw!");
                gameEnded = true;
            } else {
                
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private static boolean checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') return false;
            }
        }
        return true;
    }
}
