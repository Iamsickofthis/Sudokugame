package labpack;

import java.util.Scanner;

public class Sudokugame {

    private static final int SIZE = 9; // Size of the grid
    private int[][] board; // The sudoku board

    public Sudokugame(int[][] board) {
        this.board = board;
    }

    private boolean isValid(int row, int col, int number) {
        // Row check
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }

        // Column check
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }

        // Subgrid check
        int sqrt = (int) Math.sqrt(SIZE);
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;

        for (int r = rowStart; r < rowStart + sqrt; r++) {
            for (int c = colStart; c < colStart + sqrt; c++) {
                if (board[r][c] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    // Solves the Sudoku puzzle using backtracking
    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) { // Empty cell
                    for (int number = 1; number <= SIZE; number++) {
                        if (isValid(row, col, number)) {
                            board[row][col] = number;

                            if (solve()) { // Recursion
                                return true;
                            } else { // Backtrack
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true; // Puzzle solved
    }

    public void displayBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                System.out.print(" " + board[r][c]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] sudokuboard = {
            {2, 5, 7, 1, 3, 8, 6, 4, 9},
            {8, 3, 1, 6, 4, 9, 2, 5, 7},
            {6, 4, 9, 2, 5, 7, 1, 3, 8},
            {9, 6, 4, 5, 7, 2, 8, 1, 3},
            {7, 2, 5, 3, 8, 1, 9, 6, 4},
            {1, 8, 3, 4, 9, 6, 7, 2, 5},
            {3, 1, 8, 7, 2, 5, 4, 9, 6},
            {4, 9, 6, 8, 1, 3, 5, 7, 2},
            {5, 7, 2, 9, 6, 4, 3, 8, 0}
        };

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the row, column, and number to fill (e.g., 1 2 3 for row 1, column 2, number 3):");
        while (true) {
            System.out.print("Enter row (1-9) or 0 to finish: ");
            int row = scanner.nextInt();
            if (row == 0) break;
            System.out.print("Enter column (1-9): ");
            int col = scanner.nextInt();
            System.out.print("Enter number (1-9): ");
            int number = scanner.nextInt();

            if (row >= 1 && row <= 9 && col >= 1 && col <= 9 && number >= 1 && number <= 9) {
                sudokuboard[row - 1][col - 1] = number;
            } else {
                System.out.println("Invalid input. Please enter numbers between 1 and 9.");
            }
        }

        Sudokugame game = new Sudokugame(sudokuboard);

        if (game.solve()) {
            System.out.println("Sudoku solved!");
            game.displayBoard();
        } else {
            System.out.println("Unsolvable Sudoku");
        }

        scanner.close();
    }
}



