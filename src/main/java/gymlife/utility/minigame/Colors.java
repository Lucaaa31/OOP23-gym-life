package gymlife.utility.minigame;

import java.awt.Color;

/**
 * The Colors enum represents the colors that can be used in the Squat mini-game.
 */
public enum Colors {
    /**
     * The RED color.
     */
    RED(
            255,
            0,
            0,
            "Red"
    ),
    /**
     * The GREEN color.
     */
    GREEN(
            0,
            255,
            0,
            "Green"
    ),
    /**
     * The BLUE color.
     */
    BLUE(
            0,
            0,
            255,
            "Blue"
    ),
    /**
     * The YELLOW color.
     */
    YELLOW(
            255,
            255,
            0,
            "Yellow"
    ),
    /**
     * The ORANGE color.
     */
    ORANGE(
            255,
            165,
            0,
            "Orange"
    ),
    /**
     * The PURPLE color.
     */
    PURPLE(
            128,
            0,
            128,
            "Purple"
    ),
    /**
     * The PINK color.
     */
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

    /**
     * Constructs a new Colors object with the given red, green, blue, and colorName.
     *
     * @param red       the red value of the color.
     * @param green     the green value of the color.
     * @param blue      the blue value of the color.
     * @param colorName the name of the color.
     */
    Colors(final int red, final int green, final int blue, final String colorName) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.colorName = colorName;
    }

    /**
     * Returns the color.
     *
     * @return the color.
     */
    public Color getColor() {
        return new Color(red, green, blue);
    }

    /**
     * Returns the color name.
     *
     * @return the color name.
     */
    public String getColorName() {
        return colorName;
    }

    /**
     * Returns the color based on the color name.
     *
     * @param colorName the color name.
     * @return the color based on the color name.
     */
    public static Colors getColor(final String colorName) {
        for (final Colors color : Colors.values()) {
            if (color.getColorName().equals(colorName)) {
                return color;
            }
        }
        return null;
    }

    /**
     * Returns the color based on the red, green, and blue values.
     *
     * @param red   the red value.
     * @param green the green value.
     * @param blue  the blue value.
     * @return the color based on the red, green, and blue values.
     */
    public static Colors getColorName(final int red, final int green, final int blue) {
        for (final Colors color : Colors.values()) {
            if (color.getColor().equals(new Color(red, green, blue))) {
                return color;
            }
        }
        return null;
    }


}
