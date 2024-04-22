package gymlife.model;

import java.util.Arrays;
import java.util.Optional;

import gymlife.model.api.Cell;

/**
 * CellImpl is an enum that contains all the possible cells present in the game. Each cell has a name, the first part
 * of the name is the map that should contain said cell, the second part of the name is the specific item contained in
 * the cell. A cell is identified by a unique id, useful when reading a map from file. A cell also contains information
 * on whether the player can go on it or not, represented by a boolean {@code collision}. If a cell has collision it
 * wouldn't make sense for them to be interactable since the player can never be on them, so collidable cells are never
 * interactable. The interaction of a cell is represented by a lambda function that executes a {@code GameContainer}
 * method.
 */
public enum CellImpl implements Cell {
    /**
     * The base floor of the house {@code GameMap}, no collisions and not interactable.
     */
    HOUSE_FLOOR(0, false, Optional.empty()),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED01(1, true, Optional.empty()),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED02(2, true, Optional.empty()),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED03(3, true, Optional.empty()),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED04(4, true, Optional.empty()),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED05(5, true, Optional.empty()),
    /**
     * The bed is divided in 6 cells all of them with collisions and not interactable.
     */
    HOUSE_BED06(6, true, Optional.empty()),
    /**
     * The cell used to interact with bed, no collision and interactable.
     */
    HOUSE_BED_INTERACT(7, false, Optional.empty()),
    /**
     * The kitchen is divided in 3 cells, all of them with collisions and not interactable.
     */
    HOUSE_KITCHEN01(8, true, Optional.empty()),
    /**
     * The kitchen is divided in 3 cells, all of them with collisions and not interactable.
     */
    HOUSE_KITCHEN02(9, true, Optional.empty()),
    /**
     * The kitchen is divided in 3 cells, all of them with collisions and not interactable.
     */
    HOUSE_KITCHEN03(10, true, Optional.empty()),
    /**
     * The cell with a table, with collisions and not interactable.
     */
    HOUSE_TABLE(11, true, Optional.empty()),
    /**
     * The cell with a couple of weights, no collisions and not interactable.
     */
    HOUSE_WEIGHTS(12, false, Optional.empty()),
    /**
     * The cell that lets players go to another {@code GameMap}, no collisions and interactable.
     */
    HOUSE_EXIT(13, false, Optional.empty()),
    /**
     * The cell used to interact with the kitchen, no collisions and interactable.
     */
    HOUSE_KITCHEN_INTERACT(14, false, Optional.empty()),
    /**
     * The base floor of the shop {@code GameMap}, no collisions and not interactable.
     */
    SHOP_FLOOR(15, false, Optional.empty()),
    /**
     * The cell that lets players go to another {@code GameMap}, no collisions and interactable.
     */
    SHOP_EXIT(16, false, Optional.empty()),
    /**
     * The cell with a non interactable fridge, with collisions and not interactable.
     */
    SHOP_FRIDGE(17, true, Optional.empty()),
    /**
     * The cell with a hamburger fridge, with collisions and not interactable.
     */
    SHOP_HAMBURGER(18, true, Optional.empty()),
    /**
     * The cell with a broccoli fridge, with collisions and not interactable.
     */
    SHOP_BROCCOLI(19, true, Optional.empty()),
    /**
     * The cell with a meat fridge, with collisions and not interactable.
     */
    SHOP_MEAT(20, true, Optional.empty()),
    /**
     * The cell that allows players to purchase hamburger, no collisions and interactable.
     */
    SHOP_HAMBURGER_INTERACT(21, false, Optional.empty()),
    /**
     * The cell that allows players to purchase broccoli, no collisions and interactable.
     */
    SHOP_BROCCOLI_INTERACT(22, false, Optional.empty()),
    /**
     * The cell that allows players to purchase meat, no collisions and interactable.
     */
    SHOP_MEAT_INTERACT(23, false, Optional.empty()),
    /**
     * The cell that allows players to get money, no collisions and interactable.
     */
    SHOP_MONEY(24, false, Optional.empty()),
    /**
     * The cell with an ATM, with collisions and not interactable.
     */
    SHOP_ATM(25, true, Optional.empty()),
    /**
     * The base floor of the gym {@code GameMap}, no collisions and not interactable.
     */
    GYM_FLOOR(26, false, Optional.empty()),
    /**
     * The cell that lets players go to another {@code GameMap}, no collisions and interactable.
     */
    GYM_EXIT(27, false, Optional.empty()),
    /**
     * the cell with a regular bench, with collisions and not interactable
     */
    GYM_BENCH(28, true, Optional.empty()),
    GYM_CHEST(29, true, Optional.empty()),
    GYM_CHEST_INTERACT(30, false, Optional.empty()),
    GYM_LAT(31, true, Optional.empty()),
    GYM_LAT_INTERACT(32, false, Optional.empty()),
    GYM_SQUAT(33, true, Optional.empty()),
    GYM_SQUAT_INTERACT(34, false, Optional.empty()),
    GYM_WEIGHTS01(35, true, Optional.empty()),
    GYM_WEIGHTS02(36, true, Optional.empty()),
    PLACEHOLDER(-1, false, Optional.empty());

    private final int id;
    private final boolean collision;
    private final Optional<Runnable> interaction;

    CellImpl(final int id, final boolean collision, final Optional<Runnable> interaction) {
        this.id = id;
        this.collision = collision;
        this.interaction = interaction;
    }

    public static Cell getCellFromId(final int id) {
        return Arrays
                .stream(CellImpl.values())
                .filter(cell -> cell.getId() == id)
                .findFirst()
                .orElse(PLACEHOLDER);
    }

    @Override
    public boolean getCollision() {
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
                .toLowerCase();
    }

    public Optional<Runnable> getInteraction() {
        return this.interaction;
    }

}
