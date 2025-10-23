package connect4;

import java.util.List;

public class Column extends BoardElement {

    public Column(int position, List<BoardPlace> boardPlaces) {
        super(position, boardPlaces);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getBoardPlaces().size(); i++) {
            if (getPosition() != 7){
                stringBuilder.append(getBoardPlaces().get(i).getBoardPiece()).append("|");
            } else {
                stringBuilder.append(getBoardPlaces().get(i).getBoardPiece());
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("  ").append(getPosition());
        return stringBuilder.toString();
    }

}
