package gymlife.model;

import gymlife.model.api.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestGameMapImpl {

    private GameMap shop;
    private GameMap gym;
    private GameMap house;

    @BeforeEach
    void initiate() {
        this.house = GameMapImpl.HOUSE_MAP;
        this.shop = GameMapImpl.SHOP_MAP;
    }

    @Test
    void testHouse() {
        assertEquals(CellImpl.HOUSE_BED01, house.getCellAtCoord(new PairImpl<>(0, 0)));
        assertEquals(CellImpl.HOUSE_BED06, house.getCellAtCoord(new PairImpl<>(2, 1)));
        assertEquals(CellImpl.HOUSE_KITCHEN03, house.getCellAtCoord(new PairImpl<>(2, 5)));
        assertFalse(house.checkBorders(new PairImpl<>(-2, 20)));
        assertTrue(house.checkBorders(new PairImpl<>(2, 4)));
        assertTrue(house.getCellAtCoord(new PairImpl<>(4, 3)).getCollision());
        assertFalse(house.getCellAtCoord(new PairImpl<>(5, 5)).getCollision());
    }

    @Test
    void testShop() {
        assertEquals(CellImpl.SHOP_FRIDGE, shop.getCellAtCoord(new PairImpl<>(0, 0)));
        assertEquals(CellImpl.SHOP_HAMBURGER_INTERACT, shop.getCellAtCoord(new PairImpl<>(1, 1)));
        assertEquals(CellImpl.SHOP_FLOOR, shop.getCellAtCoord(new PairImpl<>(2, 5)));
        assertFalse(shop.checkBorders(new PairImpl<>(-2, 20)));
        assertTrue(shop.checkBorders(new PairImpl<>(2, 4)));
    }
}
