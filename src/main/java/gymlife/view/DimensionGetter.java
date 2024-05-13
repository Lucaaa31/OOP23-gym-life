package gymlife.view;

import gymlife.utility.MapConstants;
import gymlife.utility.Position;

import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class DimensionGetter {
    private static final double RIDIMENTION_PROPORTION = 1.2;
    private static final int INCREMENT = 5;
    private final Dimension actualFrameDimension = new Dimension(
            (int) (Toolkit.getDefaultToolkit().getScreenSize().width / RIDIMENTION_PROPORTION) ,
            (int) (Toolkit.getDefaultToolkit().getScreenSize().height / RIDIMENTION_PROPORTION));

    public DimensionGetter() {
    }

    public Dimension getFrameDimension() {
        return actualFrameDimension;
    }

    public Dimension getScenarioDimension() {
        return new Dimension((int) (actualFrameDimension.width * 0.75), actualFrameDimension.height);
    }

    public Dimension getSideDimension() {
        return new Dimension((int) (actualFrameDimension.width * 0.25), actualFrameDimension.height);
    }

    public Dimension getCellDimension() {
        return new Dimension((int) (actualFrameDimension.width * 0.75) / MapConstants.MAP_X_DIM,
                actualFrameDimension.height / MapConstants.MAP_Y_DIM);
    }

    public void incScreenDimension() {
        actualFrameDimension.setSize(actualFrameDimension.width + INCREMENT * 16,
                actualFrameDimension.height + INCREMENT * 9);
    }

    public void decScreenDimension() {
        actualFrameDimension.setSize(actualFrameDimension.width - INCREMENT * 16,
                actualFrameDimension.height - INCREMENT * 9);
    }
}
