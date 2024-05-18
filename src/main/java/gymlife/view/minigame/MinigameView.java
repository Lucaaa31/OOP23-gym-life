package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.ScenariosType;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;
import gymlife.view.DimensionGetter;
import gymlife.view.api.GamePanel;
import gymlife.view.api.MinigamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
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
    final MinigameDifficultyView difficultyView;
    private MinigamePanel minigamePanel;
    private CardLayout cardLayout = new CardLayout();
    private JPanel minigameEndView;


    /**
     * Constructs a MinigameViewImpl object with the specified controller.
     *
     * @param controller the controller object that handles the minigame logic
     */
    public MinigameView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;

        ActionListener deca = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("difficulty selected");
                controller.setDifficulty(MinigameDifficulty.EASY);
                startMinigame();
            }
        };



        this.difficultyView = new MinigameDifficultyView(controller, deca);
        this.minigameEndView = new MinigameEndView(controller);
        this.setLayout(cardLayout);

        this.add("difficultyView", difficultyView);
        this.add("minigameEndView", minigameEndView);


        minigameEndView.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                controller.changeScenario(ScenariosType.INDOOR_MAP);
                MinigameView.super.transferFocus();
            }
        });



        this.setVisible(true);
    }

    /**
     * Starts the minigame and displays the minigame view.
     */
    public void startMinigame(){
        try {
            minigamePanel = (MinigamePanel) Class.forName(controller.getMinigameType().getViewName())
                    .getDeclaredConstructor(Controller.class, DimensionGetter.class)
                    .newInstance(controller, dimensionGetter);

            ((JPanel)minigamePanel).addComponentListener(new ComponentAdapter() {
                @Override
                public void componentHidden(ComponentEvent e) {
                    if (controller.getMinigameState() != MinigameState.NOT_STARTED){
                        cardLayout.show(MinigameView.this, "minigameEndView");
                    }
                }
            });

            this.revalidate();
            this.add("minigamePanel", (JPanel)minigamePanel);
            cardLayout.show(MinigameView.this, "minigamePanel");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                 | InvocationTargetException ignored) {
        }
    }



    public void resizeComponents(){
        //difficultyView.resizeComponents();
        //minigamePanel.resizeComponents();
//        minigameEndView.resizeComponents();
    }

    @Override
    public String getPanelName() {
        return "minigameGym";
    }


}
