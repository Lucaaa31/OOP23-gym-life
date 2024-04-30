package gymlife.controller;

import gymlife.model.GameMapImpl;
import gymlife.model.MapManagerImpl;
import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;

/**
 * the controller (to change).
 */
public final class Controller {

    private final MapManager mapManager;

    /**
     * Constructor for the controller.
     */
    public Controller() {
        this.mapManager = new MapManagerImpl(GameMapImpl.HOUSE_MAP);
    }

    /**
     * Method to directly change the current map to Gym.
     */
    public void goToGym() {
        mapManager.changeMap(GameMapImpl.GYM_MAP);
    }

    /**
     * Method to directly change the current map to Shop.
     */
    public void goToShop() {
        mapManager.changeMap(GameMapImpl.SHOP_MAP);
    }

    /**
     * Method to directly change the current map to House.
     */
    public void goToHouse() {
        mapManager.changeMap(GameMapImpl.HOUSE_MAP);
    }

    /**
     * Method to return the current map, taken from the MapManager.
     * @return Returns the current {@code GameMap}.
     */
    public GameMap getCurrentMap() {
        return mapManager.getCurrentMap();
    }

}
