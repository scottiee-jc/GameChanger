package org.gamechanger.connect4.constants;

/**
 * Directions enum encapsulates each directional position into single, well-defined variables for iteration during scanning
 */
public enum Directions {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, -1),
    UP_RIGHT(1, -1),
    DOWN_LEFT(-1, 1),
    DOWN_RIGHT(1, 1);
    private int columnPos;
    private int rowPos;

    Directions(int columnPos, int rowPos) {
        this.columnPos = columnPos;
        this.rowPos = rowPos;
    }

    public int getColumnPos() {
        return columnPos;
    }

    public int getRowPos() {
        return rowPos;
    }
}
