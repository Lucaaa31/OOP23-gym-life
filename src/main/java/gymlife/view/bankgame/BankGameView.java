package gymlife.view.bankgame;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import gymlife.controller.api.Controller;
import gymlife.model.statistics.StatsType;
import gymlife.utility.ScenariosType;
import gymlife.view.DimensionGetter;
import gymlife.view.api.GamePanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.io.Serial;

/**
 * This class groups all the panels and shows them on screen.
 */
public final class BankGameView extends GamePanel {
    @Serial
    private static final long serialVersionUID = -3972452455820596601L;

    private static final long THREAD_W = 8;
    private final TextLabelView numberLabel;
    private final TextLabelView moneyLabel;
    private boolean started;
    private final JTextField boxMoney;
    private final Font myFont = new Font("PLAIN", Font.PLAIN, 20);
    private float moneyMultiplied;
    private float moneyStart;
    private boolean flagAnimation;
    private final transient PlaneAnimationView planeAnimation;
    private final JLayeredPane mainPanel;
    private final ImageLabelView planeLayer;
    private final ImageLabelView skyLayer;
    private final JButton button;
    private final JButton restarButton;
    private final JButton quitButton;


    /**
     * This method sets the dimensions of the plane image and the sky image, add a
     * button,
     * shows the multiplier and the money multiplied,
     * moreover it sets the images' layering.
     * 
     * @param controller
     */
    public BankGameView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.setPreferredSize(dimensionGetter.getScenarioDimension());
        this.setLayout(new BorderLayout());
        numberLabel = new MultiplierGameView();
        moneyLabel = new MoneyGameView();
        mainPanel = new JLayeredPane();
        planeLayer = new ImageLabelView("gymlife/airplane/airplane.png");
        skyLayer = new ImageLabelView("gymlife/sky/sky.png");
        button = new JButton();
        restarButton = new JButton();
        boxMoney = new JTextField();
        planeAnimation = new PlaneAnimationView();
        quitButton = new JButton();

        mainPanel.add(skyLayer, JLayeredPane.DEFAULT_LAYER);
        mainPanel.add(planeLayer, JLayeredPane.MODAL_LAYER);
        mainPanel.add(numberLabel, JLayeredPane.MODAL_LAYER);
        mainPanel.add(button, JLayeredPane.MODAL_LAYER);
        mainPanel.add(restarButton, JLayeredPane.MODAL_LAYER);
        mainPanel.add(boxMoney, JLayeredPane.MODAL_LAYER);
        mainPanel.add(moneyLabel, JLayeredPane.MODAL_LAYER);
        mainPanel.add(quitButton, JLayeredPane.MODAL_LAYER);

        planeLayer.setVisible(false);
        button.setText("Play");
        restarButton.setText("Restart");
        button.setBackground(new Color(50, 100, 0));
        button.setEnabled(false);
        restarButton.setEnabled(false);
        boxMoney.setFont(myFont);




