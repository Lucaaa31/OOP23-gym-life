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
    private long startReactionTime;
    private int nMistakes;
    private boolean isRepsCompleted;
    private long startMinigame;
    private long endMinigame;
    private boolean isFirstTimePressed = true;
    private boolean isReactionTimeSet = false;


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
        minigameState = MinigameState.RUNNING;
        if (isFirstTimePressed) {
            isFirstTimePressed = false;
            startMinigame = System.nanoTime();
        }

        nTimesPressed++;

        if (!isReactionTimeSet) {
            startReactionTime = System.nanoTime();
            System.out.println("Start reaction time: " + startReactionTime);
            isReactionTimeSet = true;
        } else {
            getValidity();
        }

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
    @Override
    public void getValidity() {
        long endReactionTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startReactionTime);
        //System.out.println("End reaction time: " + endReactionTime);

        // Reset startReactionTime for the next press
        startReactionTime = System.nanoTime();

        if (endReactionTime < difficulty.getReactionTime()) {
            if (nTimesPressed == difficulty.getTouchForLift()) {
                nTimesPressed = 0;
                numReps++;
                System.out.println("Reps: " + numReps);
                minigameState = MinigameState.REP_REACHED;
                isReactionTimeSet = false;
                if (numReps == difficulty.getRequiredReps()) {
                    endMinigame = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startMinigame);
                    minigameState = MinigameState.ENDED_WON;
                    isRepsCompleted = true;
                }
            }
        } else {
            nMistakes++;
            minigameState = MinigameState.MISTAKE_MADE;
            isReactionTimeSet = false;
            if (nMistakes > difficulty.getMaxMistakes()) {
                minigameState = MinigameState.ENDED_LOST;
            }
            nTimesPressed = 0;
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
