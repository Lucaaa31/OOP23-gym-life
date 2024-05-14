package gymlife.view.bankgame;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import gymlife.controller.api.Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serial;

/**
 * This class groups all the panels and shows them on screen.
 */
public final class BankGameView extends JLayeredPane {
    @Serial
    private static final long serialVersionUID = -3972452455820596601L;

    private final TextLabelView numberLabel;
    private final TextLabelView moneyLabel;
    private boolean started;
    private final JTextField boxMoney;
    private final Font myFont = new Font("PLAIN", Font.PLAIN, 20);
    private float moneyMultiplied;
    private float moneyStart;

    /**
     * This method sets the dimensions of the plane image and the sky image, add a
     * button,
     * shows the multiplier and the money multiplied,
     * moreover it sets the images' layering.
     * 
     * @param controller
     */
    public BankGameView(final Controller controller) {
        numberLabel = new MultiplierGameView();
        moneyLabel = new MoneyGameView();
        final ImageLabelView planeLayer = new ImageLabelView("gymlife/airplane/airplane.png");
        final ImageLabelView skyLayer = new ImageLabelView("gymlife/sky/sky.png");
        final JButton button = new JButton();
        final JButton restarButton = new JButton();
        boxMoney = new JTextField();

        this.add(skyLayer, JLayeredPane.DEFAULT_LAYER);
        this.add(planeLayer, JLayeredPane.PALETTE_LAYER);
        this.add(numberLabel, JLayeredPane.MODAL_LAYER);
        this.add(button, JLayeredPane.MODAL_LAYER);
        this.add(restarButton, JLayeredPane.MODAL_LAYER);
        this.add(boxMoney, JLayeredPane.MODAL_LAYER);
        this.add(moneyLabel, JLayeredPane.MODAL_LAYER);

        button.setText("Play");
        button.setBackground(Color.GREEN);
        restarButton.setText("Restart");
        button.setEnabled(false);
        restarButton.setEnabled(false);

        boxMoney.setFont(myFont);

        /*
         * boxMoney.addKeyListener(new KeyAdapter() {
         * 
         * @Override
         * public void keyTyped(final KeyEvent e) {
         * if (e.getKeyCode() == KeyEvent.VK_DELETE) {
         * e.consume();
         * }
         * }
         * });
         */

        boxMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String temp = boxMoney.getText();
                moneyStart = Float.parseFloat(temp);
                ((MoneyGameView) moneyLabel).updateText(moneyStart);
                moneyLabel.setVisible(true);
                button.setEnabled(true);
                restarButton.setEnabled(true);
            }
        });

        boxMoney.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_ENTER) {
                    e.consume();
                    moneyLabel.setText("Wrong format, only numbers");
                }
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                setLayersNewSize(skyLayer, planeLayer, numberLabel, button, restarButton, moneyLabel, boxMoney);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!started) {
                    startMulti(controller);
                    showsMulti(controller);
                    started = true;
                    numberLabel.setVisible(true);
                    boxMoney.setEditable(false);
                    restarButton.setEnabled(false);
                } else {
                    controller.controllerStopMultiplier();
                    restarButton.setEnabled(true);
                    started = false;
                    button.setEnabled(false);
                }
            }

            private void startMulti(final Controller controller) {
                new Thread(() -> {
                    System.out.println("starmulti e' partito");
                    controller.startMultiplier(moneyStart);
                    System.out.println("starmulti e' terminato");
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
            }
        });
        this.setVisible(true);
    }

    /**
     * Displays the multiplier and the money multiplied on the screen.
     *
     * @param controller The controller object responsible for managing the
     *                   multiplier.
     */
    public void showsMulti(final Controller controller) {
        new Thread(() -> {
            float multiplier = 0;
            System.out.println("ciao luca sono partito!!!");
            while (controller.getMultiplier() != controller.getTreshold() && started == true) {
                if (multiplier == 0) {
                    multiplier = 1;
                }
                multiplier = controller.getMultiplier();
                moneyMultiplied = controller.controllerGetMoney();
                ((MultiplierGameView) numberLabel).updateText(multiplier, moneyMultiplied);
            }
            System.out.println("ciao luca mi sono terminato!!!");
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
    }
}
