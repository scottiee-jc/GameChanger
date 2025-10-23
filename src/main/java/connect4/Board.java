package connect4;

import java.util.*;

public class Board {
    private List<BoardElement> boardElements;

    public Board(List<BoardElement> boardElements) {
        this.boardElements = boardElements;
    }

    public List<BoardElement> getBoardElements() {
        return boardElements;
    }

    public void setBoardElements(List<BoardElement> boardElements) {
        this.boardElements = boardElements;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (BoardElement r: boardElements) {
            stringBuilder.append(r.toString());
            stringBuilder.append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1); // remove the last empty line
        return stringBuilder.toString();
    }

    public Column arrangeAsColumn(int index){
        List<BoardPlace> rowElements = new LinkedList<>();

        boardElements.forEach(element -> {
            BoardPlace place = element.getBoardPlaces().get(index); // fetches column from each row
            int rowPosition = element.getPosition(); // set equal to the position of the row
            BoardPlace bp = new BoardPlace(rowPosition, place.getBoardPiece()); // board piece stays the same, but position val changes
            rowElements.add(bp);
        });
        return new Column(index+1, rowElements);
    }

    public List<BoardElement> getListOfColumns(){
        List<BoardElement> columns = new ArrayList<>(7);
        for (int i = 0; i < getBoardElements().size()+1; i++){ // set to size of row list + 1;
            Column column = arrangeAsColumn(i);
            columns.add(column);
        }
        return columns;
    }

}
