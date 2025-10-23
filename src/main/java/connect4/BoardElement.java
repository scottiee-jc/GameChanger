package connect4;

import java.util.List;

public abstract class BoardElement {
    private final int position;
    private final List<BoardPlace> boardPlaces;

    public BoardElement(int position, List<BoardPlace> boardPlaces) {
        this.position = position;
        this.boardPlaces = boardPlaces;
    }

    public int getPosition() {
        return position;
    }

    public List<BoardPlace> getBoardPlaces() {
        return boardPlaces;
    }
}
