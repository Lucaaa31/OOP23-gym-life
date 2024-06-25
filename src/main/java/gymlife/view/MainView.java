package gymlife.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.io.Serial;
import java.util.Map;

import gymlife.controller.api.Controller;
import gymlife.controller.ControllerImpl;
import gymlife.view.bankgame.GameOverView;
import gymlife.view.bankgame.WinView;
import gymlife.utility.DimensionGetter;
import gymlife.utility.FontLoader;
import gymlife.model.statistics.GameDifficulty;
import gymlife.view.choice.BuyFoodPanel;
import gymlife.view.choice.EncounterView;
import gymlife.view.map.FastTravelView;
import gymlife.view.map.GameMapView;
import gymlife.view.map.SleepView;
import gymlife.view.minigame.DifficultyMenu;
import gymlife.view.minigame.MinigameSwitchView;
import gymlife.utility.ScenariosType;
import gymlife.view.api.GamePanel;
import gymlife.view.bankgame.BankGameView;
import gymlife.view.statistics.SideStatsView;

/**
 * The MainView class represents the main view of the application.
 * It extends the JFrame class and provides methods to start and display the main view.
 */
public class MainView extends JFrame {
    @Serial
    private static final long serialVersionUID = -3544425205075144844L;
    private final JPanel mainPanel = new JPanel();
    private final transient DimensionGetter dimensionGetter = new DimensionGetter();
    private final JPanel scenariosContainer = new JPanel();
    private final JPanel sideContainer = new JPanel();
    private transient Controller controller;
    private GamePanel statsView;
    private GameDifficulty difficulty;
    private JPanel newGame;


