package gymlife.utility;

import java.awt.*;

/**
 * Constants class contains various constants used in the application.
 */
public final class Constants {
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * The movement constant used for character movement.
     */
    public static final int MOVEMENT = 1;
    /**
     * The height of the panel.
     */
    public static final int HEIGHT = 900;
    /**
     * The width of the panel.
     */
    public static final int SCENARIO_WIDTH = 1600;
    /**
     * The height of the panel.
     */
    public static final int SIDE_WIDTH = 400;
    /**
     * The width of the frame.
     */

    public static final int FRAME_WIDTH = screenSize.width;
    /**
     * The width of the character.
     */
    public static final int CHARACTER_WIDTH = 60;
    /**
     * The height of the character.
     */
    public static final int CHARACTER_HEIGHT = 70;
    /**
     * The starting position of the character.
     */
    public static final Position CHARACTER_START_POS = new Position(6, 2);
    private Constants() {
    }
}
