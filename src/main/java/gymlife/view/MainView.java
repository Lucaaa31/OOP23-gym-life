package gymlife.view;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;

import gymlife.controller.api.Controller;
import gymlife.controller.ControllerImpl;
import gymlife.utility.GameDifficulty;


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
    private final transient DimensionGetter dimensionGetter = new DimensionGetter();
    private final JPanel statsView = new SideStatsView(controller, dimensionGetter);
    private final JPanel gameMapView = new GameMapView(controller, dimensionGetter);
    private final FastTravelView fastTravelView = new FastTravelView(controller);

    /**
     * Starts the main view of the application.
     * Sets the size, layout, and default close operation of the frame.
     * Sets the size, layout, and visibility of the character view panel.
     * Adds the character view panel to the main frame and makes it visible.
     */
    public void start() {
        this.setSize(dimensionGetter.getFrameDimension());
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
        scenariosContainer.add(gameMapView, SwingConstants.CENTER);

        gameMapView.setVisible(true);
        gameMapView.setDoubleBuffered(true);
        scenariosContainer.setDoubleBuffered(true);
        sideContainer.setDoubleBuffered(true);
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

        mainPanel.repaint();
        this.pack();
        this.repaint();
        this.setLocationRelativeTo(null); // Posiziona il frame al centro dello schermo
    }
}
