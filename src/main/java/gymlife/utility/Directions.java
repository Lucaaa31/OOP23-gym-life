package gymlife.utility;

import java.util.Optional;

/**
 * Keylistener responsible for managing movement inputs.
 */
public enum Directions {
    /**
     * Direction UP.
     */
    UP('w', new Position(0, -10)),

    /**
     * Direction RIGHT.
     */
    RIGHT('d', new Position(+10, 0)),

    /**
     * Direction Left.
     */
    LEFT('a', new Position(-10, 0)),

    /**
     * Direction DOWN.
     */
    DOWN('s', new Position(0, +10));

    private final char key;
    private final Position pos;

    /**
     * Private constructor.
     *
     * @param key the character key associated with the direction
     * @param pos the position change associated with the direction
     */
    Directions(final char key, final Position pos) {
        this.key = key;
        this.pos = pos;
    }

    /**
     * Returns an Optional containing the Directions enum value associated with the given character key.
     * Used to convert the key pressed into a direction, into the 
     *
     * @param key the character key
     * @return an Optional containing the Directions enum value, or an empty Optional if no match is found
     */
    public static Optional<Directions> getDir(final char key) {
        for (final Directions elem : Directions.values()) {
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
    public Position getPos() {
        return pos;
    }
}
