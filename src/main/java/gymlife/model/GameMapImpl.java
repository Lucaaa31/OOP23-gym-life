package gymlife.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import gymlife.model.api.Cell;
import gymlife.model.api.GameMap;
import gymlife.model.api.Pair;

public class GameMapImpl implements GameMap{

    private int id;
    private String name;
    private int dimY;
    private int dimX;
    private Map<Pair<Integer, Integer>, Cell> map;

    public GameMapImpl(final int id, final String name, final int dimX, final int dimY, final String fileName){
        this.id = id;
        this.name = name;
        this.dimX = dimX;
        this.dimY = dimY;
        Map<Pair<Integer, Integer>, Cell> tempMap = new HashMap<>();
        int counterX = 0;
        int counterY = 0;
        try {
            File myObj = new File("OOP23-gym-life/src/main/resources/"+fileName);
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
            this.map = tempMap;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    @Override
    public boolean isCellCollidable(Pair<Integer, Integer> coord) {
        return this.getCellAtCoord(coord).getCollision();
    }

    @Override
    public Cell getCellAtCoord(Pair<Integer, Integer> coord) {
        return map.get(coord);
    }

    @Override
    public boolean checkBorders(Pair<Integer, Integer> coord) {
        return coord.getX()>=0 && coord.getX()<this.dimX && coord.getY()>=0 && coord.getY()<this.dimY;
    }

    @Override
    public int getDimX() {
        return this.dimX;
    }

    @Override
    public int getDimY() {
        return this.dimY;
    }

    @Override
    public Map<Pair<Integer, Integer>, Cell> getMap() {
        return this.map;
    }


    @Override
    public int getId() {
        return this.id;
    }


    @Override
    public String getName() {
        return this.name;
    }
    
}
