package org.gamechanger.connect4.model;

import org.gamechanger.boardGameCommons.GameBoard;
import org.gamechanger.connect4.constants.ConnectFourConstants;

import java.util.List;

import static org.gamechanger.boardGameCommons.CommonConstants.*;

public class ConnectFourBoard extends GameBoard {

    private final ConnectFourConstants constants = new ConnectFourConstants();

    public ConnectFourBoard(List<BoardElement> boardElements) {
        super(boardElements);
    }

    public List<BoardElement> getByRow(int row){
        return getBoardElements().stream().filter(boardElement -> boardElement.getRowPosition() == row).toList();
    }

    @Override
    public List<BoardElement> getByColumn(int column){
        return getBoardElements().stream().filter(boardElement -> boardElement.getRowPosition() == column).toList();
    }

    @Override
    public List<BoardElement> getPlayerSpaces(String token){
        return getBoardElements().stream().filter(boardElement -> boardElement.getBoardPlace().contains(token)).toList();
    }
    @Override
    public List<BoardElement> getEmptySpaces(){
        return getBoardElements().stream().filter(boardElement -> !boardElement.getBoardPlace().contains("r") && !boardElement.getBoardPlace().contains("y")).toList();
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
