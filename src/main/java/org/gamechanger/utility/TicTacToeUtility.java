package org.gamechanger.utility;

import org.gamechanger.connect4.model.BoardElement;
import org.gamechanger.ticTacToe.TicTacToeBoard;
import org.gamechanger.ticTacToe.TicTacToeConstants;

import java.util.ArrayList;
import java.util.List;

import static org.gamechanger.boardGameCommons.CommonConstants.BOARD_PIECE;
import static org.gamechanger.boardGameCommons.CommonConstants.END_PIECE;

public final class TicTacToeUtility {
    public TicTacToeUtility() {
    }

    private static final TicTacToeConstants ticTacToeConstants = new TicTacToeConstants();

    public static void loadStartingMessages(){
        System.out.println(ticTacToeConstants.WELCOME_MESSAGE());
        System.out.println(ticTacToeConstants.PLAYERS_INTRO());
        System.out.println(ticTacToeConstants.HOW_TO_PLAY());
        System.out.println(ticTacToeConstants.HOW_TO_PLAY_EXAMPLE());
        System.out.println(ticTacToeConstants.HOW_TO_WIN());
    }

    /**
     * createBoard method iterates over the total number of rows and columns to create 42 elements.
     * For each element, a unique and unmodifiable position is allocated for row and column to form coordinates.
     */

    public static TicTacToeBoard createBoard() {
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
}
