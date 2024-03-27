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
    public static Map<Pair<Integer, Integer>, Cell> load(final String fileName){
        Map<Pair<Integer, Integer>, Cell> tempMap = new HashMap<>();
        int counterX = 0;
        int counterY = 0;
        try {
            File myObj = new File("OOP23-gym-life/src/main/resources/" + fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(" ");
                for (String string : arr) {
                    Pair<Integer, Integer> coord = new PairImpl<>(counterX, counterY);
                    Cell cell = CellImpl.getCellfromId(Integer.parseInt(string));
                    tempMap.put(coord, cell);
                    counterX++;
                }
                counterX = 0;
                counterY++;
            }
            myReader.close();
            return tempMap;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }
}
