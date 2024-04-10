package gymlife.model;

import java.util.Optional;
import java.util.function.BiFunction;

import gymlife.utility.Position;
import gymlife.utility.Constants;
import gymlife.utility.Directions;
import gymlife.model.api.CharacterModel;

/**
 * Implementation of the CharacterModel interface.
 * This class represents the character in the game and provides methods for managing its movement.
 */
public class CharacterModelImpl implements CharacterModel {

    private static Position pos = Constants.CHARACTER_START_POS;

    /**
     * Retrieves the current position of the character.
     * @return The position of the character.
     */
    @Override
    public Position getCharacterPos() {
        return pos;
    }

    /**
     * Sets a new direction for the character's movement.
     * @param dir The new direction for the character's movement.
     */
    @Override
    public void setNewDir(final Optional<Directions> dir) {
        dir.ifPresent(direction -> {
            final BiFunction<Integer, Integer, Position> newPosition = (x, y) -> new Position(x, y);
            pos = newPosition.apply(pos.X() + Directions.getOffset(direction).get().X(), 
                                     pos.Y() + Directions.getOffset(direction).get().Y());
        });
    }
}
