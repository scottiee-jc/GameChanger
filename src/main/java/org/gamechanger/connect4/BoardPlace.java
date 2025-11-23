package org.gamechanger.connect4;

public class BoardPlace {
    private final int position;
    private String boardPiece;

    public BoardPlace(int position, String boardPiece){
        this.position = position;
        this.boardPiece = boardPiece;
    }

    public int getPosition(){
        return position;
    }

    public String getBoardPiece(){
        return boardPiece;
    }

    public void placeMove(String playerMove, int index){
        if (index == 7){
            this.boardPiece = "| " + playerMove + " |";
        } else{
            this.boardPiece = "| " + playerMove + " ";
        }
    }
}
