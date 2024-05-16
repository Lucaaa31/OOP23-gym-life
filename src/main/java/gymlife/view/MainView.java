package gymlife.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;

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
    private Controller controller;
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
        newGame.setLayout(new GridLayout(3, 1));
        FontLoader.loadFont();

        Border border = BorderFactory.createLineBorder(Color.BLACK, 15);

        JLabel textLabel = new JLabel();
        JLabel descriptiontext = new JLabel();

        JButton buttonEasy = this.buildButton("EASY", Color.GREEN, GameDifficulty.EASY);

        JButton buttonMedium = this.buildButton("MEDIUM", Color.YELLOW, GameDifficulty.MEDIUM);

        JButton buttonHard = this.buildButton("HARD", Color.RED, GameDifficulty.HARD);

        JLabel buttonLabel = new JLabel();
        buttonLabel.setLayout(new GridLayout(1, 3));
        buttonLabel.setSize(new Dimension(dimensionGetter.getButtonLabelDimension().width * 3,
                dimensionGetter.getButtonLabelDimension().height));

        JPanel labelEasy = new JPanel();
        labelEasy.setOpaque(true);
        labelEasy.setBackground(Color.DARK_GRAY);
        labelEasy.setPreferredSize(dimensionGetter.getButtonLabelDimension());
        labelEasy.setSize(dimensionGetter.getButtonLabelDimension());
        labelEasy.add(buttonEasy);
        buttonLabel.add(labelEasy);

        JPanel labelMedium = new JPanel();
        labelMedium.setOpaque(true);
        labelMedium.setBackground(Color.DARK_GRAY);
        labelMedium.setPreferredSize(dimensionGetter.getButtonLabelDimension());
        labelMedium.setSize(dimensionGetter.getButtonLabelDimension());
        labelMedium.add(buttonMedium);
        buttonLabel.add(labelMedium);

        JPanel labelHard = new JPanel();
        labelHard.setOpaque(true);
        labelHard.setBackground(Color.DARK_GRAY);
        labelHard.setPreferredSize(dimensionGetter.getButtonLabelDimension());
        labelHard.setSize(dimensionGetter.getButtonLabelDimension());
        labelHard.add(buttonHard);
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
        descriptiontext.setVerticalAlignment(SwingConstants.CENTER);
        descriptiontext.setFont(FontLoader.getCustomFont(55));
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

    private void checkScenario() {
        System.out.println("Checking scenario");
        ScenariosType actualScenario = controller.getActualScenario();
        System.out.println(actualScenario);
        System.out.println(ScenariosType.INDOOR_MAP.toString());
    }
    private JButton buildButton(final String name, final Color color, final GameDifficulty difficulty) {
        JButton button = new JButton(name);
        button.setBackground(color);
        button.setBorder(new LineBorder(Color.WHITE, 5, true));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setPreferredSize(dimensionGetter.getButtonStartDimension());
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setFont(FontLoader.getCustomFont(60));
        button.addActionListener(e -> {
            this.difficulty = difficulty;
            newGame.setVisible(false);
            this.start();
        });
        return button;
    }
}

