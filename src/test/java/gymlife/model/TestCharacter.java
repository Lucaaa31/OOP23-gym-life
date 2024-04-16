package gymlife.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;


import gymlife.model.api.CharacterModel;
import gymlife.model.CharacterModelImpl;
import gymlife.utility.Position;
import gymlife.utility.Directions;
import gymlife.utility.Constants;


public class TestCharacter {

    @Test
    void testInitialPos() {
        final CharacterModel character = new CharacterModelImpl();
        assertEquals(Constants.CHARACTER_START_POS, character.getCharacterPos());
    }

    @Test
    void testMove() {
        final CharacterModel character = new CharacterModelImpl();
        Position pos = Constants.CHARACTER_START_POS;

        character.move(Optional.of(Directions.UP));
        pos = new Position(pos.X(), pos.Y() - Constants.MOVEMENT);
        assertEquals(pos, character.getCharacterPos());

        character.move(Optional.of(Directions.DOWN));
        pos = new Position(pos.X(), pos.Y() + Constants.MOVEMENT);
        assertEquals(pos, character.getCharacterPos());

        character.move(Optional.of(Directions.LEFT));
        pos = new Position(pos.X() - Constants.MOVEMENT, pos.Y());
        assertEquals(pos, character.getCharacterPos());

        character.move(Optional.of(Directions.RIGHT));
        pos = new Position(pos.X()  + Constants.MOVEMENT, pos.Y());
        assertEquals(pos, character.getCharacterPos());

    }
}
