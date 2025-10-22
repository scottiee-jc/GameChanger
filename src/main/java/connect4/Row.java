package connect4;

import java.util.Deque;

public class Row {
    int position; // for ordering
    private Deque<BoardPlace> boardPlaces;
    public Row(int position, Deque<BoardPlace> boardPlaces){
        this.position = position;
        this.boardPlaces = boardPlaces;
    }

    public int getPosition(){
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Deque<BoardPlace> getBoardPlaces() {
        return boardPlaces;
    }

    public void setBoardPlaces(Deque<BoardPlace> boardPlaces) {
        this.boardPlaces = boardPlaces;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(position).append("  ");
        for (BoardPlace bp : boardPlaces) {
            stringBuilder.append(bp.getBoardPiece());
        }
        return stringBuilder.toString();
    }
}
