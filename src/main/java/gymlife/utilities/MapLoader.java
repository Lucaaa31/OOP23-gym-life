package gymlife.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import gymlife.model.CellImpl;
import gymlife.model.PairImpl;
import gymlife.model.api.Cell;
import gymlife.model.api.Pair;

/**
 * MapLoader is a static class used to load a map of pairs and Cells from file.
 */
public final class MapLoader {
    private MapLoader() {

    }

    /**
     * Static method to load a map from file.
     * @param fileName The name of the file to load.
     * @return Returns a map of Pairs and Cells.
     */
    public static Map<Pair<Integer, Integer>, Cell> load(final String fileName) {
        final Map<Pair<Integer, Integer>, Cell> tempMap = new HashMap<>();
        final InputStream in = ClassLoader.getSystemResourceAsStream(MapConstants.MAP_FILES_PATH + fileName);
        if (in != null) {
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            try {
                for (int i = 0; i < MapConstants.MAP_Y_DIM; i++) {
                    final String[] buffer = br.readLine().split(" ");
                    for (int j = 0; j < MapConstants.MAP_X_DIM; j++) {
                        final int cellId = Integer.parseInt(buffer[j]);
                        tempMap.put(new PairImpl<>(j, i), CellImpl.getCellFromId(cellId));
                    }

                }
                br.close();
                return tempMap;
            } catch (IOException e) {
                return Map.of();
            }

        }
        return tempMap;
    }
}
