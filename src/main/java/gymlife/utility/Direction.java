package gymlife.utility;

import java.util.Optional;

/**
 * Enum responsible for converting key input into directions
 */
public enum Direction {
    /**
     * Direction UP.
     */
    UP('w', new Position(0, -1)),

    /**
     * Direction RIGHT.
     */
    RIGHT('d', new Position(+1, 0)),

    /**
     * Direction Left.
     */
    LEFT('a', new Position(-1, 0)),

    /**
     * Direction DOWN.
     */
    DOWN('s', new Position(0, +1));

    private final char key;
    private final Position offSet;

    /**
     * Private constructor.
     *
     * @param key the character key associated with the direction
     * @param offSet the position change associated with the direction
     */
    Direction(final char key, final Position offSet) {
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
    public static Optional<Direction> getDir(final char key) {
        for (final Direction elem : Direction.values()) {
            if (elem.key == Character.toLowerCase(key)) {
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
