package gymlife.model;

import gymlife.utility.Direction;
import org.junit.jupiter.api.Test;


import java.awt.event.KeyEvent;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDirection {
    @Test
    void testGetDir() {
        assertEquals(Optional.of(Direction.UP), Direction.getDir(KeyEvent.VK_W));
        assertEquals(Optional.of(Direction.RIGHT), Direction.getDir(KeyEvent.VK_D));
        assertEquals(Optional.of(Direction.LEFT), Direction.getDir(KeyEvent.VK_A));
        assertEquals(Optional.of(Direction.DOWN), Direction.getDir(KeyEvent.VK_S));
        assertEquals(Optional.empty(), Direction.getDir('z'));
    }

}

