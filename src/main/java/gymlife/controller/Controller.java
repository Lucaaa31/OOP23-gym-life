package gymlife.controller;
import gymlife.model.GameMapImpl;
import gymlife.model.api.GameMap;
import gymlife.utilities.MapLoader;

public class Controller {
    
    private GameMap gameMap;

    public Controller(final String name){
        this.gameMap = new GameMapImpl(0, "house", 8, 6, MapLoader.load("houseMap.txt"));
    }

    public GameMap getMap(){
        return gameMap;
    }

}
