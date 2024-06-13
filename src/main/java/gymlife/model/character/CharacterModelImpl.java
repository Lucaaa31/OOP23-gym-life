package gymlife.model.character;


import java.util.function.BiFunction;

import gymlife.model.character.api.CharacterModel;
import gymlife.utility.Position;
import gymlife.utility.Constants;
import gymlife.utility.Directions;

/**
 * Implementation of the CharacterModel interface.
 * This class represents the character in the game and provides methods for managing its movement.
 */
public class CharacterModelImpl implements CharacterModel {

    private Position pos = Constants.CHARACTER_START_POS;

    /**
     * Retrieves the current position of the character.
     * @return The position of the character.
     */
    @Override
    public Position getCharacterPos() {
        return pos;
    }

    /**
     * Move the character in  the given direction.
     * @param dir The new direction for the character's movement.
     */
    @Override
    public void move(final Directions dir) {
        final BiFunction<Integer, Integer, Position> newPosition = Position::new;
        pos = newPosition.apply(pos.X() + dir.getPos().X(), pos.Y() + dir.getPos().Y());
    }

    /**
     * Method to jump the player to a given position.
     * @param pos new position to move the player to.
     */
    @Override
    public void setPosition(final Position pos) {
        this.pos = pos;
    }

}
