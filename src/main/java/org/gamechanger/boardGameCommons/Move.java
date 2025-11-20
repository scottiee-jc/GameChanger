package org.gamechanger.boardGameCommons;

/**
 * Move is a data transfer object (DTO) used to track game state.
 * The class is final as the internal state should not be modified, i.e. it is immutable
 */
public final class Move {
    private final int column;
    private final int row;

    public Move(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
