package gymlife.model;

import java.util.Arrays;
import java.util.Optional;

import gymlife.model.api.Cell;

public enum CellImpl implements Cell {
    HOUSE_FLOOR(0, false, Optional.empty()),
    HOUSE_BED01(1, true, Optional.empty()),
    HOUSE_BED02(2, true, Optional.empty()),
    HOUSE_BED03(3, true, Optional.empty()),
    HOUSE_BED04(4, true, Optional.empty()),
    HOUSE_BED05(5, true, Optional.empty()),
    HOUSE_BED06(6, true, Optional.empty()),
    HOUSE_BED_INTERACT(7, false, Optional.empty()),
    HOUSE_KITCHEN01(8, true, Optional.empty()),
    HOUSE_KITCHEN02(9, true, Optional.empty()),
    HOUSE_KITCHEN03(10, true, Optional.empty()),
    HOUSE_TABLE(11, true, Optional.empty()),
    HOUSE_WEIGHTS(12, false, Optional.empty()),
    HOUSE_EXIT(13, false, Optional.empty()),
    HOUSE_KITCHEN_INTERACT(14, false, Optional.empty()),
    SHOP_FLOOR(15, false, Optional.empty()),
    PLACEHOLDER(-1, false, Optional.empty());

    private int id;
    private boolean collision;
    private Optional<Runnable> interaction;

    private CellImpl(final int id, final boolean collision, final Optional<Runnable> interaction) {
        this.id = id;
        this.collision = collision;
        this.interaction = interaction;
    }

    public static Cell getCellfromId(final int id) {
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

    public Optional<Runnable> getInteraction(){
        return this.interaction;
    }

}
