package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.DimensionGetter;
import gymlife.utility.ScenariosType;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;
import gymlife.view.api.GamePanel;
import gymlife.view.api.MinigamePanel;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.Serial;
import java.lang.reflect.InvocationTargetException;


/**
 * The MinigameViewImpl class represents the view component for the minigame
 * feature in the GymLife application.
 * It extends the JPanel class and provides a graphical user interface for the
 * minigame.
 */
public class MinigameSwitchView extends GamePanel {
    @Serial
    private static final long serialVersionUID = 7421500249399144105L;
    private final transient Controller controller;
    private final transient DimensionGetter dimensionGetter;
    private final CardLayout cardLayout = new CardLayout();
    private MinigamePanel minigamePanel;
    private final DifficultyMenu difficultyMenu;


    /**
     * Constructs a MinigameViewImpl object with the specified controller.
     *
     * @param controller      the controller object that handles the minigame logic
     * @param dimensionGetter the dimension getter for screen size adjustments
     */
    public MinigameSwitchView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        final JPanel minigameEndView = new MinigameEndView(controller, dimensionGetter);
        this.setLayout(cardLayout);


        difficultyMenu = new DifficultyMenu(dimensionGetter, e -> {
            controller.setMinigameDifficulty(MinigameDifficulty.EASY);
            startMinigameView();
        }, e -> {
            controller.setMinigameDifficulty(MinigameDifficulty.MEDIUM);
            startMinigameView();
        }, e -> {
            controller.setMinigameDifficulty(MinigameDifficulty.HARD);
            startMinigameView();
        }, new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                difficultyMenu.updateDescription((JButton) e.getSource());
            }
        });


        this.add("difficultyMenu", difficultyMenu);
        this.add("minigameEndView", minigameEndView);

        minigameEndView.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent e) {
                controller.changeScenario(ScenariosType.INDOOR_MAP);
                MinigameSwitchView.super.transferFocus();
            }
        });
        this.setVisible(true);
    }

    /**
     * Starts the minigame and displays the minigame view.
     */
    public void startMinigameView() {
        try {
            minigamePanel = (MinigamePanel) Class.forName(controller.getMinigameType().getViewName())
                    .getDeclaredConstructor(Controller.class, DimensionGetter.class)
                    .newInstance(controller, dimensionGetter);

            ((JPanel) minigamePanel).addComponentListener(new ComponentAdapter() {
                @Override
                public void componentHidden(final ComponentEvent e) {
                    if (controller.getMinigameState() != MinigameState.NOT_STARTED) {
                        cardLayout.show(MinigameSwitchView.this, "minigameEndView");
                    }
                }
            });
            this.revalidate();
            minigamePanel.resizeComponents();
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
        difficultyMenu.resizeComponents();
        if (minigamePanel != null) {
            minigamePanel.resizeComponents();
        }
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
