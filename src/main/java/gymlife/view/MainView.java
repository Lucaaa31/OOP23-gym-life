package gymlife.view;


import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
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
    private Controller controller;
    private final JPanel mainPanel = new JPanel();
    private final JPanel scenariosContainer = new JPanel();
    private final JPanel sideContainer = new JPanel();
    private final JPanel newGame = new JPanel();
    private JPanel fastTravelView;
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
        this.requestFocusInWindow();
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
        this.controller = new ControllerImpl(difficulty);
        this.statsView = new SideStatsView(controller, dimensionGetter);
        this.gameMapView = new GameMapView(controller, dimensionGetter);

        mainPanel.setPreferredSize(dimensionGetter.getFrameDimension());
        mainPanel.setLayout(new BorderLayout());

        scenariosContainer.setPreferredSize(dimensionGetter.getScenarioDimension());
        CardLayout scenarioLayout = new CardLayout();
        scenariosContainer.setLayout(scenarioLayout);
        scenariosContainer.setBackground(Color.RED);

        sideContainer.setPreferredSize(dimensionGetter.getSideDimension());
        CardLayout sideLayout = new CardLayout();
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

        // Creazione dell'azione per il tasto 'e'
        final Action checkScenario = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                checkScenario();
                System.out.println("Checking scenarioooo");
            }
        };

        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke('+'), "increase size");
        mainPanel.getActionMap().put("increase size", increaseSizeAction);

        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke('-'), "decrease size");
        mainPanel.getActionMap().put("decrease size", decreaseSizeAction);

        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke('e'), "check scenario");
        mainPanel.getActionMap().put("check scenario", checkScenario);


        sideContainer.add(statsView, BorderLayout.CENTER);
        statsView.setVisible(true);
        scenariosContainer.add(gameMapView, ScenariosType.INDOOR_MAP.toString());
        scenarioLayout.show(scenariosContainer, ScenariosType.INDOOR_MAP.toString());

        scenarioLayout.show(scenariosContainer, ScenariosType.INDOOR_MAP.toString());

        scenariosContainer.setDoubleBuffered(true);
        sideContainer.setDoubleBuffered(true);
        sideContainer.setVisible(true);
        this.add(mainPanel);
        this.setLocationRelativeTo(null); // Posiziona il frame al centro dello schermo
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
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
        newGame.setLayout(new GridLayout(3, 1));
        FontLoader.loadFont();
        final int borderSize = 15;
        final int fontDescriptionSize = 55;
        Border border = BorderFactory.createLineBorder(Color.BLACK, borderSize);

        JLabel textLabel = new JLabel();
        JLabel descriptiontext = new JLabel();

        JButton buttonEasy = this.buildButton(Color.GREEN, GameDifficulty.EASY);
        JButton buttonMedium = this.buildButton(Color.YELLOW, GameDifficulty.MEDIUM);
        JButton buttonHard = this.buildButton(Color.RED, GameDifficulty.HARD);

        JLabel buttonLabel = new JLabel();
        buttonLabel.setLayout(new GridLayout(1, 3));
        buttonLabel.setSize(new Dimension(dimensionGetter.getButtonLabelDimension().width * 3,
                dimensionGetter.getButtonLabelDimension().height));

        JPanel labelEasy = buildPanel(buttonEasy);
        buttonLabel.add(labelEasy);

        JPanel labelMedium = buildPanel(buttonMedium);
        buttonLabel.add(labelMedium);

        JPanel labelHard = buildPanel(buttonHard);
        buttonLabel.add(labelHard);

        newGame.add(textLabel);
        newGame.add(descriptiontext);
        newGame.add(buttonLabel);
        newGame.setBorder(border);

        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setText("New Game");
        textLabel.setOpaque(true);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setFont(FontLoader.getCustomFont(100));
        textLabel.setForeground(Color.WHITE);

        descriptiontext.setBackground(Color.DARK_GRAY);
        descriptiontext.setText("Select the difficulty level of the game");
        descriptiontext.setOpaque(true);
        descriptiontext.setHorizontalAlignment(SwingConstants.CENTER);
        descriptiontext.setVerticalAlignment(SwingConstants.NORTH);
        descriptiontext.setFont(FontLoader.getCustomFont(fontDescriptionSize));
        descriptiontext.setForeground(Color.WHITE);

        this.add(newGame);
    }
    /**
     * Return the ImageIcona that is in the path.
     * @return ImageIcon
     * @param path the path of the image
     */
    private ImageIcon getIcon(final String path) {
        return new ImageIcon(new ImageIcon(ClassLoader.
                getSystemResource(path))
                .getImage()
                .getScaledInstance(dimensionGetter.getButtonStartDimension().width,
                        dimensionGetter.getButtonStartDimension().height,
                        Image.SCALE_FAST));
    }
    /**
     * Checks the scenario.
     */
    private void checkScenario() {
        System.out.println("Checking scenario");
        ScenariosType actualScenario = controller.getActualScenario();
        System.out.println(actualScenario);
        System.out.println(ScenariosType.INDOOR_MAP.toString());
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
        JButton button = new JButton(difficulty.toString());
        button.setBackground(color);
        button.setBorder(new LineBorder(Color.WHITE, 5, true));
        button.setForeground(Color.BLACK);
        button.setBorderPainted(true);
        button.setPreferredSize(dimensionGetter.getButtonStartDimension());
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setFont(FontLoader.getCustomFont(60));
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
    private JPanel buildPanel (final JButton button){
        final JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);
        panel.setPreferredSize(dimensionGetter.getButtonLabelDimension());
        panel.setSize(dimensionGetter.getButtonLabelDimension());
        panel.add(button);
        return panel;
    }
}

