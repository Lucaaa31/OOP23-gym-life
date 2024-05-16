package gymlife.view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
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
    private final JPanel mainPanel = new JPanel();
    private final JPanel scenariosContainer = new JPanel();
    private final JPanel sideContainer = new JPanel();
    private final JPanel newGame = new JPanel();
    private JPanel fastTravelView;
    private JPanel statsView;
    private JPanel gameMapView;
    private GameDifficulty difficulty;

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
        final Controller controller = new ControllerImpl(difficulty);
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

        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke('+'), "increase size");
        mainPanel.getActionMap().put("increase size", increaseSizeAction);
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke('-'), "decrease size");
        mainPanel.getActionMap().put("decrease size", decreaseSizeAction);

        sideContainer.add(statsView, BorderLayout.CENTER);
        statsView.setVisible(true);
        scenariosContainer.add(gameMapView, ScenariosType.INDOOR_MAP.toString());

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

    }

    // Metodo per ridimensionare i pannelli proporzionalmente
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

    private void newGame() {
        newGame.setLayout(new GridLayout(2, 1));
        FontLoader.loadFont();

        Border border = BorderFactory.createLineBorder(Color.WHITE, 10);

        JLabel textLabel = new JLabel();
        JButton buttonEasy = new JButton();
        buttonEasy.setPreferredSize(dimensionGetter.getButtonStartDimension());
        buttonEasy.setOpaque(true);
        buttonEasy.setContentAreaFilled(true);
        buttonEasy.setBackground(Color.GREEN);
        buttonEasy.setFont(FontLoader.getCustomFont(30));
        buttonEasy.addActionListener(e -> {
            difficulty = GameDifficulty.EASY;
            this.start();
            newGame.setVisible(false);
        });

        JButton buttonMedium = new JButton("MEDIUM");
        buttonMedium.setPreferredSize(dimensionGetter.getButtonStartDimension());
        buttonMedium.setOpaque(true);
        buttonMedium.setContentAreaFilled(true);
        buttonMedium.setBackground(Color.YELLOW);
        buttonMedium.setFont(FontLoader.getCustomFont(30));
        buttonMedium.addActionListener(e -> {
            difficulty = GameDifficulty.MEDIUM;
            this.start();
            newGame.setVisible(false);
        });

        JButton buttonHard = new JButton("HARD");
        buttonHard.setPreferredSize(dimensionGetter.getButtonStartDimension());
        buttonHard.setOpaque(true);
        buttonHard.setContentAreaFilled(true);
        buttonHard.setBackground(Color.RED);
        buttonHard.setFont(FontLoader.getCustomFont(30));
        buttonHard.addActionListener(e -> {
            difficulty = GameDifficulty.HARD;
            this.start();
            newGame.setVisible(false);
        });

        JLabel buttonLabel = new JLabel();
        buttonLabel.setLayout(new FlowLayout());
        buttonEasy.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon button = new ImageIcon("images/icons/broccoli.png");
        button = new ImageIcon(button.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        buttonEasy.setIcon(button);

        buttonLabel.add(buttonEasy);

        buttonLabel.add(buttonMedium);

        buttonLabel.add(buttonHard);




        newGame.add(textLabel);
        newGame.add(buttonLabel);
        newGame.setBackground(Color.GRAY);
        newGame.setPreferredSize(dimensionGetter.getFrameDimension());

        textLabel.setText("New Game");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setFont(FontLoader.getCustomFont(100));
        textLabel.setForeground(Color.WHITE);
        textLabel.setBorder(border);

        this.add(newGame);
    }
}

