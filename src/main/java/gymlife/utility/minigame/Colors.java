package gymlife.utility.minigame;

import java.awt.*;

public enum Colors {
    RED(
            255,
            0,
            0,
            "Red"
    ),
    GREEN(
            0,
            255,
            0,
            "Green"
    ),
    BLUE(
            0,
            0,
            255,
            "Blue"
    ),
    YELLOW(
            255,
            255,
            0,
            "Yellow"
    ),
    ORANGE(
            255,
            165,
            0,
            "Orange"
    ),
    PURPLE(
            128,
            0,
            128,
            "Purple"
    ),
    PINK(
            255,
            192,
            203,
            "Pink"
    );

    private final int red;
    private final int green;
    private final int blue;
    private final String colorName;

    Colors(final int red, final int green, final int blue, final String colorName) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.colorName = colorName;
    }

    public Color getColor() {
        return new Color(red, green, blue);
    }

    public String getColorName() {
        return colorName;
    }

    public static Colors getColor(final String colorName) {
        for (Colors color : Colors.values()) {
            if (color.getColorName().equals(colorName)) {
                return color;
            }
        }
        return null;
    }

    public static Colors getColorName(final int red, final int green, final int blue) {
        for (Colors color : Colors.values()) {
            if (color.getColor().equals(new Color(red, green, blue))) {
                return color;
            }
        }
        return null;

    }


}
