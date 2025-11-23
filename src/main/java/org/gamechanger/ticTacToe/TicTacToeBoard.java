package org.gamechanger.ticTacToe;

import org.gamechanger.boardGameCommons.GameBoard;
import org.gamechanger.boardGameCommons.Move;
import org.gamechanger.connect4.model.BoardElement;

import java.util.List;

public class TicTacToeBoard extends GameBoard {
    private final TicTacToeConstants constants = new TicTacToeConstants();
    public TicTacToeBoard(List<BoardElement> boardElements) {
        super(boardElements);
    }
    @Override
    public boolean validateRowInput(int row) {
        return false;
    }
    @Override
    public List<BoardElement> getPlayerSpaces(String token) {
        return null;
    }
    @Override
    public List<BoardElement> getEmptySpaces() {
        return null;
    }

    @Override
    public void editBoard(Move move, String playerMove) {

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
        System.out.println("     1   2   3");
        return stringBuilder.toString();
    }
}
