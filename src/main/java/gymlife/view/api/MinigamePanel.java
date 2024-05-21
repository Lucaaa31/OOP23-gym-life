package gymlife.view.api;

import javax.swing.ImageIcon;

/**
 * The MinigamePanel interface represents the view component for the minigame.
 * It provides the necessary UI elements and functionality for the minigame.
 */
public interface MinigamePanel {

    /**
     * Do the animation of the charachter.
     */
    void doAnimation();

    /**
     * Resize the components inside the panel.
     */
    void resizeComponents();

    /**
     * Method to update the timer view.
     */
    void timerView();

    /**
     * Method for update the image of the character.
     *
     * @param path the path of the image
     * @return the image icon
     */
    ImageIcon getCharacterImage(String path);


}