        boxMoney.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });

        boxMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                numberLabel.setForeground(Color.black);
                moneyLabel.setForeground(Color.black);
                final String temp = boxMoney.getText();
                moneyStart = Float.parseFloat(temp);
                if (moneyStart <= controller.getStatistics().get(StatsType.MONEY).getCount()) {
                    ((MoneyGameView) moneyLabel).updateText(moneyStart);
                    moneyLabel.setVisible(true);
                    button.setEnabled(true);
                    restarButton.setEnabled(true);
                    if (!flagAnimation) {
                        planeLayer.setVisible(true);
                        planeAnimation.startPlaneAnimation(mainPanel, planeLayer);
                        flagAnimation = true;
                    }
                } else {
                    moneyLabel.setText("You broke, man.");
                }
            }
        });

        boxMoney.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (c == 'q') {
                    goAway(controller);
                }
                else if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_ENTER) {
                    e.consume();
                    moneyLabel.setText("Wrong format, only numbers");
                }
            }
        });



        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!started) {
                    startMulti(controller);
                    showsMulti(controller);
                    numberLabel.setVisible(true);
                    boxMoney.setEditable(false);
                    restarButton.setEnabled(false);
                    planeAnimation.planeUpDownAnimation(planeLayer);
                    controller.changeMoney(controller.getStatistics().get(StatsType.MONEY).getCount() - (int) moneyStart);
                } else {
                    controller.controllerStopMultiplier();
                    restarButton.setEnabled(true);
                    started = false;
                    button.setEnabled(false);
                    planeAnimation.stopUpDownAnimation();
                    planeAnimation.planeExitAnimation(mainPanel, planeLayer);
                    controller.changeMoney(controller.getStatistics().get(StatsType.MONEY).getCount() + (int) moneyMultiplied);
                }
            }

            private void startMulti(final Controller controller) {
                new Thread(() -> {
                    controller.startMultiplier(moneyStart);
                    if (started) {
                        numberLabel.setForeground(Color.red);
                        planeAnimation.planeExitAnimation(mainPanel, planeLayer);
                    }
                    numberLabel.setForeground(new Color(20, 100, 0));
                }).start();
            }
        });

        restarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                numberLabel.setVisible(false);
                controller.randomizeNewThreshold();
                restarButton.setEnabled(false);
                moneyLabel.setVisible(false);
                boxMoney.setEditable(true);
                planeLayer.setVisible(false);
                flagAnimation = false;
            }
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
            float multiplier;
            while (controller.getMultiplier() != controller.getThreshold() && started) {
                try {
                    controller.getSync2().waitForSignal();
                    Thread.sleep(THREAD_W);
                } catch (InterruptedException ignored) {
                }
                multiplier = controller.getMultiplier();
                moneyMultiplied = controller.controllerGetMoney();
                ((MultiplierGameView) numberLabel).updateText(multiplier, moneyMultiplied);
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
     */
    private void setLayersNewSize(final ImageLabelView skyLabel, final ImageLabelView planeLabel,
            final TextLabelView numberLabel, final JButton button, final JButton restartButton,
            final TextLabelView moneyLabel, final JTextField boxMoney) {

        final Dimension newSize = this.getSize();
        final int buttonWidth = newSize.width / 45;
        final int buttonHeight = newSize.height / 11;
        final int restartButtonWidth = newSize.width / 8;
        final int restartButtonHeight = newSize.height / 11;
        final int planeLabelWidth = newSize.width / 4;
        final int planeLabelHeight = newSize.height / 2;
        final int numberLabelWidth = newSize.width / 3;
        final int numberLabelHeight = newSize.height;
        final int boxMoneyWidth = newSize.width / 40;
        final int boxMoneyHeight = newSize.height / 17;
        final int moneyLabelWidth = newSize.width / 40;
        final int moneyLabelHeight = newSize.height;

        skyLabel.setBounds(0, 0, newSize.width, newSize.height);
        skyLabel.reload();
        button.setBounds(buttonWidth, newSize.height / 3, buttonHeight, buttonHeight);
        restartButton.setBounds(restartButtonWidth, newSize.height / 3, restartButtonHeight, restartButtonHeight);
        planeLabel.setBounds(planeLabelWidth, newSize.height / 4, planeLabelWidth, planeLabelHeight);
        planeLabel.reload();
        numberLabel.setBounds(numberLabelWidth, newSize.height / 3, numberLabelHeight, numberLabelHeight);
        numberLabel.reload();
        boxMoney.setBounds(boxMoneyWidth, newSize.height / 2, boxMoneyHeight, boxMoneyHeight);
        moneyLabel.setBounds(moneyLabelWidth, newSize.height / 3, moneyLabelHeight, moneyLabelHeight);
        moneyLabel.reload();
        quitButton.setBounds(100, 80 / 3, 20, 20);
    }

    @Override
    public void resizeComponents() {
        setLayersNewSize(skyLayer, planeLayer, numberLabel, button, restarButton, moneyLabel, boxMoney);
    }

    @Override
    public String getPanelName() {
        return "planeGame";
    }

    private void goAway(final Controller controller) {
        BankGameView.super.grabFocus();
        controller.changeScenario(ScenariosType.INDOOR_MAP);
        this.transferFocus();
    }


}
