package gymlife.model.character;


import java.util.function.BiFunction;

import gymlife.model.character.api.Character;
import gymlife.utility.Position;
import gymlife.utility.Constants;
import gymlife.utility.Direction;

/**
 * Implementation of the CharacterModel interface.
 * This class represents the character in the game and provides methods for managing its movement.
 */
public class CharacterImpl implements Character {
    private Position characterPos = Constants.CHARACTER_START_POS;

    /**
     * Retrieves the current position of the character.
     * @return The position of the character.
     */
    @Override
    public Position getCharacterPos() {
        return characterPos;
    }

    /**
     * Move the character in  the given direction.
     * @param dir The new direction for the character's movement.
     */
    @Override
    public void move(final Direction dir) {
        final BiFunction<Integer, Integer, Position> newPosition = Position::new;
        characterPos = newPosition.apply(characterPos.X() + dir.getOffSet().X(), characterPos.Y() + dir.getOffSet().Y());
    }

    /**
     * Method to jump the player to a given position.
     * @param pos new position to move the player to.
     */
    @Override
    public void setPosition(final Position pos) {
        this.characterPos = pos;
    }

}
