package gymlife.view.bankgame;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.ScenariosType;
import gymlife.utility.DimensionGetter;
import gymlife.view.api.GamePanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serial;
import java.util.List;


/**
 * This class groups all the panels and shows them on screen.
 */
public final class BankGameView extends GamePanel {
    @Serial
    private static final long serialVersionUID = -3972452455820596601L;

    private static final long THREAD_W = 8;
    private static final double START_MULTIPLIER_VALUES = 0.9;
    private final TextLabelView numberLabel;
    private final TextLabelView moneyLabel;
    private boolean started;
    private final JTextField boxMoney;
    private float moneyMultiplied;
    private float moneyStart;
    private boolean flagAnimation;
    private final transient PlaneAnimationView planeAnimation;
    private final JLayeredPane mainPanel;
    private final ImageLabelView planeLayer;
    private final ImageLabelView skyLayer;
    private final JButton startButton;
    private final JButton restartButton;
    private final JButton infoGameButton;
    private final transient DimensionGetter dimensionGetter;
    private static final double MULTIPLIER_VALUE = 0.9;
    private static final int START_BUTTON_RED = 30;
    private static final int START_BUTTON_GREEN = 130;
    private static final int RESTART_BUTTON_RED = 150;
    private static final int NUMBER_LABEL_RED = 20;

