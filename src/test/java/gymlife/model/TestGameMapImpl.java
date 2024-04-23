package gymlife.model;

import gymlife.model.api.GameMap;
import gymlife.model.api.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestGameMapImpl {

    private GameMap shop;
    private GameMap gym;
    private GameMap house;
    private final Pair<Integer, Integer> testCoord1 = new PairImpl<>(0, 0);
    private final Pair<Integer, Integer> testCoord2 = new PairImpl<>(2, 1);
    private final Pair<Integer, Integer> testCoord3 = new PairImpl<>(2, 5);
    private final Pair<Integer, Integer> testCoord4 = new PairImpl<>(-2, 20);
    private final Pair<Integer, Integer> testCoord5 = new PairImpl<>(2, 4);
    private final Pair<Integer, Integer> testCoord6 = new PairImpl<>(4, 3);
    private final Pair<Integer, Integer> testCoord7 = new PairImpl<>(5, 5);
    private final Pair<Integer, Integer> testCoord8 = new PairImpl<>(1, 1);

    @BeforeEach
    void initiate() {
        this.house = GameMapImpl.HOUSE_MAP;
        this.shop = GameMapImpl.SHOP_MAP;
        this.gym = GameMapImpl.GYM_MAP;
    }

    @Test
    void testHouse() {
        assertEquals(CellImpl.HOUSE_BED01, house.getCellAtCoord(testCoord1));
        assertEquals(CellImpl.HOUSE_BED06, house.getCellAtCoord(testCoord2));
        assertEquals(CellImpl.HOUSE_KITCHEN03, house.getCellAtCoord(testCoord3));
        assertFalse(house.checkBorders(testCoord4));
        assertTrue(house.checkBorders(testCoord5));
        assertTrue(house.isCellCollidable(testCoord6));
        assertFalse(house.isCellCollidable(testCoord7));
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
}