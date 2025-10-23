package connect4;

import java.util.List;

public class Row extends BoardElement {

    public Row(int position, List<BoardPlace> boardPlaces) {
        super(position, boardPlaces);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getPosition()).append("  ");
        for (BoardPlace bp : getBoardPlaces()) {
            stringBuilder.append(bp.getBoardPiece());
        }
        return stringBuilder.toString();
    }
}
