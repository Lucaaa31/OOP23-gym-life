package gymlife.view;


import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.io.Serial;

import gymlife.controller.api.Controller;
import gymlife.controller.ControllerImpl;
import gymlife.utility.FontLoader;
import gymlife.utility.GameDifficulty;
import gymlife.utility.ScenariosType;

/**
 * The MainView class represents the main view of the application.
 * It extends the JFrame class and provides methods to start and display the main view.
 */
public class MainView extends JFrame {
    @Serial
    private static final long serialVersionUID = -3544425205075144844L;
    private final transient DimensionGetter dimensionGetter = new DimensionGetter();
    private transient Controller controller;
    private final JPanel mainPanel = new JPanel();
    private final JPanel scenariosContainer = new JPanel();
    private final JPanel sideContainer = new JPanel();
    private final JPanel newGame = new JPanel();
    //private JPanel fastTravelView;
    private JPanel statsView;
    private JPanel gameMapView;
    private GameDifficulty difficulty;

    /**
     * Constructor for the MainView class.
     * Sets the size of the frame, requests focus, sets the location relative to null and makes it visible.
     */
    public MainView() {
        this.setSize(dimensionGetter.getFrameDimension());
        this.newGame();
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Starts the main view of the application.
     * Sets the size, layout, and default close operation of the frame.
     * Sets the size, layout, and visibility of the character view panel.
     * Adds the character view panel to the main frame and makes it visible.
     */
    public void start() {
        final CardLayout scenarioLayout = new CardLayout();
        final CardLayout sideLayout = new CardLayout();
        scenariosContainer.setPreferredSize(dimensionGetter.getScenarioDimension());
        sideContainer.setPreferredSize(dimensionGetter.getSideDimension());
        mainPanel.setPreferredSize(dimensionGetter.getFrameDimension());
        this.setPreferredSize(dimensionGetter.getFrameDimension());
        this.controller = new ControllerImpl(difficulty);
        this.statsView = new SideStatsView(controller, dimensionGetter);
        this.gameMapView = new GameMapView(controller, dimensionGetter);

        mainPanel.setPreferredSize(dimensionGetter.getFrameDimension());
        mainPanel.setLayout(new BorderLayout());

        scenariosContainer.setPreferredSize(dimensionGetter.getScenarioDimension());
        scenariosContainer.setLayout(scenarioLayout);
        scenariosContainer.setBackground(Color.RED);

        sideContainer.setPreferredSize(dimensionGetter.getSideDimension());
        sideContainer.setLayout(sideLayout);
        sideContainer.setBackground(Color.BLUE);

        mainPanel.add(scenariosContainer, BorderLayout.WEST);
        mainPanel.add(sideContainer, BorderLayout.CENTER);

        // Creazione dell'azione per il tasto '+'
        final Action increaseSizeAction = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dimensionGetter.incScreenDimension();
                resizeComponents();
                ((GameMapView) gameMapView).resizeComponents();
                ((SideStatsView) statsView).resizeStats();
            }
        };

