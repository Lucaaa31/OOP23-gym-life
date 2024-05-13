package gymlife.view;

import javax.swing.JPanel;
import javax.swing.JFrame;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.Serial;

import gymlife.utility.Constants;

//import java.util.HashMap;
//import java.util.Map;
import gymlife.controller.api.Controller;
import gymlife.controller.ControllerImpl;
import gymlife.utility.GameDifficulty;
import gymlife.view.minigame.MinigameViewImpl;
import gymlife.view.stats.SideStatsView;
//import gymlife.utility.ScenariosType;

/**
 * The MainView class represents the main view of the application.
 * It extends the JFrame class and provides methods to start and display the main view.
 */
public class MainView extends JFrame {
    @Serial
    private static final long serialVersionUID = 4328743;
    private final transient Controller controller = new ControllerImpl(GameDifficulty.EASY);
    private final JPanel mainPanel = new JPanel();
    private final JPanel scenariosContainer = new JPanel();
    private final JPanel sideContainer = new JPanel();
    private final JPanel statsView = new SideStatsView(controller);

//    private final CharacterView charView = new CharacterView(controller);
//    private final GameMapView gameMapView = new GameMapView(controller);
//    private final Map<ScenariosType,JPanel> scenariosMap = new HashMap<>();

    /**
     * Starts the main view of the application.
     * Sets the size, layout, and default close operation of the frame.
     * Sets the size, layout, and visibility of the character view panel.
     * Adds the character view panel to the main frame and makes it visible.
     */
    public void start() {
        this.setSize(Constants.FRAME_WIDTH, Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.HEIGHT));

        scenariosContainer.setPreferredSize(new Dimension(Constants.SCENARIO_WIDTH, Constants.HEIGHT));
        scenariosContainer.setLayout(new CardLayout());
        scenariosContainer.setBackground(Color.BLUE);

        sideContainer.setPreferredSize(new Dimension(Constants.SIDE_WIDTH, Constants.HEIGHT));
        sideContainer.setLayout(new CardLayout());
        sideContainer.setBackground(Color.BLACK);

        final BorderLayout b = new BorderLayout();
        mainPanel.setLayout(b);

        this.add(mainPanel);

//        MinigameManager minigameManager = new MinigameManager();
//        controller.setMinigameManager(minigameManager);
//        minigameManager.setCurrentMinigame(MinigameType.BENCH_PRESS);


        mainPanel.add(new MinigameViewImpl(controller), BorderLayout.CENTER);
        mainPanel.add(sideContainer, BorderLayout.EAST);

        // Aggiunta del ComponentListener per gestire il ridimensionamento della finestra
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                resizeComponents();
            }
        });
        sideContainer.add(statsView);
        statsView.setVisible(true);
        this.setLocationRelativeTo(null); // Posiziona il frame al centro dello schermo

        this.pack();
        this.setResizable(true);
        this.setVisible(true);
    }

    // Metodo per ridimensionare i pannelli proporzionalmente
    private void resizeComponents() {
        final int newFrameWidth = getWidth();
        final int newFrameHeight = getHeight();

        // Calcola le nuove dimensioni proporzionali per i pannelli
        final int newScenarioWidth = (int) (newFrameWidth * Constants.SCENARIO_WIDTH / (double) Constants.FRAME_WIDTH);
        final int newSideWidth = (int) (newFrameWidth * Constants.SIDE_WIDTH / (double) Constants.FRAME_WIDTH);

        // Imposta le nuove dimensioni per i pannelli
        scenariosContainer.setPreferredSize(new Dimension(newScenarioWidth, newFrameHeight));
        sideContainer.setPreferredSize(new Dimension(newSideWidth, newFrameHeight));

        // Aggiorna il layout
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
