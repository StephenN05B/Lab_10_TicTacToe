import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static String currentPlayer = "X";
    private static int moveCount = 0;

    public static void main(String[] args) {
        clearBoard();
        boolean gameOver = false;

        while (!gameOver) {
            display();  // Show the board
            int row = GetRangedInt("Enter the row (1-3): ", 1, 3) - 1;
            int col = GetRangedInt("Enter the column (1-3): ", 1, 3) - 1;

            while (!isValidMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                row = GetRangedInt("Enter the row (1-3): ", 1, 3) - 1;
                col = GetRangedInt("Enter the column (1-3): ", 1, 3) - 1;
            }

            board[row][col] = currentPlayer;
            moveCount++;

            if (isWin(currentPlayer)) {
                display();
                System.out.println(currentPlayer + " wins!");
                gameOver = true;
            } else if (isTie()) {
                display();
                System.out.println("It's a tie!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
            }
        }

        if (GetY_N("Do you want to play again? (y/n): ")) {
            main(args);
        } else {
            System.out.println("Thanks for playing!");
        }
    }


    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("---------");
        for (int row = 0; row < ROWS; row++) {
            System.out.print("| ");
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("---------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        if (moveCount == ROWS * COLS) {
            return true;
        }
        return false;
    }

    private static int GetRangedInt(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                if (num >= min && num <= max) {
                    return num;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.next();
            }
        }
    }

    private static boolean GetY_N(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input! Please enter 'y' or 'n'.");
            }
        }
    }
}

