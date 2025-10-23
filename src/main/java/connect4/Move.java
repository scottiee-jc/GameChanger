package connect4;

public final class Move { // move should be immutable as the internal state should not be modified as it tracks game state.
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
