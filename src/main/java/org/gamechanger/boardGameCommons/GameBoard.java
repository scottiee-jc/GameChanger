package org.gamechanger.boardGameCommons;

import org.gamechanger.connect4.model.BoardElement;

import java.util.List;

public abstract class GameBoard {

    private final List<BoardElement> boardElements;

    public GameBoard(List<BoardElement> boardElements) {
        this.boardElements = boardElements;
    }

    public List<BoardElement> getBoardElements() {
        return boardElements;
    }

    public List<BoardElement> getByRow(int row){
        return getBoardElements().stream().filter(boardElement -> boardElement.getRowPosition() == row).toList();
    }
    public List<BoardElement> getByColumn(int column){
        return getBoardElements().stream().filter(boardElement -> boardElement.getRowPosition() == column).toList();
    }

    public abstract boolean validateRowInput(int row);
    public abstract List<BoardElement> getPlayerSpaces(String token);
    public abstract List<BoardElement> getEmptySpaces();
    public abstract void editBoard(Move move, String playerMove);
}
