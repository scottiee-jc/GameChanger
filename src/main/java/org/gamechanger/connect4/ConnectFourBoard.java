package org.gamechanger.connect4;

import org.gamechanger.boardGameCommons.GameBoard;
import org.gamechanger.boardGameCommons.Move;

import java.util.List;

import static org.gamechanger.boardGameCommons.CommonConstants.*;

public class ConnectFourBoard extends GameBoard {

    private final ConnectFourConstants constants = new ConnectFourConstants();

    public ConnectFourBoard(List<BoardElement> boardElements) {
        super(boardElements);
    }

    @Override
    public void editBoard(Move move, String playerMove) {
        for (BoardElement be : getBoardElements()) {
            if (be.getRowPosition() == move.getRow() && be.getColumnPosition() == move.getColumn()) {
                be.setBoardPlace(playerMove);
                return;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= constants.ROW_SIZE() ; i++) {
            if (i != 0){
                stringBuilder.append(i).append("  ");
            }
            for (BoardElement row: getByRow(i)) {
                stringBuilder.append(row.toString());
            }
            stringBuilder.append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1); // remove the last empty line
        System.out.println("     1   2   3   4   5   6   7");
        return stringBuilder.toString();
    }

    @Override
    public boolean validateRowInput(int row) {
        try {
            if (row < 7){
                return true;
            } else {
                System.out.printf((NOT_VALID_ROW_VAL_ERROR), constants.ROW_SIZE(), row);
                return false;
            }
        } catch (NumberFormatException n){
            System.out.printf((NON_DIGIT_CHAR_ERROR) + "%n", n.getMessage());
            return false;
        }
    }

    public boolean validateColumnInput(int column) {
        try {
            if (column <= 7) {
                return true;
            } else {
                System.out.printf((NOT_VALID_COLUMN_VAL_ERROR), constants.COLUMN_SIZE(), column);
                return false;
            }
        } catch (NumberFormatException n) {
            System.out.printf((NON_DIGIT_CHAR_ERROR) + "%n", n.getMessage());
            return false;
        }
    }

}
