package gymlife.model.map;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import gymlife.model.InteractionsManager;
import gymlife.model.inventory.FoodType;
import gymlife.model.map.api.Cell;
import gymlife.model.api.GameInteraction;
import gymlife.utility.minigame.MinigameType;

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
    HOUSE_FLOOR(0, false),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED01(1, true),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED02(2, true),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED03(3, true),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED04(4, true),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED05(5, true),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED06(6, true),
    /**
     * The cell used to interact with bed, no collision and interactable.
     */
    HOUSE_BED_INTERACT(7, false, InteractionsManager::daysInteraction),
    /**
     * The kitchen is divided in 3 cells, all of them with collisions and not interactable.
     */
    HOUSE_KITCHEN01(8, true),
    /**
     * The kitchen is divided in 3 cells, all of them with collisions and not interactable.
     */
    HOUSE_KITCHEN02(9, true),
    /**
     * The kitchen is divided in 3 cells, all of them with collisions and not interactable.
     */
    HOUSE_KITCHEN03(10, true),
    /**
     * The cell with a table, with collisions and not interactable.
     */
    HOUSE_TABLE(11, true),
    /**
     * The cell with a couple of weights, no collisions and not interactable.
     */
    HOUSE_WEIGHTS(12, false),
    /**
     * The cell that lets players go to another {@code GameMap}, no collisions and interactable.
     */
    HOUSE_EXIT(13, false, InteractionsManager::exitInteraction),
    /**
     * The cell used to eat some meat from the inventory, no collisions and interactable.
     */
    HOUSE_MEAT_INTERACT(14, false),
    /**
     * The cell used to eat a hamburger from the inventory, no collisions and interactable.
     */
    HOUSE_HAMBURGER_INTERACT(48, false),
    /**
     * The cell used to eat some broccoli from the inventory, no collisions and interactable.
     */
    HOUSE_BROCCOLI_INTERACT(49, false),
    /**
     * The cell that contains a part of the house couch, with collisions and not interactable.
     */
    HOUSE_COUCH01(37, true),
    /**
     * The cell that contains a part of the house couch, with collisions and not interactable.
     */
    HOUSE_COUCH02(38, true),
    /**
     * The cell that contains the tv, with collisions and not interactable.
     */
    HOUSE_TV(39, true),
    /**
     * The base floor of the shop {@code GameMap}, no collisions and not interactable.
     */
    SHOP_FLOOR(15, false),
    /**
     * The cell that lets players go to another {@code GameMap}, no collisions and interactable.
     */
    SHOP_EXIT(16, false, InteractionsManager::exitInteraction),
    /**
     * The cell with a non interactable fridge, with collisions and not interactable.
     */
    SHOP_FRIDGE(17, true),
    /**
     * The cell with a hamburger fridge, with collisions and not interactable.
     */
    SHOP_HAMBURGER(18, true),
    /**
     * The cell with a broccoli fridge, with collisions and not interactable.
     */
    SHOP_BROCCOLI(19, true),
    /**
     * The cell with a meat fridge, with collisions and not interactable.
     */
    SHOP_MEAT(20, true),
    /**
     * The cell that allows players to purchase hamburger, no collisions and interactable.
     */
    SHOP_HAMBURGER_INTERACT(21, false, e -> e.buyFoodInteraction(FoodType.HAMBURGER)),
    /**
     * The cell that allows players to purchase broccoli, no collisions and interactable.
     */
    SHOP_BROCCOLI_INTERACT(22, false, e -> e.buyFoodInteraction(FoodType.BROCCOLI)),
    /**
     * The cell that allows players to purchase meat, no collisions and interactable.
     */
    SHOP_MEAT_INTERACT(23, false, e -> e.buyFoodInteraction(FoodType.MEAT)),
    /**
     * The cell that allows players to get money, no collisions and interactable.
     */
    SHOP_MONEY(24, false, InteractionsManager::bankInteraction),
    /**
     * The cell with an ATM, with collisions and not interactable.
     */
    SHOP_ATM(25, true),
    /**
     * A cell with products in a shelf, with collisions and not interactable.
     */
    SHOP_SHELF01(40, true),
    /**
     * A cell with products in a shelf, with collisions and not interactable.
     */
    SHOP_SHELF02(41, true),
    /**
     * A cell with products in a shelf, with collisions and not interactable.
     */
    SHOP_SHELF03(42, true),
    /**
     * A cell with a box of groceries, with collisions and not interactable.
     */
    SHOP_GROCERIES01(43, true),
    /**
     * A cell with a box of groceries, with collisions and not interactable.
     */
    SHOP_GROCERIES02(44, true),
    /**
     * A cell with a box of groceries, with collisions and not interactable.
     */
    SHOP_GROCERIES03(45, true),
    /**
     * A cell with a box of groceries, with collisions and not interactable.
     */
    SHOP_GROCERIES04(46, true),
    /**
     * The cell with the shop checkout, with collisions and not interactable.
     */
    SHOP_CHECKOUT(47, true),
    /**
     * The base floor of the gym {@code GameMap}, no collisions and not interactable.
     */
    GYM_FLOOR(26, false),
    /**
     * The cell that lets players go to another {@code GameMap}, no collisions and interactable.
     */
    GYM_EXIT(27, false, InteractionsManager::exitInteraction),
    /**
     * The cell with a regular bench, with collisions and not interactable.
     */
    GYM_BENCH(28, true),
    /**
     * The cell with the bench press, with collisions not interactable.
     */
    GYM_CHEST(29, true),
    /**
     * The cell that allows players to train chest, no collisions and interactable.
     */
    GYM_CHEST_INTERACT(30, false, e -> e.minigameInteraction(MinigameType.BENCH_PRESS)),
    /**
     * The cell with the lat machine, with collisions not interactable.
     */
    GYM_LAT(31, true),
    /**
     * The cell that allows players to train back, no collisions and interactable.
     */
    GYM_BACK_INTERACT(32, false, e -> e.minigameInteraction(MinigameType.LAT_MACHINE)),
    /**
     * The cell with the squat rack, with collisions not interactable.
     */
    GYM_SQUAT(33, true),
    /**
     * The cell that allows players to train legs, no collisions and interactable.
     */
    GYM_LEGS_INTERACT(34, false),
    /**
     * The cell with a couple of weights, with collisions and not interactable.
     */
    GYM_WEIGHTS01(35, true),
    /**
     * The cell with a couple of weights, with collisions and not interactable.
     */
    GYM_WEIGHTS02(36, true),
    /**
     * The placeholder cell, it is inserted in a map if the {@code MapLoader} does not recognize the cell id in a file.
     */
    PLACEHOLDER(-1, false);

    private final int id;
    private final boolean collision;
    private final transient GameInteraction interaction;

    /**
     * Standard constructor with 3 parameters.
     * @param id Integer representing the id of the cell.
     * @param collision Boolean to indicate the presence or lack of collisions.
     * @param interaction Lambda for the interaction.
     */
    CellImpl(final int id, final boolean collision, final GameInteraction interaction) {
        this.id = id;
        this.collision = collision;
        this.interaction = interaction;
    }

    /**
     * Constructor with 2 parameters instead of 3, used for cells with no interactions, since it is initialised as null.
     * @param id Integer representing the id of the cell.
     * @param collision Boolean to indicate the presence or lack of collisions.
     */
    CellImpl(final int id, final boolean collision) {
        this(id, collision, null);
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
        return this.toString().toLowerCase(new Locale("en"));
    }

    /**
     * Method to get the interaction of the cell.
     * @return An optional that can be either empty or contains a lambda function.
     */
    @Override
    public Optional<GameInteraction> getInteraction() {
        if (this.interaction == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.interaction);
        }
    }

}
