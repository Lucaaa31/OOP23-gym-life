package gymlife.model;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;

import gymlife.model.api.Cell;
import gymlife.model.api.GameInteraction;
import gymlife.utility.ScenariosType;

/**
 * CellImpl is an enum that contains all the possible cells present in the game. A cell is identified by a unique id,
 * useful when reading a map from file. A cell also contains information on whether the player can go on it or not,
 * represented by a boolean {@code collision}. The interaction of a cell is represented by a lambda function that
 * executes a {@code GameContainer} method.
 */
public enum CellImpl implements Cell {
    /**
     * The base floor of the house {@code GameMap}, no collisions and not interactable.
     */
    HOUSE_FLOOR(0, false, null),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED01(1, true, null),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED02(2, true, null),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED03(3, true, null),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED04(4, true, null),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED05(5, true, null),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED06(6, true, null),
    /**
     * The cell used to interact with bed, no collision and interactable.
     */
    HOUSE_BED_INTERACT(7, false, null),
    /**
     * The kitchen is divided in 3 cells, all of them with collisions and not interactable.
     */
    HOUSE_KITCHEN01(8, true, null),
    /**
     * The kitchen is divided in 3 cells, all of them with collisions and not interactable.
     */
    HOUSE_KITCHEN02(9, true, null),
    /**
     * The kitchen is divided in 3 cells, all of them with collisions and not interactable.
     */
    HOUSE_KITCHEN03(10, true, null),
    /**
     * The cell with a table, with collisions and not interactable.
     */
    HOUSE_TABLE(11, true, null),
    /**
     * The cell with a couple of weights, no collisions and not interactable.
     */
    HOUSE_WEIGHTS(12, false, null),
    /**
     * The cell that lets players go to another {@code GameMap}, no collisions and interactable.
     */
    HOUSE_EXIT(13, false, (e) -> e.scenarioInteraction(ScenariosType.MAIN_MAP)),
    /**
     * The cell used to interact with the kitchen, no collisions and interactable.
     */
    HOUSE_KITCHEN_INTERACT(14, false, null),
    /**
     * The base floor of the shop {@code GameMap}, no collisions and not interactable.
     */
    SHOP_FLOOR(15, false, null),
    /**
     * The cell that lets players go to another {@code GameMap}, no collisions and interactable.
     */
    SHOP_EXIT(16, false, null),
    /**
     * The cell with a non interactable fridge, with collisions and not interactable.
     */
    SHOP_FRIDGE(17, true, null),
    /**
     * The cell with a hamburger fridge, with collisions and not interactable.
     */
    SHOP_HAMBURGER(18, true, null),
    /**
     * The cell with a broccoli fridge, with collisions and not interactable.
     */
    SHOP_BROCCOLI(19, true, null),
    /**
     * The cell with a meat fridge, with collisions and not interactable.
     */
    SHOP_MEAT(20, true, null),
    /**
     * The cell that allows players to purchase hamburger, no collisions and interactable.
     */
    SHOP_HAMBURGER_INTERACT(21, false, null),
    /**
     * The cell that allows players to purchase broccoli, no collisions and interactable.
     */
    SHOP_BROCCOLI_INTERACT(22, false, null),
    /**
     * The cell that allows players to purchase meat, no collisions and interactable.
     */
    SHOP_MEAT_INTERACT(23, false, null),
    /**
     * The cell that allows players to get money, no collisions and interactable.
     */
    SHOP_MONEY(24, false, null),
    /**
     * The cell with an ATM, with collisions and not interactable.
     */
    SHOP_ATM(25, true, null),
    /**
     * The base floor of the gym {@code GameMap}, no collisions and not interactable.
     */
    GYM_FLOOR(26, false, null),
    /**
     * The cell that lets players go to another {@code GameMap}, no collisions and interactable.
     */
    GYM_EXIT(27, false, null),
    /**
     * The cell with a regular bench, with collisions and not interactable.
     */
    GYM_BENCH(28, true, null),
    /**
     * The cell with the bench press, with collisions not interactable.
     */
    GYM_CHEST(29, true, null),
    /**
     * The cell that allows players to train chest, no collisions and interactable.
     */
    GYM_CHEST_INTERACT(30, false, null),
    /**
     * The cell with the lat machine, with collisions not interactable.
     */
    GYM_LAT(31, true, null),
    /**
     * The cell that allows players to train back, no collisions and interactable.
     */
    GYM_LAT_INTERACT(32, false, null),
    /**
     * The cell with the squat rack, with collisions not interactable.
     */
    GYM_SQUAT(33, true, null),
    /**
     * The cell that allows players to train legs, no collisions and interactable.
     */
    GYM_SQUAT_INTERACT(34, false, null),
    /**
     * The cell with a couple of weights, with collisions and not interactable.
     */
    GYM_WEIGHTS01(35, true, null),
    /**
     * The cell with a couple of weights, with collisions and not interactable.
     */
    GYM_WEIGHTS02(36, true, null),
    /**
     * The placeholder cell, it is inserted in a map if the {@code MapLoader} does not recognize the cell id in a file.
     */
    PLACEHOLDER(-1, false, null);

    private final int id;
    private final boolean collision;
    private final GameInteraction interaction;

    CellImpl(final int id, final boolean collision, final GameInteraction interaction) {
        this.id = id;
        this.collision = collision;
        this.interaction = interaction;
    }

    /**
     * Static method that returns the right {@code Cell} given an id.
     * @param id An integer that represents the Cell.
     * @return Returns Cell with matching ID or a placeholder Cell if given id is invalid.
     */
    public static Cell getCellFromId(final int id) {
        return Arrays
                .stream(CellImpl.values())
                .filter(cell -> cell.getId() == id)
                .findFirst()
                .orElse(PLACEHOLDER);
    }

    @Override
    public boolean isCollision() {
        return this.collision;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.getClass()
                .getName()
                .toLowerCase(new Locale("en"));
    }

    /**
     * Method to get the interaction of the cell.
     * @return An optional that can be either empty or contains a lambda function.
     */
    public Optional<GameInteraction> getInteraction() {
        if (this.interaction == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.interaction);
        }
    }

}
