package gymlife.utility;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import gymlife.model.CellImpl;
import gymlife.model.api.Cell;

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
    public static Map<Position, Cell> load(final String fileName) {
        final Map<Position, Cell> tempMap = new HashMap<>();
        final BufferedReader br = getReaderFromFile(fileName);
        if (br != null) {
            for (int i = 0; i < MapConstants.MAP_Y_DIM; i++) {
                try {
                    final String buffer = br.readLine();
                    if (buffer != null) {
                        final String[] stringArray = buffer.split(" ");
                        for (int j = 0; j < MapConstants.MAP_X_DIM; j++) {
                            final int cellId = Integer.parseInt(stringArray[j]);
                            tempMap.put(new Position(j, i), CellImpl.getCellFromId(cellId));
                        }
                    }
                } catch (IOException e) {
                    return Map.of();
                }
            }
            try {
                br.close();
            } catch (IOException e) {
                return Map.of();
            }

        }
        return tempMap;
    }

    private static BufferedReader getReaderFromFile(final String fileName) {
        final InputStream in = ClassLoader.getSystemResourceAsStream(MapConstants.MAP_FILES_PATH + fileName);
        if (in != null) {
            return new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        }
        return null;
    }
}
