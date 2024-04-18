package gymlife.utilities;

import java.io.File;
import java.io.FileNotFoundException;
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
        int counterX = 0;
        int counterY = 0;
        try {
            final File myObj = new File(MapConstants.MAP_FILES_PATH + fileName);
            final Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                final String data = myReader.nextLine();
                final String[] arr = data.split(" ");
                for (String string : arr) {
                    final Pair<Integer, Integer> coord = new PairImpl<>(counterX, counterY);
                    final Cell cell = CellImpl.getCellfromId(Integer.parseInt(string));
                    tempMap.put(coord, cell);
                    counterX++;
                }
                counterX = 0;
                counterY++;
            }
            myReader.close();
            return tempMap;
        } catch (FileNotFoundException e) {
            return Map.of();
        }
    }
}
