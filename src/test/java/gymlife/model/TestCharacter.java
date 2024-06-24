package gymlife.model;

import gymlife.model.character.CharacterImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import gymlife.model.character.api.Character;
import gymlife.utility.Position;
import gymlife.utility.Direction;
import gymlife.utility.Constants;

import java.util.Locale;

/**
 * This class contains unit tests for the CharacterModel class.
 */
class TestCharacter {
    /**
     * Test case to verify the initial position of the character.
     */
    @Test
    void testInitialPos() {
        final Character character = new CharacterImpl();
        assertEquals(Constants.CHARACTER_START_POS, character.getCharacterPos());
    }
    /**
     * Test case to verify the movement of the character in different directions.
     */
    @Test
    void testMove() {
        final Character character = new CharacterImpl();
        Position pos = Constants.CHARACTER_START_POS;

        character.move(Direction.UP);
        assertEquals("up", Direction.UP.toString().toLowerCase(Locale.ROOT));
        pos = new Position(pos.X(), pos.Y() - Constants.MOVEMENT);
        assertEquals(pos, character.getCharacterPos());

        character.move(Direction.DOWN);
        pos = new Position(pos.X(), pos.Y() + Constants.MOVEMENT);
        assertEquals(pos, character.getCharacterPos());

        character.move(Direction.LEFT);
        pos = new Position(pos.X() - Constants.MOVEMENT, pos.Y());
        assertEquals(pos, character.getCharacterPos());

        character.move(Direction.RIGHT);
        pos = new Position(pos.X()  + Constants.MOVEMENT, pos.Y());
        assertEquals(pos, character.getCharacterPos());
    }

    /**
     * Test Character setting position.
     */
    @Test
    void testSetPos() {
        final Character character = new CharacterImpl();
        final Position position = new Position(2, 3);
        assertEquals(Constants.CHARACTER_START_POS, character.getCharacterPos());
        character.setPosition(position);
        assertEquals(character.getCharacterPos(), position);
    }
}
