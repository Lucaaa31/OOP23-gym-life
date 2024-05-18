package gymlife.model.minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;

import java.util.concurrent.TimeUnit;

/**
 * Represents a bench minigame that implements the Minigame interface and the
 * Runnable interface.
 */
public class BenchMinigame implements Minigame {
    private MinigameDifficulty difficulty;
    private int nTimesPressed;
    private int numReps;
    private MinigameState minigameState;
    private long startTime;
    private int nMistakes;
    private boolean isRepsCompleted;
    private long startMinigame;
    private long endMinigame;
    private boolean isFirstTimePressed = true;


    /**
     * Constructs a new BenchMinigame object.
     * Initializes the instance variables.
     */
    public BenchMinigame() {
        this.minigameState = MinigameState.NOT_STARTED;
        this.nTimesPressed = 0;
        this.numReps = 0;
        this.difficulty = null;
        this.nMistakes = 0;
        this.isRepsCompleted = false;
    }

    /**
     * Notifies the bench minigame that a button has been pressed.
     * Starts the timer if it is the first time the button is pressed.
     */
    @Override
    public void notifyUserAction() {
        if (isFirstTimePressed) {
            isFirstTimePressed = false;
            startMinigame = System.nanoTime();
            minigameState = MinigameState.RUNNING;
            System.out.println("Minigame started");
        }
        if (nTimesPressed == 0) {
            isRepsCompleted = false;
            startTime = System.nanoTime();
        }
        nTimesPressed++;

    }

    /**
     * The view check if the reps has been completed.
     *
     * @return true if the reps has been completed, false otherwise
     */
    @Override
    public boolean isRepsCompleted() {
        return isRepsCompleted;
    }

    /**
     * Checks if the user has completed the required number of reps.
     * If the user has completed the required number of reps, the minigame is ended.
     * If the user has made the maximum number of mistakes, the minigame is ended.
     */
    public boolean getValidity() {
        long endTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        if (endTime < difficulty.getReactionTime()) {
            if (nTimesPressed == difficulty.getTouchForLift()) {
                isRepsCompleted = true;
                nTimesPressed = 0;
                numReps++;
            }
            if (numReps == difficulty.getRequiredReps()) {
                endMinigame = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startMinigame);
                minigameState = MinigameState.ENDED_WON;
                System.out.println("Minigame ended with a win!");
            }
            return true;
        } else {
            nMistakes++;
            if (nMistakes == difficulty.getMaxMistakes()) {
                System.out.println("Minigame ended with a lost!");
                minigameState = MinigameState.ENDED_LOST;
            }
            nTimesPressed = 0;
            return false;
        }
    }

    /**
     * Sets the difficulty level for the bench minigame.
     *
     * @param selectedDifficulty the selected difficulty level for the minigame
     */
    @Override
    public void setDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.difficulty = selectedDifficulty;
    }


    /**
     * Return the state of the bench minigame.
     *
     * @return 0 if the minigame isn't started, 1 if the minigame is running, 2 if the minigame is ended
     */
    @Override
    public MinigameState getMinigameState() {
        return minigameState;
    }

    /**
     * Returns the difficulty level of the bench minigame.
     *
     * @return the difficulty level of the bench minigame
     */
    @Override
    public MinigameDifficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Returns the time that took the player to complete the minigame.
     *
     * @return the number of times the button has been pressed
     */
    public int getEndMinigame() {
            return Math.toIntExact(endMinigame);
    }


}
