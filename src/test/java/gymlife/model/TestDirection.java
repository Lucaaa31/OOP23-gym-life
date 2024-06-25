package gymlife.model;

import gymlife.utility.Direction;
import org.junit.jupiter.api.Test;


import java.awt.event.KeyEvent;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDirection {
    @Test
    void testGetDir() {
        assertEquals(Optional.of(Direction.UP), Direction.getDir(KeyEvent.VK_UP));
        assertEquals(Optional.of(Direction.RIGHT), Direction.getDir(KeyEvent.VK_RIGHT));
        assertEquals(Optional.of(Direction.LEFT), Direction.getDir(KeyEvent.VK_LEFT));
        assertEquals(Optional.of(Direction.DOWN), Direction.getDir(KeyEvent.VK_DOWN));
        assertEquals(Optional.empty(), Direction.getDir('z'));
    }

}

