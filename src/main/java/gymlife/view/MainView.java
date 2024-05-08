package gymlife.view;

import javax.swing.JPanel;
import javax.swing.JFrame;


import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import gymlife.utility.Constants;

//import java.util.HashMap;
//import java.util.Map;
//import gymlife.controller.api.Controller;
//import gymlife.controller.ControllerImpl;
//import gymlife.utility.ScenariosType;

/**
 * The MainView class represents the main view of the application.
 * It extends the JFrame class and provides methods to start and display the main view.
 */
public class MainView extends JFrame {
    public static final long serialVersionUID = 4328743;
//    private final transient  Controller controller = new ControllerImpl();
    private final JPanel mainPanel = new JPanel();
    private final JPanel scenariosContainer = new JPanel();
    private final JPanel sideContainer = new JPanel();
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
        sideContainer.setPreferredSize(new Dimension(Constants.SIDE_WIDTH, Constants.HEIGHT));
        scenariosContainer.setBackground(Color.BLUE);
        sideContainer.setBackground(Color.RED);

        final BorderLayout b = new BorderLayout();
        mainPanel.setLayout(b);

        this.add(mainPanel);

        mainPanel.add(scenariosContainer, BorderLayout.WEST);
        mainPanel.add(sideContainer, BorderLayout.CENTER);

        this.setResizable(true);

        // Aggiunta del ComponentListener per gestire il ridimensionamento della finestra
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                resizeComponents();
            }
        });

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
