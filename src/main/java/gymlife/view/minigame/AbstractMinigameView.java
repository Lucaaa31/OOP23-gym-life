package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.minigame.MinigameState;
import gymlife.view.DimensionGetter;
import gymlife.view.api.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public abstract class AbstractMinigameView extends GamePanel {
    private final JLabel characterLabel = new JLabel();
    private ImageIcon characterImage;
    private final transient DimensionGetter dimensionGetter;
    private final JLabel timerView = new JLabel();
    private final JLabel backgroundLabel = new JLabel();
    private final ImageIcon backgroundImage;
    private final JLayeredPane layeredPane = new JLayeredPane();
    private final transient Controller controller;
    private final JProgressBar progressBar = new JProgressBar();


    private final Map<String, Color> colorMap = Map.of(
            "backgroundColorGreen", new Color(29, 110, 12),
            "foregroundColorGreen", new Color(72, 253, 0),
            "backgroundColorRed", new Color(142, 25, 25),
            "foregroundColorRed", new Color(255, 0, 0),
            "backgroundColorYellow", new Color(118, 119, 34),
            "foregroundColorYellow", new Color(248, 255, 0)
    );


    public void setColorBackground(final String colorName) {
        progressBar.setBackground(colorMap.get(colorName));
    }

    public void setColorForeground(final String colorName) {
        progressBar.setForeground(colorMap.get(colorName));
    }

    public void setValueProgressBar(final int value) {
        progressBar.setValue(value);
    }

    public int getValueProgressBar() {
        return progressBar.getValue();
    }

    public int getWidhtProgressBar() {
        return progressBar.getWidth();
    }

    public boolean limits(final int x, final int y, final JButton buttonMinigame) {
        return characterLabel.getBounds()
                .intersects(new Rectangle(x, y, buttonMinigame.getWidth(), buttonMinigame.getHeight()));
    }


    public AbstractMinigameView(final Controller controller, final DimensionGetter dimensionGetter, final String minigameType) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;

        FontLoader.loadFont();

        this.setLayout(new BorderLayout());
        this.setSize(dimensionGetter.getScenarioDimension());

        backgroundImage = new ImageIcon("src/main/resources/images/Minigame/background.png");
        characterImage = new ImageIcon("src/main/resources/images/Minigame/" + minigameType + "/sprite_0.png");

        this.add(progressBar, BorderLayout.EAST);

        progressBar.setOrientation(JProgressBar.VERTICAL);
        progressBar.setPreferredSize(new Dimension(100, dimensionGetter.getScenarioDimension().height));

        progressBar.setBackground(colorMap.get("backgroundColorGreen"));
        progressBar.setForeground(colorMap.get("foregroundColorGreen"));


        this.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(null);

        layeredPane.setBounds(0, 0, dimensionGetter
                        .getScenarioDimension().width - progressBar.getWidth(),
                dimensionGetter.getScenarioDimension().height);

        timerView.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        timerView.setForeground(Color.YELLOW);

        timerView.setBounds(10, 10,
                dimensionGetter.getTimerMinigameDimension().width,
                dimensionGetter.getTimerMinigameDimension().height);

        backgroundImage.setImage(backgroundImage.getImage()
                .getScaledInstance(dimensionGetter.getScenarioDimension().width - progressBar.getWidth(),
                        dimensionGetter.getScenarioDimension().height,
                        Image.SCALE_SMOOTH));


        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0, dimensionGetter
                        .getScenarioDimension().width - progressBar.getWidth(),
                dimensionGetter.getScenarioDimension().height);

        characterLabel.setSize(dimensionGetter.getCharacterMinigameDimension());

        characterLabel.setLocation(dimensionGetter.getCharacterMinigamePos().width,
                dimensionGetter.getCharacterMinigamePos().height);

        characterLabel.setIcon(characterImage);
        characterLabel.setVisible(true);

        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(characterLabel, Integer.valueOf(1));
        layeredPane.add(timerView, Integer.valueOf(1));


    }

    public void doAnimation(final String path) {
        for (int state = 3; state >= 0; state--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            characterImage = getCharacterImage("images/Minigame/" + path + "/sprite_" + state + ".png");
            characterLabel.setIcon(characterImage);
        }
    }

    public void addLayeredPanel(final JButton buttonMinigame) {
        layeredPane.add(buttonMinigame, Integer.valueOf(1));
    }

    public void timerView() {
        new Thread(() -> {
            int i = 0;
            while (controller.getMinigameState() != MinigameState.ENDED_WON
                    && controller.getMinigameState() != MinigameState.ENDED_LOST) {
                i++;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {
                }
                timerView.setText(String.format("%02d:%02d", i / 1000, (i % 1000) / 10));
            }
        }).start();
    }

    public ImageIcon getCharacterImage(final String path) {
        return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource(path))
                .getImage()
                .getScaledInstance(dimensionGetter.getCharacterMinigameDimension().width,
                        dimensionGetter.getCharacterMinigameDimension().height,
                        Image.SCALE_DEFAULT));
    }

    public void progressBarHandler() {
        final int lowProgress = 33;
        final int highProgress = 66;
        if (progressBar.getValue() < lowProgress) {
            progressBar.setBackground(colorMap.get("backgroundColorGreen"));
            progressBar.setForeground(colorMap.get("foregroundColorGreen"));

        } else if (progressBar.getValue() >= lowProgress && progressBar.getValue() < highProgress) {
            progressBar.setBackground(colorMap.get("backgroundColorYellow"));
            progressBar.setForeground(colorMap.get("foregroundColorYellow"));
        } else if (progressBar.getValue() >= highProgress) {
            progressBar.setBackground(colorMap.get("backgroundColorRed"));
            progressBar.setForeground(colorMap.get("foregroundColorRed"));
        }

    }

    public void resizeComponents() {

    }

    public String getPanelName() {
        return "AbstractMinigameView";
    }

}
