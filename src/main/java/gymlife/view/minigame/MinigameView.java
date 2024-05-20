package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.ScenariosType;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;
import gymlife.view.DimensionGetter;
import gymlife.view.api.GamePanel;
import gymlife.view.api.MinigamePanel;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.Serial;
import java.lang.reflect.InvocationTargetException;


/**
 * The MinigameViewImpl class represents the view component for the minigame
 * feature in the GymLife application.
 * It extends the JPanel class and provides a graphical user interface for the
 * minigame.
 */
public class MinigameView extends GamePanel {
    @Serial
    private static final long serialVersionUID = 7421500249399144105L;
    private final transient Controller controller;
    private final transient DimensionGetter dimensionGetter;
    private final MinigameDifficultyView difficultyView;
    private final CardLayout cardLayout = new CardLayout();
    private MinigamePanel minigamePanel;


    /**
     * Constructs a MinigameViewImpl object with the specified controller.
     *
     * @param controller      the controller object that handles the minigame logic
     * @param dimensionGetter the dimension getter for screen size adjustments
     */
    public MinigameView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.difficultyView = new MinigameDifficultyView(dimensionGetter);
        final JPanel minigameEndView = new MinigameEndView(controller);
        this.setLayout(cardLayout);

        for (final MinigameDifficulty difficulty : MinigameDifficulty.values()) {
            difficultyView.initializeActionListener(e -> {
                controller.setMinigameDifficulty(difficulty);
                startMinigame();
            }, difficulty);
        }

        this.add("difficultyView", difficultyView);
        this.add("minigameEndView", minigameEndView);

        minigameEndView.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent e) {
                controller.changeScenario(ScenariosType.INDOOR_MAP);
                MinigameView.super.transferFocus();
            }
        });
        this.setVisible(true);
    }

    /**
     * Starts the minigame and displays the minigame view.
     */
    public void startMinigame() {
        try {
            minigamePanel = (MinigamePanel) Class.forName(controller.getMinigameType().getViewName())
                    .getDeclaredConstructor(Controller.class, DimensionGetter.class)
                    .newInstance(controller, dimensionGetter);

            ((JPanel) minigamePanel).addComponentListener(new ComponentAdapter() {
                @Override
                public void componentHidden(final ComponentEvent e) {
                    if (controller.getMinigameState() != MinigameState.NOT_STARTED) {
                        cardLayout.show(MinigameView.this, "minigameEndView");
                    }
                }
            });
            this.revalidate();
            this.add("minigamePanel", (JPanel) minigamePanel);
            cardLayout.show(this, "minigamePanel");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                 | InvocationTargetException ignored) {
        }
    }

    /**
     * Resizes the components in the view.
     */
    @Override
    public void resizeComponents() {
        difficultyView.resizeComponents();
        if (minigamePanel != null) {
            minigamePanel.resizeComponents();
        }
//        minigameEndView.resizeComponents();
    }

    /**
     * Returns the name of the panel.
     *
     * @return the name of the panel
     */
    @Override
    public String getPanelName() {
        return "minigameGym";
    }


}
