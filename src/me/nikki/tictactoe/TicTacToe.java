package me.nikki.tictactoe;

import java.util.Scanner;

/**
 * Created by Nikki on 03/09/2016.
 */
public class TicTacToe {
    static final String INITAL_SQUARE_VALUE = " ";
    String[][] board = new String[][] {
            {INITAL_SQUARE_VALUE,INITAL_SQUARE_VALUE,INITAL_SQUARE_VALUE},
            {INITAL_SQUARE_VALUE,INITAL_SQUARE_VALUE,INITAL_SQUARE_VALUE},
            {INITAL_SQUARE_VALUE,INITAL_SQUARE_VALUE,INITAL_SQUARE_VALUE}};
    static final String PLAYER_1 = "Player 1";
    static final String PLAYER_2 = "Player 2";
    String turn = PLAYER_2;

    public static void main(String[] args) {
        TicTacToe newGame = new TicTacToe();
        newGame.play();
    }

    String getCurrentPiece() {
        if (turn.equals(PLAYER_1)) {
            return "O";
        } else if (turn.equals(PLAYER_2)){
            return "X";
        } else {
            throw new RuntimeException();
        }
    }
    void changePlayer() {
        if (turn.equals(PLAYER_1)) {
            turn = PLAYER_2;
        } else if (turn.equals(PLAYER_2)){
            turn = PLAYER_1;
        } else {
            throw new RuntimeException();
        }
    }
    void placePiece(int rowIndex, int columnIndex ) {
        board[rowIndex][columnIndex] = getCurrentPiece();
    }

    boolean playerWins() {
       rowSearch:
        for (String[] row:board) {
            for (String square:row) {
                if (!square.equals(getCurrentPiece())) {
                    continue rowSearch;
                }
            }
            return true;
        }
        columnSearch:
        for (int columnIndex = 0; columnIndex < board[0].length; ++columnIndex ) {
            for (int rowIndex = 0; rowIndex < board.length; ++rowIndex) {
                if (!board[rowIndex][columnIndex].equals(getCurrentPiece())) {
                    continue columnSearch;
                }
            }
            return true;
        }

        boolean checkvariable = true;
        for (int i = 0; i < board.length; ++i) {
            if (!board[i][i].equals(getCurrentPiece())) {
                checkvariable = false;
                break;
            }

        }
        if (checkvariable == true){
            return true;
        }
        checkvariable = true;
        for (int i = 0; i < board.length; ++i) {
            if (!board[i][board.length - 1 - i].equals(getCurrentPiece())) {
                checkvariable = false;
                break;
            }

        }
        if (checkvariable == true){
            return true;
        }
        return false;
    }



    void playerTurn() {
        int rowIndex;
        int columnIndex;
        do {
            Scanner in = new Scanner(System.in);
            rowIndex = in.nextInt();
            columnIndex = in.nextInt();
        } while (!board[rowIndex][columnIndex].equals(INITAL_SQUARE_VALUE));
        placePiece(rowIndex, columnIndex);
        // TODO: Add check if won function

    }

    void play() {
        printBoard();
        int turnCounter = 0;
        do {
            changePlayer();
            playerTurn();
            printBoard();
            ++turnCounter;
        } while (playerWins() == false && turnCounter < 9 );
        if (playerWins() == true) {
            System.out.println(turn + " wins");
        } else {
            System.out.println("It's a draw");
        }
    }
    void printBoard() {
        for (int rowIndex = 0; rowIndex < board.length - 1; ++rowIndex ) {
            String[] row = board[rowIndex];
            printRow(row);
            System.out.println("-+-+-");
        }
        printRow(board[board.length-1]);
        System.out.println();
    }
    void printRow(String[] row){
        for (int i = 0; i < row.length - 1; ++i) {
            System.out.print(row[i] + "|");

        }
        System.out.println(row[row.length - 1]);
    }


}