package gymlife.model;

import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;
import gymlife.utility.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestMaps {

    private GameMap shop;
    private GameMap gym;
    private GameMap house;
    private final Position testCoord1 = new Position(0, 0);
    private final Position testCoord2 = new Position(2, 1);
    private final Position testCoord3 = new Position(2, 5);
    private final Position testCoord4 = new Position(-2, 20);
    private final Position testCoord5 = new Position(2, 4);
    private final Position testCoord6 = new Position(4, 3);
    private final Position testCoord7 = new Position(5, 5);
    private final Position testCoord8 = new Position(1, 1);
    private MapManager manager;

    @BeforeEach
    void init() {
        this.house = GameMapImpl.HOUSE_MAP;
        this.shop = GameMapImpl.SHOP_MAP;
        this.gym = GameMapImpl.GYM_MAP;
        this.manager = new MapManagerImpl(house);
    }

    @Test
    void testHouse() {
        assertEquals(CellImpl.HOUSE_BED01, house.getCellAtCoord(testCoord1));
        assertEquals(CellImpl.HOUSE_BED06, house.getCellAtCoord(testCoord2));
        assertEquals(CellImpl.HOUSE_KITCHEN03, house.getCellAtCoord(testCoord3));
        assertFalse(house.checkBorders(testCoord4));
        assertTrue(house.checkBorders(testCoord5));
        assertTrue(house.isCellCollidable(testCoord6));
        assertTrue(house.isCellCollidable(testCoord7));
    }

    @Test
    void testShop() {
        assertEquals(CellImpl.SHOP_FRIDGE, shop.getCellAtCoord(testCoord1));
        assertEquals(CellImpl.SHOP_HAMBURGER_INTERACT, shop.getCellAtCoord(testCoord8));
        assertEquals(CellImpl.SHOP_FLOOR, shop.getCellAtCoord(testCoord3));
        assertFalse(shop.checkBorders(testCoord4));
        assertTrue(shop.checkBorders(testCoord5));
        assertTrue(shop.isCellCollidable(testCoord1));
        assertFalse(shop.isCellCollidable(testCoord3));
    }

    @Test
    void testGym() {
        assertEquals(CellImpl.GYM_BENCH, gym.getCellAtCoord(testCoord1));
        assertEquals(CellImpl.GYM_FLOOR, gym.getCellAtCoord(testCoord2));
        assertEquals(CellImpl.GYM_WEIGHTS01, gym.getCellAtCoord(testCoord3));
        assertFalse(gym.checkBorders(testCoord4));
        assertTrue(gym.checkBorders(testCoord5));
        assertTrue(gym.isCellCollidable(testCoord7));
        assertFalse(gym.isCellCollidable(testCoord6));
    }

    @Test
    void testManager() {
        assertEquals(manager.getCurrentMap(), GameMapImpl.HOUSE_MAP);
        manager.changeMap(gym);
        assertEquals(manager.getCurrentMap(), GameMapImpl.GYM_MAP);
        manager.changeMap(shop);
        assertEquals(manager.getCurrentMap(), GameMapImpl.SHOP_MAP);
    }
}
