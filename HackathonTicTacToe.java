import java.util.Random;
import java.util.Scanner;

public class HackathonTicTacToe {
    static int move;
    static char[][] board;
    static int[][] position = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    static boolean[] block = new boolean[9];
    static boolean display = true;
    static char player;
    static char bot;
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static int playerWins = 0;
    static int botWins = 0;
    static int ties = 0;

    public static void main(String[] args) {
        // Welcome message and assignment of symbols
        System.out.println("Welcome to the Syntax Slayers Tic-Tac-Toe Game!");
        System.out.print("Would you like to be X or O? ");
        String playerChoice = scanner.nextLine();
        System.out.print("How many matches would you like to play? ");
        int matches = scanner.nextInt();

        // Defining each player symbol
        if (playerChoice.equalsIgnoreCase("X")) {
            player = 'X';
            bot = 'O';
        } else if (playerChoice.equalsIgnoreCase("O")) {
            player = 'O';
            bot = 'X';
        } else {
            System.out.println("Invalid Input: " + playerChoice);
            System.out.print("Would you like to be X or O? ");
            playerChoice = scanner.nextLine();
        }

        // Loop through the matches
        for (int i = 1; i <= matches; i++) {
            System.out.println("Match " + i + ":");
            createBoard();
            playGame();

            // Display the current score
            System.out.println("Score after match " + i + ":");
            System.out.println("     Player wins: " + playerWins);
            System.out.println("     Bot wins: " + botWins);
            System.out.println("     Ties: " + ties);
        }

        // Determine the overall winner
        if (matches > 1)
            determineOverallWinner();
        System.out.println("Thank you for playing!");
    }

    // Creation of an empty board for each match
    public static void createBoard() {
        board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        displayNumberBoard();
        printBoard();
    }

    public static void displayNumberBoard() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(position[row][col]);
                if (col < 2) System.out.print(" | ");
            }
            System.out.println();
            if (row < 2) System.out.println("---------");
        }
    }

    // Method to print the board
    public static void printBoard() {

        System.out.println();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + "");
                if (col < 2) System.out.print(" | ");
            }
            System.out.println();
            if (row < 2) System.out.println("---------");
        }
        display = false;
    }

    // Allowing the player to place their move
    public static void playerMove() {

        while (true) {
            System.out.print("Enter your move (1-9): ");
            move = scanner.nextInt();
            int row = (move - 1) / 3;
            int col = (move - 1) % 3;
            if (move >= 1 && move <= 9 && board[row][col] == ' ') {
                board[row][col] = player;
                printBoard();  // Show the board immediately after player's move
                break;
            } else {
                System.out.println("Invalid move.");
            }
        }
    }

    // Bot's move
    public static void botMove(){
        block[move-1] = true;
        while (true) {
            int max=9,min=1;
            int rand = (random.nextInt(max - min + 1) + min);
            rand--; //to get to zero-based position
            if(block[rand] == false){
                int colPos = rand % 3;
                int rowPos = rand / 3;
                board[rowPos][colPos] = bot;

                break;

            }

        }
        printBoard();
    }


    // Main gameplay loop
    public static void playGame() {
        while (true) {
            playerMove();

            if (checkWinner(player)) {
                System.out.println("Player wins this round!");
                playerWins++;
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a tie!");
                ties++;
                break;
            }

            botMove();

            if (checkWinner(bot)) {
                System.out.println("Bot wins this round!");
                botWins++;
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a tie!");
                ties++;
                break;
            }
        }
    }

    // Method for checking if the board is full
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if the current player has won
    public static boolean checkWinner(char currentPlayer) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    // Determine the overall winner after all matches are played
    public static void determineOverallWinner() {
        System.out.println("Final Score:");
        System.out.println("     Player wins: " + playerWins);
        System.out.println("     Bot wins: " + botWins);
        System.out.println("     Ties: " + ties);

        if (playerWins > botWins) {
            System.out.println("Congratulations! You are the overall winner!");
        } else if (botWins > playerWins) {
            System.out.println("Bot is the overall winner! Better luck next time.");
        } else {
            System.out.println("It's a tie overall!");
        }
    }
}