package connect4;

public class BoardPlace {
    private final int columnPosition;
    private String boardPiece;

    public BoardPlace(int columnPosition, String boardPiece){
        this.columnPosition = columnPosition;
        this.boardPiece = boardPiece;
    }

    public int getColumnPosition(){
        return columnPosition;
    }

    public String getBoardPiece(){
        return boardPiece;
    }

    public void placeMove(String playerMove){
        if (this.columnPosition == 7){
            this.boardPiece = "| " + playerMove + " |";
        } else{
            this.boardPiece = "| " + playerMove + " ";
        }
    }

}