    /**
     * Constructor for the MainView class.
     * Sets the size of the frame, requests focus, sets the location relative to null and makes it visible.
     */
    public MainView() {
        FontLoader.loadFont();
        // Creazione dell'azione per il tasto 'esc'
        final Action closeGameAction = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                closeGame();
            }
        };
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE), "close game");
        mainPanel.getActionMap().put("close game", closeGameAction);

        this.difficulty = null;
        this.setSize(dimensionGetter.getFrameDimension());
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setVisible(true);
    }


    /**
     * Starts the main view of the application.
     * Sets the size, layout, and default close operation of the frame.
     * Sets the size, layout, and visibility of the character view panel.
     * Adds the character view panel to the main frame and makes it visible.
     */
    private void start() {
        final CardLayout sideLayout = new CardLayout();
        scenariosContainer.setPreferredSize(dimensionGetter.getScenarioDimension());
        sideContainer.setPreferredSize(dimensionGetter.getSideDimension());
        mainPanel.setPreferredSize(dimensionGetter.getFrameDimension());
        this.setPreferredSize(dimensionGetter.getFrameDimension());
        this.controller = new ControllerImpl(difficulty);
        this.statsView = new SideStatsView(controller, dimensionGetter);
        final GamePanel gameMapView = new GameMapView(controller, dimensionGetter);
        final GamePanel fastTravelView = new FastTravelView(controller, dimensionGetter);
        final GamePanel sleepView = new SleepView(controller, dimensionGetter);
        final GamePanel encounterView = new EncounterView(controller, dimensionGetter);
        final GamePanel buyFoodView = new BuyFoodPanel(controller, dimensionGetter);
        final MinigameSwitchView minigameSwitchView = new MinigameSwitchView(controller, dimensionGetter);
        final GamePanel bankGameView = new BankGameView(controller, dimensionGetter);
        final GamePanel gameOverView = new GameOverView(dimensionGetter);
        final GamePanel gameWonView = new WinView(dimensionGetter);
        final Map<ScenariosType, GamePanel> scenariosPanels = Map.of(
                ScenariosType.INDOOR_MAP, gameMapView,
                ScenariosType.MAIN_MAP, fastTravelView,
                ScenariosType.SLEEPING, sleepView,
                ScenariosType.MINIGAME_GYM, minigameSwitchView,
                ScenariosType.ENCOUNTER, encounterView,
                ScenariosType.MINIGAME_BANK, bankGameView,
                ScenariosType.BUY_FOOD, buyFoodView,
                ScenariosType.GAME_OVER, gameOverView,
                ScenariosType.GAME_WON, gameWonView);
        for (final GamePanel panel : scenariosPanels.values()) {
            panel.setFocusable(true);
        }
        mainPanel.setPreferredSize(dimensionGetter.getFrameDimension());
        mainPanel.setLayout(new BorderLayout());

        scenariosContainer.setPreferredSize(dimensionGetter.getScenarioDimension());
        final CardLayout layout = new CardLayout();
        scenariosContainer.setLayout(layout);
        scenariosContainer.setBackground(Color.RED);

        sideContainer.setPreferredSize(dimensionGetter.getSideDimension());
        sideContainer.setLayout(sideLayout);
        sideContainer.setBackground(Color.BLUE);

        mainPanel.add(scenariosContainer, BorderLayout.CENTER);
        mainPanel.add(sideContainer, BorderLayout.EAST);

        // Creazione dell'azione per il tasto '+'
        final Action increaseSizeAction = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dimensionGetter.incScreenDimension();
                resizeComponents();
                scenariosPanels.values().forEach(GamePanel::resizeComponents);
                statsView.resizeComponents();
            }
        };

        // Creazione dell'azione per il tasto '-'
        final Action decreaseSizeAction = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dimensionGetter.decScreenDimension();
                resizeComponents();
                scenariosPanels.values().forEach(GamePanel::resizeComponents);
                statsView.resizeComponents();
            }
        };


        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke('+'), "increase size");
        mainPanel.getActionMap().put("increase size", increaseSizeAction);

        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke('-'), "decrease size");
        mainPanel.getActionMap().put("decrease size", decreaseSizeAction);


        sideContainer.add(statsView, BorderLayout.CENTER);
        statsView.setVisible(true);

        scenariosContainer.add(gameMapView.getPanelName(), gameMapView);
        scenariosContainer.add(fastTravelView.getPanelName(), fastTravelView);
        scenariosContainer.add(sleepView.getPanelName(), sleepView);
        scenariosContainer.add(minigameSwitchView.getPanelName(), minigameSwitchView);
        scenariosContainer.add(encounterView.getPanelName(), encounterView);
        scenariosContainer.add(bankGameView.getPanelName(), bankGameView);
        scenariosContainer.add(buyFoodView.getPanelName(), buyFoodView);
        scenariosContainer.add(gameOverView.getPanelName(), gameOverView);
        scenariosContainer.add(gameWonView.getPanelName(), gameWonView);



        statsView.setVisible(true);

        final FocusAdapter fa = new FocusAdapter() {
            @Override
            public void focusLost(final FocusEvent e) {
                if (controller.isGameOver()) {
                    controller.changeScenario(ScenariosType.GAME_OVER);
                }
                if (controller.isWin()) {
                    controller.changeScenario(ScenariosType.GAME_WON);
                }
                final GamePanel panelToSwitchTo = scenariosPanels.get(controller.getActualScenario());
                layout.show(scenariosContainer, panelToSwitchTo.getPanelName());
                if (!panelToSwitchTo.equals(bankGameView)) {
                    panelToSwitchTo.requestFocusInWindow();
                }
                panelToSwitchTo.resizeComponents();
                ((SideStatsView) statsView).updateStats();
            }
        };

        scenariosPanels.values().forEach(panel -> panel.addFocusListener(fa));

        scenariosContainer.setDoubleBuffered(true);
        sideContainer.setDoubleBuffered(true);
        statsView.setVisible(true);
        sideContainer.setVisible(true);
        this.add(mainPanel);
        this.setLocationRelativeTo(null); // Posiziona il frame al centro dello schermo
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusable(true);
        gameMapView.requestFocusInWindow();
        gameMapView.setFocusable(true);
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
    public void newGame() {
        newGame = new DifficultyMenu(
                dimensionGetter,
                e -> {
                    this.difficulty = GameDifficulty.EASY;
                    this.remove(newGame);
                    this.start();
                },
                e -> {
                    this.difficulty = GameDifficulty.MEDIUM;
                    this.remove(newGame);
                    this.start();
                },
                e -> {
                    this.difficulty = GameDifficulty.HARD;
                    this.remove(newGame);
                    this.start();
                }
        );
        this.add(newGame);
        this.revalidate();
    }


    /**
     * Closes the game.
     * Sets the default close operation of the frame to exit on close and disposes the frame.
     */
    private void closeGame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
    }


}
