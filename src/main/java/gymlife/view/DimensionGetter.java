package gymlife.view;

import gymlife.utility.MapConstants;

import javax.swing.plaf.DimensionUIResource;
import java.awt.Dimension;
import java.awt.Toolkit;

public class DimensionGetter {
    private static final double RIDIMENTION_PRPORTION = 1.2;
    private final Dimension actualFrameDimension = new Dimension(
            (int) (Toolkit.getDefaultToolkit().getScreenSize().width / RIDIMENTION_PRPORTION) ,
            (int) (Toolkit.getDefaultToolkit().getScreenSize().height / RIDIMENTION_PRPORTION));

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
        return new Dimension(actualFrameDimension.width / MapConstants.MAP_X_DIM, actualFrameDimension.height / MapConstants.MAP_Y_DIM);
    }

    public void incScreenDimension() {
        actualFrameDimension.setSize(actualFrameDimension.width + 10, actualFrameDimension.height + 10);
    }

    public void decScreenDimension() {
        actualFrameDimension.setSize(actualFrameDimension.width - 10, actualFrameDimension.height +
                -10);
    }

}
