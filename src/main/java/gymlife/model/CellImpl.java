package gymlife.model;

import java.util.Arrays;

import gymlife.model.api.Cell;

public enum CellImpl implements Cell {
    HOUSE_FLOOR(0, "house_floor.png", false),
    HOUSE_BED01(1, "house_bed01.png", true),
    HOUSE_BED02(2, "house_bed02.png", true),
    HOUSE_BED03(3, "house_bed03.png", true),
    HOUSE_BED04(4, "house_bed04.png", true),
    HOUSE_BED05(5, "house_bed05.png", true),
    HOUSE_BED06(6, "house_bed06.png", true),
    PLACEHOLDER(99, "placeholder.png", false);

    private int id;
    private String fileName;
    private boolean collision;

    private CellImpl(final int id, final String fileName, final boolean collision) {
        this.id = id;
        this.fileName = fileName;
        this.collision = collision;
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
    public String getImage() {
        return this.fileName;
    }

}
