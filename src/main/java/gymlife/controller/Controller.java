package gymlife.controller;

import gymlife.model.GameMapImpl;
import gymlife.model.MapManagerImpl;
import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;

/**
 * the controller (to change)
 */
public final class Controller {

    private final MapManager mapManager;

    public Controller() {
        this.mapManager = new MapManagerImpl(GameMapImpl.HOUSE_MAP);
    }

    public void goToGym() {
        mapManager.changeMap(GameMapImpl.GYM_MAP);
    }

    public void goToShop() {
        mapManager.changeMap(GameMapImpl.SHOP_MAP);
    }

    public void goToHouse() {
        mapManager.changeMap(GameMapImpl.HOUSE_MAP);
    }

    public GameMap getCurrentMap() {
        return mapManager.getCurrentMap();
    }

}
