package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.utility.ScenariosType;
import gymlife.view.api.GamePanel;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.Serial;

/**
 * Class that extends GamePanel representing the scenario of when the character sleeps.
 */
public class SleepView extends GamePanel {

    @Serial
    private static final long serialVersionUID = -6516148483272026913L;
    /**
     * Number of milliseconds to wait before switching to the next image.
     */
    private static final int MILLIS = 500;
    private final JLabel imageLabel;
    private final transient Controller controller;
    private final transient DimensionGetter dimensionGetter;

    /**
     * Standard constructor for SleepView class.
     * @param controller the inherited controller.
     * @param dimensionGetter the inherited dimensionGetter.
     */
    public SleepView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.imageLabel = new JLabel();
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.setSize(dimensionGetter.getScenarioDimension());
        this.setBackground(Color.BLACK);
        this.add(imageLabel);
        imageLabel.setIcon(loadResizedImage(0));

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                imageLabel.setIcon(loadResizedImage(0));
                startAnimation();
            }
        });
    }

    private void startAnimation() {
        new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                try {
                    imageLabel.setIcon(loadResizedImage(i));
                    Thread.sleep(MILLIS);
                } catch (InterruptedException ignored) {
                }
            }
            controller.changeScenario(ScenariosType.INDOOR_MAP);
            imageLabel.setIcon(loadResizedImage(0));
            this.transferFocus();
        }).start();
    }

    private ImageIcon loadResizedImage(final int state) {
        final String path = "images/sleeping/sprite_" + state + ".png";
        final Image imgToResize = new ImageIcon(ClassLoader.getSystemResource(path)).getImage();
        return new ImageIcon(imgToResize.getScaledInstance(
                dimensionGetter.getScenarioDimension().width,
                dimensionGetter.getScenarioDimension().height,
                Image.SCALE_SMOOTH));
    }

    /**
     * Method to resize the image inside according to the size of its parent component.
     */
    @Override
    public void resizeComponents() {
        imageLabel.setIcon(loadResizedImage(0));
    }

    /**
     * Method to return the canonical name of this view.
     * @return string representing this view's name.
     */
    @Override
    public String getPanelName() {
        return "sleep";
    }
}

