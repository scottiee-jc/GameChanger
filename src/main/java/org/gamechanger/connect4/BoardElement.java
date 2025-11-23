package org.gamechanger.connect4;

public class BoardElement {

    private final int columnPosition;
    private final int rowPosition;
    private String boardPlace;

    public BoardElement(int columnPosition, int rowPosition, String boardPlace) {
        this.columnPosition = columnPosition;
        this.rowPosition = rowPosition;
        this.boardPlace = boardPlace;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public String getBoardPlace() {
        return boardPlace;
    }

    public void setBoardPlace(String player) {
        if (this.columnPosition == 7){
            this.boardPlace = "| " + player + " |";
        } else{
            this.boardPlace = "| " + player + " ";
        }
    }

    @Override
    public String toString() {
        return this.boardPlace;
    }
}
