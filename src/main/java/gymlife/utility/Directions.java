package gymlife.utility;


import java.util.Optional;

import gymlife.utility.Directions;

public enum Directions {
    UP('w', new Position(0, -10)),
    RIGHT('d', new Position(+10, 0)),
    LEFT('a', new Position(-10, 0)),
    DOWN('s', new Position(0, +10));

    private final char key;
    private final Position pos;

    private Directions(char key, Position pos) {
        this.key = key;
        this.pos = pos;
    }

    public static Optional<Directions> GetDir(char key) {
        for (Directions elem : Directions.values()) {
            if (elem.key == key) {
                return Optional.of(elem);
            }
        }
        return Optional.empty();
    }

    public static Optional<Position> GetOffset(Directions dir) {
        for (Directions elem : Directions.values()) {
            if (elem.equals(dir)) {
                return Optional.of(elem.pos);
            }
        }
        return Optional.empty();
    }
}
