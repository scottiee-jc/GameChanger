package org.gamechanger;

import org.gamechanger.connect4.Directions;
import org.gamechanger.connect4.BoardElement;

import java.util.List;
import java.util.zip.DataFormatException;

public interface GameValidatorService {

    boolean isValidInput(String input) throws DataFormatException;
    boolean isVictorious(String token);
    boolean hasConsecutivePlayerMovesInDirection(List<BoardElement> playerSpaces, Directions directions, int count);
}
