package gymlife.utility;

import java.awt.event.KeyEvent;
import java.util.Optional;

/**
 * Enum responsible for converting key input into directions.
 */
public enum Direction {
    /**
     * Direction UP.
     */
    UP(KeyEvent.VK_UP, new Position(0, -1)),

    /**
     * Direction RIGHT.
     */
    RIGHT(KeyEvent.VK_DOWN, new Position(+1, 0)),

    /**
     * Direction Left.
     */
    LEFT(KeyEvent.VK_LEFT, new Position(-1, 0)),

    /**
     * Direction DOWN.
     */
    DOWN(KeyEvent.VK_RIGHT, new Position(0, +1));

    private final int key;
    private final Position offSet;

    /**
     * Private constructor.
     *
     * @param key the character key associated with the direction
     * @param offSet the position change associated with the direction
     */
    Direction(final int key, final Position offSet) {
        this.key = key;
        this.offSet = offSet;
    }

    /**
     * Returns an Optional containing the Directions enum value associated with the given character key.
     * Used to convert the key pressed into a direction.
     *
     * @param key the character key
     * @return an Optional containing the Directions enum value, or an empty Optional if no match is found
     */
    public static Optional<Direction> getDir(final int key) {
        for (final Direction elem : Direction.values()) {
            if (elem.key == key) {
                return Optional.of(elem);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the position change associated with the direction.
     *
     * @return the position change
     */
    public Position getOffSet() {
        return offSet;
    }
}
