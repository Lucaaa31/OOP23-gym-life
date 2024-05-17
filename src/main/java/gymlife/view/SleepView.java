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

public class SleepView extends GamePanel {

    private final JLabel imageLabel;
    private final Controller controller;
    private final DimensionGetter dimensionGetter;

    public SleepView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.imageLabel = new JLabel();
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.setBackground(Color.BLACK);
        this.add(imageLabel);
        imageLabel.setIcon(loadResizedImage(0));

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
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
                    Thread.sleep(500);
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

    @Override
    public void resizeComponents() {

    }

    @Override
    public String getPanelName() {
        return "sleep";
    }
}

