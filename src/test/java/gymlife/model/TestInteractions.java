package gymlife.model;

import gymlife.model.inventory.FoodType;
import gymlife.model.inventory.InventoryImpl;
import gymlife.model.map.api.MapManager;
import gymlife.model.map.GameMapImpl;
import gymlife.model.map.MapManagerImpl;
import gymlife.model.minigame.MinigameManagerImpl;
import gymlife.model.statistics.StatsManagerImpl;
import gymlife.model.statistics.api.StatsManager;
import gymlife.utility.GameDifficulty;
import gymlife.utility.Position;
import gymlife.utility.ScenariosType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestInteractions {

    private ScenariosManager scenariosManager;
    private StatsManager statsManager;
    private InteractionsManager interactionsManager;
    private MapManager mapManager;
    private final MinigameManagerImpl minigameManagerImpl = new MinigameManagerImpl();
    private InventoryImpl inventory;

    @BeforeEach
    void init() {
        scenariosManager = new ScenariosManager();
        scenariosManager.updateScenarios(ScenariosType.INDOOR_MAP);
        statsManager = new StatsManagerImpl(GameDifficulty.EASY);
        inventory = new InventoryImpl();
        interactionsManager = new InteractionsManager(scenariosManager, statsManager, minigameManagerImpl, inventory);
        mapManager = new MapManagerImpl(GameMapImpl.HOUSE_MAP);
    }

    @Test
    void testBed() {
        final int expectedDays = 39;
        final Position pos = new Position(1, 2);
        interactOnCell(pos);
        assertEquals(expectedDays, statsManager.getDays().getCount());
    }

    @Test
    void testExit() {
        final Position pos = new Position(7, 2);
        interactOnCell(pos);
        assertEquals(ScenariosType.MAIN_MAP, scenariosManager.getActualScenariosType());
    }

    @Test
    void testBuyFood() {
        mapManager.changeMap(GameMapImpl.SHOP_MAP);
        final Position pos = new Position(1, 1);
        interactOnCell(pos);
        assertEquals(ScenariosType.BUY_FOOD, scenariosManager.getActualScenariosType());
        scenariosManager.updateScenarios(ScenariosType.INDOOR_MAP);
        final Position pos2 = new Position(3, 1);
        interactOnCell(pos2);
        assertEquals(ScenariosType.BUY_FOOD, scenariosManager.getActualScenariosType());
        scenariosManager.updateScenarios(ScenariosType.INDOOR_MAP);
        final Position pos3 = new Position(5, 1);
        interactOnCell(pos3);
        assertEquals(ScenariosType.BUY_FOOD, scenariosManager.getActualScenariosType());
        scenariosManager.updateScenarios(ScenariosType.INDOOR_MAP);

    }

    @Test
    void testEatFood() {
        inventory.addFood(FoodType.HAMBURGER);
        inventory.addFood(FoodType.BROCCOLI);
        inventory.addFood(FoodType.MEAT);
        Position pos = new Position(0, 4);
        interactOnCell(pos);
        assertEquals(0, inventory.getFoodCount().get(FoodType.HAMBURGER));
        pos = new Position(1, 4);
        interactOnCell(pos);
        assertEquals(0, inventory.getFoodCount().get(FoodType.MEAT));
        pos = new Position(2, 4);
        interactOnCell(pos);
        assertEquals(0, inventory.getFoodCount().get(FoodType.BROCCOLI));
    }

    @Test
    void testMinigame() {
        mapManager.changeMap(GameMapImpl.GYM_MAP);
        final Position pos = new Position(1, 1);
        interactOnCell(pos);
        assertEquals(ScenariosType.MINIGAME_GYM, scenariosManager.getActualScenariosType());
        scenariosManager.updateScenarios(ScenariosType.INDOOR_MAP);
        final Position pos2 = new Position(6, 1);
        interactOnCell(pos2);
        assertEquals(ScenariosType.MINIGAME_GYM, scenariosManager.getActualScenariosType());
        scenariosManager.updateScenarios(ScenariosType.INDOOR_MAP);
        final Position pos3 = new Position(5, 4);
        interactOnCell(pos3);
        assertEquals(ScenariosType.MINIGAME_GYM, scenariosManager.getActualScenariosType());
        scenariosManager.updateScenarios(ScenariosType.INDOOR_MAP);

        mapManager.changeMap(GameMapImpl.SHOP_MAP);
        final Position pos4 = new Position(7, 1);
        interactOnCell(pos4);
        assertEquals(ScenariosType.MINIGAME_BANK, scenariosManager.getActualScenariosType());
    }

    @Test
    void testNoInteraction() {
        final Position pos = new Position(4, 4);
        assertEquals(Optional.empty(), mapManager.getCurrentMap().getCellAtCoord(pos).getInteraction());
    }

    private void interactOnCell(final Position pos) {
        mapManager.getCurrentMap()
                .getCellAtCoord(pos)
                .getInteraction()
                .ifPresent((e) -> e.interact(interactionsManager));
    }
}
