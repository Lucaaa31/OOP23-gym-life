package gymlife.model;

import gymlife.utility.Direction;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDirection {
    @Test
    void testGetDir() {
        assertEquals(Optional.of(Direction.UP), Direction.getDir('w'));
        assertEquals(Optional.of(Direction.UP), Direction.getDir('W'));
        assertEquals(Optional.of(Direction.DOWN), Direction.getDir('s'));
        assertEquals(Optional.of(Direction.DOWN), Direction.getDir('S'));
        assertEquals(Optional.of(Direction.LEFT), Direction.getDir('a'));
        assertEquals(Optional.of(Direction.LEFT), Direction.getDir('A'));
        assertEquals(Optional.of(Direction.RIGHT), Direction.getDir('d'));
        assertEquals(Optional.of(Direction.RIGHT), Direction.getDir('D'));
        assertEquals(Optional.empty(), Direction.getDir('z'));

    }

}

