package gymlife.model.api;

import java.util.Map;

public interface GameMap {

    boolean isCellCollidable(final Pair<Integer, Integer> coord);

    Cell getCellAtCoord(final Pair<Integer, Integer> coord);

    boolean checkBorders(final Pair<Integer, Integer> coord);

    int getDimX();

    int getDimY();

    Map<Pair<Integer, Integer>, Cell> getMap();
}
