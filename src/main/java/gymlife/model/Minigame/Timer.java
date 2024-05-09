package gymlife.model.Minigame;

import java.util.List;

/**
 * The Timer class represents a timer that counts down from a specified time in milliseconds.
 * It implements the Runnable interface to allow it to be executed in a separate thread.
 */
public class Timer extends Thread {
    private int milliseconds;
    private int runningTime;
    private boolean isRunning = false;

    /**
     * Constructs a Timer object with default values.
     */
    public Timer() {
    }

    /**
     * Runs the timer by decrementing the running time until it reaches 0 or the thread is interrupted.
     * The timer runs in a separate thread.
     */
    @Override
    public void run() {
        System.out.println("Running");

        while (runningTime > 0 && !Thread.currentThread().isInterrupted()) {
            isRunning = true;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            runningTime--;
        }
        System.out.println("Timer finished");
        isRunning = false;
    }

    /**
     * Returns the running time of the timer as a list of integers representing the hours, minutes, seconds, and milliseconds.
     *
     * @return the running time of the timer
     */
    public int getRunningTime() {
        return runningTime;
    }

    /**
     * Sets the running time of the timer to the specified number of milliseconds.
     *
     * @param milliseconds the number of milliseconds to set as the running time
     */
    public void setRunningTime(final int milliseconds) {
        this.runningTime = milliseconds;
        this.milliseconds = milliseconds;
    }

    /**
     * Resets the timer to its initial state, setting the running time to the original value and stopping the timer.
     */
    public void resetTimer() {
        runningTime = milliseconds;
        isRunning = false;
    }

    /**
     * Checks if the timer is currently running.
     *
     * @return true if the timer is running, false otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }
}