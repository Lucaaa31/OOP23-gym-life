package gymlife.model;

import gymlife.utility.Directions;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDirections {
    @Test
    void testGetDir() {
        assertEquals(Optional.of(Directions.UP), Directions.getDir('w'));
        assertEquals(Optional.of(Directions.UP), Directions.getDir('W'));
        assertEquals(Optional.of(Directions.DOWN), Directions.getDir('s'));
        assertEquals(Optional.of(Directions.DOWN), Directions.getDir('S'));
        assertEquals(Optional.of(Directions.LEFT), Directions.getDir('a'));
        assertEquals(Optional.of(Directions.LEFT), Directions.getDir('A'));
        assertEquals(Optional.of(Directions.RIGHT), Directions.getDir('d'));
        assertEquals(Optional.of(Directions.RIGHT), Directions.getDir('D'));
        assertEquals(Optional.empty(), Directions.getDir('z'));

    }

}

