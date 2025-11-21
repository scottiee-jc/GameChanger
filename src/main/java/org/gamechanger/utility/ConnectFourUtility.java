package org.gamechanger.utility;

import org.gamechanger.connect4.constants.ConnectFourConstants;
import org.gamechanger.connect4.model.BoardElement;
import org.gamechanger.connect4.model.ConnectFourBoard;

import java.util.ArrayList;
import java.util.List;

import static org.gamechanger.boardGameCommons.CommonConstants.BOARD_PIECE;
import static org.gamechanger.boardGameCommons.CommonConstants.END_PIECE;

public final class ConnectFourUtility {

    private static final ConnectFourConstants connectFourConstants = new ConnectFourConstants();

    public static void loadStartingMessages(){
        System.out.println(connectFourConstants.WELCOME_MESSAGE());
        System.out.println(connectFourConstants.PLAYERS_INTRO());
        System.out.println(connectFourConstants.HOW_TO_PLAY());
        System.out.println(connectFourConstants.HOW_TO_PLAY_EXAMPLE());
        System.out.println(connectFourConstants.HOW_TO_WIN());
    }

    /**
     * createBoard method iterates over the total number of rows and columns to create 42 elements.
     * For each element, a unique and unmodifiable position is allocated for row and column to form coordinates.
     */

    public static ConnectFourBoard createBoard() {
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
}
