package gymlife.utilities;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import gymlife.model.CellImpl;
import gymlife.model.PairImpl;
import gymlife.model.api.Cell;
import gymlife.model.api.Pair;

public class MapLoader {
    public static Map<Pair<Integer, Integer>, Cell> load(final String fileName) {
        final Map<Pair<Integer, Integer>, Cell> tempMap = new HashMap<>();
        final InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
        if (in != null) {
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            try {
                for (int i = 0; i < MapConstants.MAP_Y_DIM; i++) {
                    String[] buffer = br.readLine().split(" ");
                    for (int j = 0; j < MapConstants.MAP_X_DIM; j++) {
                        int cellId = Integer.parseInt(buffer[j]);
                        tempMap.put(new PairImpl<>(j, i), CellImpl.getCellFromId(cellId));
                    }

                }
                return tempMap;
            } catch (IOException e) {
                return Map.of();
            }

        }
        return tempMap;
    }
}