        // Creazione dell'azione per il tasto '-'
        final Action decreaseSizeAction = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dimensionGetter.decScreenDimension();
                resizeComponents();
                ((GameMapView) gameMapView).resizeComponents();
                ((SideStatsView) statsView).resizeStats();
            }
        };


        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke('+'), "increase size");
        mainPanel.getActionMap().put("increase size", increaseSizeAction);

        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke('-'), "decrease size");
        mainPanel.getActionMap().put("decrease size", decreaseSizeAction);

        sideContainer.add(statsView, BorderLayout.CENTER);
        scenariosContainer.add(gameMapView, ScenariosType.INDOOR_MAP.toString());
        scenarioLayout.show(scenariosContainer, ScenariosType.INDOOR_MAP.toString());

        scenarioLayout.show(scenariosContainer, ScenariosType.INDOOR_MAP.toString());

        scenariosContainer.setDoubleBuffered(true);
        sideContainer.setDoubleBuffered(true);
        statsView.setVisible(true);
        sideContainer.setVisible(true);
        this.add(mainPanel);
        this.setResizable(false);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.pack();
        this.setLocationRelativeTo(null); // Posiziona il frame al centro dello schermo
        this.setVisible(true);
        gameMapView.requestFocusInWindow();
    }

    /**
     * Resizes the components proportionally.
     * Calculates the new proportional dimensions for the panels.
     * Updates the layout.
     */
    private void resizeComponents() {
        // Calcola le nuove dimensioni proporzionali per i pannelli
        scenariosContainer.setPreferredSize(dimensionGetter.getScenarioDimension());
        sideContainer.setPreferredSize(dimensionGetter.getSideDimension());
        mainPanel.setPreferredSize(dimensionGetter.getFrameDimension());
        this.setPreferredSize(dimensionGetter.getFrameDimension());

        // Aggiorna il layout
        sideContainer.revalidate();
        sideContainer.repaint();
        scenariosContainer.revalidate();
        scenariosContainer.repaint();

        mainPanel.repaint();
        this.pack();
        this.repaint();
        this.setLocationRelativeTo(null); // Posiziona il frame al centro dello schermo
    }

    /**
     * Creates a new game.
     * Sets the layout of the new game panel, loads the font, creates a border, creates labels and buttons,
     * and adds them to the new game panel.
     * Sets the background color, text, opacity, alignment, font, and color of the labels.
     * Adds the new game panel to the frame.
     */
    private void newGame() {
        FontLoader.loadFont();
        final Color backgroundColor = Color.DARK_GRAY;
        final int borderSize = 15;
        final int fontDescriptionSize = 55;

        final JButton buttonEasy = this.buildButton(Color.GREEN, GameDifficulty.EASY);
        final JButton buttonMedium = this.buildButton(Color.YELLOW, GameDifficulty.MEDIUM);
        final JButton buttonHard = this.buildButton(Color.RED, GameDifficulty.HARD);
        final JPanel labelEasy = buildPanel(buttonEasy);
        final JPanel labelMedium = buildPanel(buttonMedium);
        final JPanel labelHard = buildPanel(buttonHard);

        final JLabel textLabel = new JLabel();
        final JLabel descriptionText = new JLabel();
        final JLabel buttonLabel = new JLabel();

        buttonLabel.setLayout(new GridLayout(1, 3));
        buttonLabel.setSize(new Dimension(dimensionGetter.getButtonLabelDimension().width * 3,
                dimensionGetter.getButtonLabelDimension().height));

        buttonLabel.add(labelEasy);
        buttonLabel.add(labelMedium);
        buttonLabel.add(labelHard);

        textLabel.setBackground(backgroundColor);
        textLabel.setText("New Game");
        textLabel.setOpaque(true);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setFont(FontLoader.getCustomFont(100));
        textLabel.setForeground(Color.WHITE);

        descriptionText.setBackground(backgroundColor);
        descriptionText.setText("Select the difficulty level of the game");
        descriptionText.setOpaque(true);
        descriptionText.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionText.setVerticalAlignment(SwingConstants.NORTH);
        descriptionText.setFont(FontLoader.getCustomFont(fontDescriptionSize));
        descriptionText.setForeground(Color.WHITE);

        newGame.setLayout(new GridLayout(3, 1));
        newGame.add(textLabel);
        newGame.add(descriptionText);
        newGame.add(buttonLabel);
        newGame.setBorder(new LineBorder(Color.WHITE, borderSize));
        this.add(newGame);
    }
    /**
     * Builds a button with the specified color and difficulty.
     * Sets the background color, border, foreground color, opacity, content area filled, font,
     * and action listener of the button.
     * @return JButton
     * @param color the color of the button
     * @param difficulty the difficulty of the game
     */
    private JButton buildButton(final Color color, final GameDifficulty difficulty) {
        final int borderSize = 5;
        final int fontSize = 60;
        final JButton button = new JButton(difficulty.toString());
        button.setBackground(color);
        button.setBorder(new LineBorder(Color.WHITE, borderSize));
        button.setForeground(Color.BLACK);
        button.setBorderPainted(true);
        button.setPreferredSize(dimensionGetter.getButtonStartDimension());
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setFont(FontLoader.getCustomFont(fontSize));
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.addActionListener(e -> {
            this.difficulty = difficulty;
            newGame.setVisible(false);
            this.start();
        });
        return button;
    }
    /**
     * Builds a panel with the specified button.
     * Sets the opacity, background color, preferred size, size, and adds the button to the panel.
     * @return JPanel
     * @param button the button to be added to the panel
     */
    private JPanel buildPanel(final JButton button) {
        final JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);
        panel.setPreferredSize(dimensionGetter.getButtonLabelDimension());
        panel.setSize(dimensionGetter.getButtonLabelDimension());
        panel.add(button);
        return panel;
    }
}

