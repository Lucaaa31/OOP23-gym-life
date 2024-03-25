package gymlife.utility;

import java.util.Optional;
/**
*  Keylistener responsable for managing movement inputs.
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
    *  Private constructor.
    * @param key
    * @param pos
    */
    Directions(final char key, final Position pos) {
        this.key = key;
        this.pos = pos;
    }
    /**
    *  From char key return Optional<Directions>.
    * @param key
    * @return Optional<Directions>
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
    *  From dir return Optional<Position>.
    * @param dir
    * @return Optional<Position>
    */
    public static Optional<Position> getOffset(final Directions dir) {
        for (final Directions elem : Directions.values()) {
            if (elem.equals(dir)) {
                return Optional.of(elem.pos);
            }
        }
        return Optional.empty();
    }
}
