package gymlife.model.minigame;

import com.sun.nio.sctp.SendFailedNotification;
import gymlife.model.api.Minigame;
import gymlife.utility.minigame.MinigameDifficulty;

import java.util.concurrent.TimeUnit;

/**
 * Represents a bench minigame that implements the Minigame interface and the
 * Runnable interface.
 */
public class BenchMinigame implements Minigame {
    private MinigameDifficulty difficulty;
    private int nTimesPressed;
    private int numReps;
    private boolean isMinigameEnded;
    private long startTime;
    private boolean resultMinigame = true;
    private int nMistakes;
    private boolean isRepsCompleted;
    private long startMinigame = 0;
    private int endMinigame = 0;
    private boolean isFirstTimePressed = true;


    /**
     * Constructs a new BenchMinigame object.
     * Initializes the instance variables.
     */
    public BenchMinigame() {
        this.isMinigameEnded = false;
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
            startTime = System.nanoTime();
        }
        if (nTimesPressed == 0) {
            isRepsCompleted = false;
            startTime = System.nanoTime();
        }
        nTimesPressed++;
        checkIfCompletedReps();
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
    private void checkIfCompletedReps() {
        long endTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        System.out.println("End time: " + endTime);
        if (endTime < difficulty.getReactionTime()) {
            if (nTimesPressed == difficulty.getTouchForLift()) {
                isRepsCompleted = true;
                nTimesPressed = 0;
                numReps++;
            }
            if (numReps == difficulty.getRequiredReps()) {
                endMinigame = (int)TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startMinigame);
                System.out.println("End minigame: " + endMinigame);
                System.out.println("Exssa" + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startMinigame));
                isMinigameEnded = true;
            }
        } else {
            nMistakes++;
            if (nMistakes == difficulty.getMaxMistakes()) {
                resultMinigame = false;
                isMinigameEnded = true;
            } else {
                nTimesPressed = 0;
            }
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
     * Returns the result of the bench minigame.
     *
     * @return the result of the bench minigame
     */
    @Override
    public int minigameResult() {
        return resultMinigame ? 0 : difficulty.getExperienceGained();
    }

    /**
     * Return the state of the bench minigame.
     *
     * @return return true if the minigame is ended, false otherwise
     */
    @Override
    public boolean isMinigameEnded() {
        return isMinigameEnded;
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

    public long getEndMinigame() {
        int seconds = endMinigame / 1000;
        int hundredths = (endMinigame % 1000) / 10;
        return seconds + hundredths;
    }


}
