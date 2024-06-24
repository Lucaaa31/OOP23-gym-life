package gymlife.model.minigame;

import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * The Minigame interface represents a game that can be played within the gym.
 * It provides methods for notifying button presses, setting a timer, getting
 * the current state,
 * and setting the difficulty level of the minigame.
 */
public abstract class Minigame {
    private MinigameDifficulty difficulty;
    private MinigameState minigameState = MinigameState.NOT_STARTED;

    /**
     * Constructs a new Minigame object and initializes the instance variables.
     */
    public Minigame() {
        this.difficulty = null;
    }


    /**
     * Notify the model of an action of the player in the lat and squat mini-games.
     *
     * @param buttonCode the parameters of the action
     */
    public abstract void notifyUserAction(String buttonCode);

    /**
     * Sets the difficulty level of the minigame.
     *
     * @param selectedDifficulty the selected difficulty level
     */
    public void setDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.difficulty = selectedDifficulty;

    }


    /**
     * Returns the state of the minigame.
     *
     * @return 0 if the minigame isn't started, 1 if the minigame is running, 2 if the minigame is ended
     */
    public MinigameState getMinigameState(){
        return minigameState;
    }

    /**
     * Returns the difficulty level of the minigame.
     *
     * @return the difficulty level of the minigame
     */
    public MinigameDifficulty getDifficulty() {
        return difficulty;
    }


    /**
     * Returns the time to complete the minigame.
     *
     * @return the time to complete the minigame
     */
    public abstract int getTimeMinigame();




    /**
     * Abstract method to get the sequence of the minigame.
     *
     * @return the sequence of the minigame
     */
    public abstract List<String> getSequence();

    /**
     * Creates a random sequence depending on the minigame.
     */
    public void createRandomSequence() {

    }


    public void setMinigameState(final MinigameState minigameState) {
        this.minigameState = minigameState;
    }
}
