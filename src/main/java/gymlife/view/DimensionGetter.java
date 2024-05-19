package gymlife.view;

import gymlife.utility.MapConstants;

import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * This class is used to get the dimensions for different components of the application.
 * It calculates the dimensions based on the screen size and some predefined constants.
 */
public class DimensionGetter {
    // Proportion to reduce the screen size to get the frame dimension
    private static final double RIDIMENTION_PROPORTION = 1.1;
    // Increment value used to increase or decrease the screen dimension
    private static final int INCREMENT = 5;
    // The actual frame dimension calculated based on the screen size and RIDIMENTION_PROPORTION
    private final Dimension actualFrameDimension = new Dimension(
            (int) (Toolkit.getDefaultToolkit().getScreenSize().width / RIDIMENTION_PROPORTION),
            (int) (Toolkit.getDefaultToolkit().getScreenSize().height / RIDIMENTION_PROPORTION));
    private static final double SCENARIO_PROPORTION = 0.75;
    private static final double SIDE_PROPORTION = 0.25;
    private static final double FAST_TRAVEL_PROPORTION = 0.875;
    private static final double ENCOUNTER_IMAGE_PROPORTION = 0.625;
    private static final int BIG_FONT_SIZE = 32;
    private static final int SMALL_FONT_SIZE = 35;
    private static final int FRAME_HEIGHT = 9;
    private static final int FRAME_WIDTH = 16;
    private static final int SQUARE_STATS = 10;

    /**
     * Returns the actual frame dimension.
     * @return the actual frame dimension
     */
    public Dimension getFrameDimension() {
        return new Dimension(actualFrameDimension.width, actualFrameDimension.height);
    }

    /**
     * Returns the scenario dimension which is 75% of the frame width and equal to the frame height.
     * @return the scenario dimension
     */
    public Dimension getScenarioDimension() {
        return new Dimension((int) (actualFrameDimension.width * SCENARIO_PROPORTION), actualFrameDimension.height);
    }

    /**
     * Returns the side dimension which is 25% of the frame width and equal to the frame height.
     * @return the side dimension
     */
    public Dimension getSideDimension() {
        return new Dimension((int) (actualFrameDimension.width * SIDE_PROPORTION), actualFrameDimension.height);
    }

    /**
     * Returns the cell dimension which is calculated based on the scenario width and the map dimensions.
     * @return the cell dimension
     */
    public Dimension getCellDimension() {
        return new Dimension((int) (actualFrameDimension.width * SCENARIO_PROPORTION) / MapConstants.MAP_X_DIM,
                actualFrameDimension.height / MapConstants.MAP_Y_DIM);
    }

    /**
     * Returns the cell dimension which is calculated based on the scenario width and the map dimensions.
     * @return the cell dimension
     */
    public Dimension getSquareStatsDimension() {
        return new Dimension(actualFrameDimension.height / SQUARE_STATS,
                actualFrameDimension.height / SQUARE_STATS);
    }

    /**
     * Returns the Fast Travel image dimension which is calculated based on width and height of the scenario.
     * @return the fast travel image dimension.
     */
    public Dimension getFastTravelDimension() {
        return new Dimension(getScenarioDimension().width,
                (int) (getScenarioDimension().height * FAST_TRAVEL_PROPORTION));
    }

    /**
     * Returns the Fast Travel buttons dimension which are calculated based on width and height of the scenario.
     * @return the fast travel buttons dimension.
     */
    public Dimension getFastTravelButtonsDimension() {
        return new Dimension(getScenarioDimension().width,
                (int) (getScenarioDimension().height * (1 - FAST_TRAVEL_PROPORTION)));
    }

    public Dimension getEncounterImageDimension() {
        return new Dimension((int) (getScenarioDimension().width * ENCOUNTER_IMAGE_PROPORTION),
                getScenarioDimension().height);
    }


    public Dimension getEncounterButtonDimension() {
        return new Dimension((int)(getScenarioDimension().width * (1 - ENCOUNTER_IMAGE_PROPORTION)),
                getScenarioDimension().height);
    }

    /**
     * Returns the big font size which is the frame width divided by 32.
     * @return the big font size
     */
    public float getBigFontSize() {
        return (float) actualFrameDimension.width / BIG_FONT_SIZE;
    }

    /**
     * Returns the small font size which is the frame width divided by 35.
     * @return the small font size
     */
    public float getSmallFontSize() {
        return (float) actualFrameDimension.width / SMALL_FONT_SIZE;
    }

    /**
     * Increases the screen dimension by a fixed increment.
     */
    public void incScreenDimension() {
        actualFrameDimension.setSize(actualFrameDimension.width + INCREMENT * FRAME_WIDTH,
                actualFrameDimension.height + INCREMENT * FRAME_HEIGHT);
    }

    /**
     * Decreases the screen dimension by a fixed decrement.
     */
    public void decScreenDimension() {
        actualFrameDimension.setSize(actualFrameDimension.width - INCREMENT * FRAME_WIDTH,
                actualFrameDimension.height - INCREMENT * FRAME_HEIGHT);
    }
}
