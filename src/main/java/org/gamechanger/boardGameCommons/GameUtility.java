package org.gamechanger.boardGameCommons;

import org.gamechanger.connect4.BoardElement;
import org.gamechanger.connect4.ConnectFourBoard;
import org.gamechanger.connect4.ConnectFourConstants;
import org.gamechanger.connect4.Directions;
import org.gamechanger.ticTacToe.TicTacToeBoard;
import org.gamechanger.ticTacToe.TicTacToeConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

import static org.gamechanger.boardGameCommons.CommonConstants.*;

public final class GameUtility {

    public GameUtility() {
    }

    private static final ConnectFourConstants connectFourConstants = new ConnectFourConstants();
    private static final TicTacToeConstants ticTacToeConstants = new TicTacToeConstants();

    public static void loadConnectFourStartingMessages(){
        System.out.println(connectFourConstants.WELCOME_MESSAGE());
        System.out.println(connectFourConstants.PLAYERS_INTRO());
        System.out.println(connectFourConstants.HOW_TO_PLAY());
        System.out.println(connectFourConstants.HOW_TO_PLAY_EXAMPLE());
        System.out.println(connectFourConstants.HOW_TO_WIN());
    }

    public static void loadTicTacToeStartingMessages(){
        System.out.println(ticTacToeConstants.WELCOME_MESSAGE());
        System.out.println(ticTacToeConstants.PLAYERS_INTRO());
        System.out.println(ticTacToeConstants.HOW_TO_PLAY());
        System.out.println(ticTacToeConstants.HOW_TO_PLAY_EXAMPLE());
        System.out.println(ticTacToeConstants.HOW_TO_WIN());
    }

    public static TicTacToeBoard createTicTacToeBoard() {
        List<BoardElement> elements = new ArrayList<>();
        for (int i = 1; i <= ticTacToeConstants.ROW_SIZE(); i++) {
            for (int j = 1; j <= ticTacToeConstants.COLUMN_SIZE(); j++) {
                BoardElement boardElement;
                if (j == ticTacToeConstants.COLUMN_SIZE()){
                    boardElement = new BoardElement(j,i,END_PIECE);
                } else {
                    boardElement = new BoardElement(j,i,BOARD_PIECE);
                }
                elements.add(boardElement);
            }
        }
        return new TicTacToeBoard(elements);
    }

    public static ConnectFourBoard createConnectFourBoard() {
        List<BoardElement> elements = new ArrayList<>();
        for (int i = 1; i <= connectFourConstants.ROW_SIZE(); i++) {
            for (int j = 1; j <= connectFourConstants.COLUMN_SIZE(); j++) {
                BoardElement boardElement;
                if (j == 7){
                    boardElement = new BoardElement(j,i,END_PIECE);
                } else {
                    boardElement = new BoardElement(j,i,BOARD_PIECE);
                }
                elements.add(boardElement);
            }
        }
        return new ConnectFourBoard(elements);
    }

    /**
     * checkPositions scans for negative positions i.e. in the opposite direction to that specified
     * It checks whether the previous cell in the given direction is also occupied:
     * If so, it moves backward (subtracts the direction offset - for instance DOWN is 0,1 so would look for 3,3 if the previous pos was 3,4) and repeats.
     * Once it reaches a cell that isn't occupied, it stops
     * This way it is able to find the actual first position of the chain, allowing the computer to identify the best spot to block
     */
    public static int[] checkPositions(int row, int col, Set<String> positionSet, Directions direction) {
        while (positionSet.contains((row - direction.getRowPos()) + "," + (col - direction.getColumnPos()))) {
            row -= direction.getRowPos();
            col -= direction.getColumnPos();
        }
        return new int[]{row, col};
    }


    /**
     * Enhances the error handling by catching an exception and printing an error message if there is a non-digit entered
     * input is split also by whitespace and trimmed to ensure length of input is correct - limit defined by length of array
     * @param input
     * @return
     */
    public static String[] formatInput(String input){
        String[] newInput = new String[1];
        try {
            newInput = input.replaceAll("\\s+","").trim().split(",");
        } catch (PatternSyntaxException e){
            System.out.printf(NON_DIGIT_CHAR_ERROR, e.getMessage());
        }
        return newInput;
    }
}