    /**
     * This method sets the dimensions of the plane image and the sky image, add a
     * button,
     * shows the multiplier and the money multiplied,
     * moreover it sets the images' layering.
     *
     * @param controller      the controller associated with the bank game view.
     * @param dimensionGetter an object for getting dimensions.
     */
    public BankGameView(final Controller controller, final DimensionGetter dimensionGetter) {
        final float buttonTextSIze = dimensionGetter.getBigFontSize() / 3;
        this.setPreferredSize(dimensionGetter.getScenarioDimension());
        this.setLayout(new BorderLayout());
        numberLabel = new MultiplierGameView();
        moneyLabel = new MoneyGameView();
        mainPanel = new JLayeredPane();
        planeLayer = new ImageLabelView("images/airplane/airplane.png");
        skyLayer = new ImageLabelView("images/sky/sky.jpg");
        startButton = new JButton();
        restartButton = new JButton();
        infoGameButton = new JButton();
        boxMoney = new JTextField();
        planeAnimation = new PlaneAnimationView();
        final Border darkBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        this.dimensionGetter = dimensionGetter;


        mainPanel.add(skyLayer, JLayeredPane.DEFAULT_LAYER);
        mainPanel.add(planeLayer, JLayeredPane.MODAL_LAYER);
        mainPanel.add(numberLabel, JLayeredPane.MODAL_LAYER);
        mainPanel.add(startButton, JLayeredPane.MODAL_LAYER);
        mainPanel.add(restartButton, JLayeredPane.MODAL_LAYER);
        mainPanel.add(boxMoney, JLayeredPane.MODAL_LAYER);
        mainPanel.add(moneyLabel, JLayeredPane.MODAL_LAYER);
        mainPanel.add(infoGameButton, JLayeredPane.MODAL_LAYER);


        planeLayer.setVisible(false);
        startButton.setText("PLAY");
        restartButton.setText("RESTART");
        infoGameButton.setText("INFO");
        startButton.setBackground(new Color(START_BUTTON_RED, START_BUTTON_GREEN, 0));
        restartButton.setBackground(new Color(RESTART_BUTTON_RED, 0, 0));
        restartButton.setForeground(Color.black);
        startButton.setForeground(Color.black);
        infoGameButton.setForeground(Color.darkGray);
        startButton.setBorder(darkBorder);
        restartButton.setBorder(darkBorder);
        infoGameButton.setBorder(darkBorder);
        startButton.setEnabled(false);
        restartButton.setOpaque(true);
        startButton.setOpaque(true);
        startButton.setFont(FontLoader.getCustomFont(buttonTextSIze));
        restartButton.setFont(FontLoader.getCustomFont(buttonTextSIze));
        boxMoney.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));

        infoGameButton.addActionListener(e -> {
            final List<Float> value = controller.returnList();
            int i = 0;
            final StringBuilder values = new StringBuilder();
            for (final float f : value) {
                i++;
                values.append(String.format("Bet %d thresholds was: %f %n", i, f));
            }
            JOptionPane.showMessageDialog(this, values.toString());
        });

        boxMoney.addActionListener(e -> {
            numberLabel.setForeground(Color.black);
            moneyLabel.setForeground(Color.black);
            moneyLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
            final String temp = boxMoney.getText();
            try {
                moneyStart = Integer.parseInt(temp);
                if (moneyStart <= controller.returnMoney() && moneyStart != 0) {
                    ((MoneyGameView) moneyLabel).updateText(controller.returnMoney());
                    moneyLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
                    moneyLabel.setVisible(true);
                    restartButton.setEnabled(true);
                    ((MultiplierGameView) numberLabel).updateText(START_MULTIPLIER_VALUES, moneyStart);
                    numberLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
                    if (!flagAnimation) {
                        planeLayer.setVisible(true);
                        planeAnimation.startPlaneAnimation(mainPanel, planeLayer, startButton);
                        flagAnimation = true;
                    }
                } else {
                    moneyLabel.setText("Are you broke man?");
                }
            } catch (NumberFormatException ex) {
                moneyLabel.setText("Enter a valid number.");
            }
        });


        boxMoney.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (Character.toLowerCase(c) == 'q') {
                    goAway(controller);
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!started) {
                    startMulti(controller);
                    showsMulti(controller);
                    startButton.setText("STOP");
                    numberLabel.setVisible(true);
                    boxMoney.setEditable(false);
                    restartButton.setEnabled(false);
                    planeAnimation.planeUpDownAnimation(planeLayer);
                    controller.changeMoney(-(int) moneyStart);
                } else {
                    started = false;
                    controller.controllerStopMultiplier();
                    restartButton.setEnabled(true);
                    startButton.setEnabled(false);
                    planeAnimation.planeExitAnimation(mainPanel, planeLayer);
                    controller.changeMoney(Math.round(moneyMultiplied));
                    if (controller.getMultiplier() < 1) {
                        numberLabel.setForeground(Color.red);
                    } else {
                        numberLabel.setForeground(new Color(NUMBER_LABEL_RED, 100, 0));
                    }
                }
            }

            private void startMulti(final Controller controller) {
                new Thread(() -> {
                    controller.startMultiplier(moneyStart);
                    if (started) {
                        startButton.setEnabled(false);
                        numberLabel.setForeground(Color.red);
                        planeAnimation.planeExitAnimation(mainPanel, planeLayer);
                        restartButton.setEnabled(true);
                        started = false;
                    }
                }).start();
            }
        });

        restartButton.addActionListener(e -> {
            boxMoney.setEditable(true);
            startButton.setEnabled(false);
            planeLayer.setVisible(false);
            flagAnimation = false;
            startButton.setText("PLAY");
            boxMoney.setText("");
            numberLabel.setForeground(Color.black);
            ((MoneyGameView) moneyLabel).updateText(controller.returnMoney());
            moneyLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
            controller.newThreshold();
            ((MultiplierGameView) numberLabel).updateText(MULTIPLIER_VALUE, 0);
        });
        this.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Displays the multiplier and the money multiplied on the screen.
     *
     * @param controller The controller object responsible for managing the
     *                   multiplier.
     */
    public void showsMulti(final Controller controller) {
        started = true;
        controller.getSync2().signal();
        new Thread(() -> {
            double multiplier;
            while (controller.getMultiplier() != controller.getThreshold() && started) {
                try {
                    controller.getSync2().waitForSignal();
                    Thread.sleep(THREAD_W);
                } catch (InterruptedException ignored) {
                }
                multiplier = controller.getMultiplier();
                moneyMultiplied = controller.controllerGetMoney();
                ((MultiplierGameView) numberLabel).updateText(multiplier, moneyMultiplied);
                numberLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
                controller.getSync1().signal();
            }
        }).start();
    }

    /**
     * Starts a new thread to update the multiplier value by calling the
     * startMultiplier method
     * with the current money to play value.
     *
     * @param skyLabel      The sky image label.
     * @param planeLabel    The airplane image label.
     * @param numberLabel   The label displaying the multiplier and money
     *                      multiplied.
     * @param button        The button used to start or stop the game.
     * @param restartButton The button used to restart the game.
     * @param moneyLabel    The label displaying the money.
     * @param boxMoney      The text field for entering the money value.
     * @param infoGame      The button use to know all the threshold of any bet made.
     */
    private void setLayersNewSize(final ImageLabelView skyLabel, final ImageLabelView planeLabel,
                                  final TextLabelView numberLabel, final JButton button, final JButton restartButton,
                                  final TextLabelView moneyLabel, final JTextField boxMoney, final JButton infoGame) {

        final Dimension newSize = this.getSize();
        final int buttonWidth = newSize.width / 45;
        final int buttonDimension = newSize.height / 8;
        final int restartButtonWidth = newSize.width / 8;
        final int restartButtonDimension = newSize.height / 8;
        final int planeLabelWidth = newSize.width / 4;
        final int planeLabelHeight = newSize.height / 2;
        final int numberLabelWidth = newSize.width / 2;
        final int numberLabelDimension = newSize.height;
        final int boxMoneyWidth = newSize.width / 40;
        final int boxMoneyDimensionWidth = newSize.width / 10;
        final int boxMoneyDimensionHeight = newSize.height / 17;
        final int moneyLabelWidth = newSize.width / 40;
        final int moneyLabelDimension = newSize.height;
        final int infoGameHeight = newSize.height / 2;
        final int infoGameDimensionWidth = newSize.width / 20;
        final int infoGameDimensionHeight = newSize.height / 15;

        skyLabel.setBounds(0, 0, newSize.width, newSize.height);
        skyLabel.reload();
        button.setBounds(buttonWidth, newSize.height / 3, buttonDimension, buttonDimension);
        restartButton.setBounds(restartButtonWidth, newSize.height / 3, restartButtonDimension, restartButtonDimension);
        planeLabel.setBounds(planeLabelWidth, newSize.height / 4, planeLabelWidth, planeLabelHeight);
        planeLabel.reload();
        numberLabel.setBounds(numberLabelWidth, newSize.height / 3, numberLabelDimension, numberLabelDimension);
        infoGame.setBounds(buttonWidth, infoGameHeight / 2, infoGameDimensionWidth, infoGameDimensionHeight);
        boxMoney.setBounds(boxMoneyWidth, newSize.height / 2, boxMoneyDimensionWidth, boxMoneyDimensionHeight);
        moneyLabel.setBounds(moneyLabelWidth, newSize.height / 3, moneyLabelDimension, moneyLabelDimension);
    }

    @Override
    public void resizeComponents() {
        setLayersNewSize(skyLayer, planeLayer, numberLabel, startButton, restartButton, moneyLabel, boxMoney, infoGameButton);
    }

    @Override
    public String getPanelName() {
        return "planeGame";
    }

    private void goAway(final Controller controller) {
        super.grabFocus();
        controller.changeScenario(ScenariosType.INDOOR_MAP);
        transferFocus();
    }
}
