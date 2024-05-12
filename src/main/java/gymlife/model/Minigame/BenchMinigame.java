package gymlife.model.Minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;

/**
 * Represents a bench minigame that implements the Minigame interface and the
 * Runnable interface.
 */
public class BenchMinigame implements Minigame, Runnable {
    private MinigameDifficulty difficulty;
    private Thread timerThread;
    private boolean isPressed;
    private int nTimesPressed;
    private int state;
    private int numReps;

    /**
     * Constructs a new BenchMinigame object.
     * Initializes the instance variables.
     */
    public BenchMinigame() {
        this.isPressed = false;
        this.nTimesPressed = 0;
        this.state = 0;
        this.numReps = 0;
        this.timerThread = null;
        this.difficulty = null;
    }

    /**
     * Notifies the bench minigame that a button has been pressed.
     * Sets the isPressed variable to true.
     */
    @Override
    public void notifyButtonPressed() {
        this.isPressed = true;
    }

    /**
     * Sets the timer for the bench minigame.
     * Creates a new thread for the timer and sets the running time according to the
     * difficulty level.
     */
    @Override
    public void setTimer(final TimerImpl timer) {
        timerThread = new Thread(timer);
        timer.setRunningTime(difficulty.getReactionTime());
    }

    /**
     * Runs the bench minigame.
     * 
     */
    @Override
    public void run() {
        timerThread.start();
        while (timerThread.isAlive() && numReps < difficulty.getRequiredReps()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            if (isPressed) {
                nTimesPressed++;
                System.out.println("Pressed");
                isPressed = false;
                if (nTimesPressed == difficulty.getnRepsForSwitchState()) {
                    System.out.println("Switch state");
                    state++;
                    if (state == 4) {
                        numReps++;
                        state = 0;
                    }
                    nTimesPressed = 0;
                }
            }
        }
    }

    /**
     * Gets the current state of the bench minigame.
     *
     * @return the current state of the bench minigame
     */
    public int getState() {
        return state;
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
     * Checks if the timer thread is alive.
     *
     * @return true if the timer thread is alive, false otherwise
     */
    public boolean isAlive() {
        if (timerThread == null) {
            return false;
        }
        return timerThread.isAlive();
    }

}
