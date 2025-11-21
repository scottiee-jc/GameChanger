package org.gamechanger.ticTacToe;

import org.gamechanger.boardGameCommons.GameBoard;
import org.gamechanger.connect4.model.BoardElement;

import java.util.List;

public class TicTacToeBoard extends GameBoard {
    public TicTacToeBoard(List<BoardElement> boardElements) {
        super(boardElements);
    }

    @Override
    public boolean validateRowInput(int row) {
        return false;
    }

    @Override
    public List<BoardElement> getByColumn(int column) {
        return null;
    }

    @Override
    public List<BoardElement> getPlayerSpaces(String token) {
        return null;
    }

    @Override
    public List<BoardElement> getEmptySpaces() {
        return null;
    }
}
