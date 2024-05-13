package gymlife.view;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


import java.awt.*;
import java.awt.event.*;
import java.io.Serial;

import gymlife.utility.Constants;

//import java.util.HashMap;
//import java.util.Map;
import gymlife.controller.api.Controller;
import gymlife.controller.ControllerImpl;
import gymlife.utility.GameDifficulty;
import gymlife.view.stats.SideStatsView;
//import gymlife.utility.ScenariosType;

/**
 * The MainView class represents the main view of the application.
 * It extends the JFrame class and provides methods to start and display the main view.
 */
public class MainView extends JFrame {
    @Serial
    private static final long serialVersionUID = -3544425205075144844L;
    private final transient  Controller controller = new ControllerImpl(GameDifficulty.EASY);
    private final JPanel mainPanel = new JPanel();
    private final JPanel scenariosContainer = new JPanel();
    private final JPanel sideContainer = new JPanel();
    private final JPanel statsView = new SideStatsView(controller);
    private final DimensionGetter dimensionGetter = new DimensionGetter();
    private final JPanel gameMapView = new GameMapView(controller, dimensionGetter);

//    private final CharacterView charView = new CharacterView(controller);
//    private final Map<ScenariosType,JPanel> scenariosMap = new HashMap<>();

    /**
     * Starts the main view of the application.
     * Sets the size, layout, and default close operation of the frame.
     * Sets the size, layout, and visibility of the character view panel.
     * Adds the character view panel to the main frame and makes it visible.
     */
    public void start() {
        this.setSize(dimensionGetter.getFrameDimension());
        System.out.println("Screen width: " + dimensionGetter.getFrameDimension().width + " Screen height: " + dimensionGetter.getFrameDimension().height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setPreferredSize(dimensionGetter.getFrameDimension());
        mainPanel.setLayout(new BorderLayout());

        scenariosContainer.setPreferredSize(dimensionGetter.getScenarioDimension());
        scenariosContainer.setLayout(new CardLayout());
        scenariosContainer.setBackground(Color.RED);

        sideContainer.setPreferredSize(dimensionGetter.getSideDimension());
        sideContainer.setLayout(new CardLayout());
        sideContainer.setBackground(Color.BLUE);

        mainPanel.add(scenariosContainer, BorderLayout.WEST);
        mainPanel.add(sideContainer, BorderLayout.CENTER);

//        this.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                if (e.getKeyChar() == '+') {
//                    dimensionGetter.incScreenDimension();
//                    System.out.println("Main view resized");
//                    resizeComponents();
//                    ((GameMapView) gameMapView).resizeComponents(); // Aggiunta la chiamata al metodo resizeComponents() della classe GameMapView
//                }
//                if (e.getKeyChar() == '-') {
//                    dimensionGetter.decScreenDimension();
//                    resizeComponents();
//                    ((GameMapView) gameMapView).resizeComponents(); // Aggiunta la chiamata al metodo resizeComponents() della classe GameMapView
//                }
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });

        // Creazione dell'azione per il tasto '+'
        Action increaseSizeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dimensionGetter.incScreenDimension();
                System.out.println("Main view resized");
                resizeComponents();
                ((GameMapView) gameMapView).resizeComponents();
            }
        };

        // Creazione dell'azione per il tasto '-'
        Action decreaseSizeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dimensionGetter.decScreenDimension();
                resizeComponents();
                ((GameMapView) gameMapView).resizeComponents();
            }
        };
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('+'), "increase size");
        mainPanel.getActionMap().put("increase size", increaseSizeAction);
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('-'), "decrease size");
        mainPanel.getActionMap().put("decrease size", decreaseSizeAction);

        sideContainer.add(statsView, BorderLayout.CENTER);
        statsView.setVisible(true);
        scenariosContainer.add(gameMapView, SwingConstants.CENTER);

        gameMapView.setVisible(true);
        sideContainer.setVisible(true);
        this.setUndecorated(true);
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

        mainPanel.repaint();;
        this.pack();
        this.repaint();
        this.setLocationRelativeTo(null); // Posiziona il frame al centro dello schermo

        System.out.println("Main view " + this.getSize().width + " " + this.getSize().height);
        System.out.println("Main Panel " + mainPanel.getSize().width + " " + mainPanel.getSize().height);
        System.out.println("Screen width: " + dimensionGetter.getFrameDimension().width + " Screen height: "
                + dimensionGetter.getFrameDimension().height + "\n");
    }
}
