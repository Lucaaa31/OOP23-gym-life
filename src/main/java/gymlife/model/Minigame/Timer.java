package gymlife.model.Minigame;


import java.util.List;

public class Timer implements Runnable{
    private int milliseconds;
    private int runningTime;
    private boolean isRunning = false;

    public Timer() {
    }

    @Override
    public void run() {
        while (runningTime > 0 && !Thread.currentThread().isInterrupted()) {
            isRunning = true;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            runningTime--;
        }
        isRunning = false;
    }

    public List<Integer> getRunningTime(){
        return List.of(runningTime/10000,
                (runningTime / 1000) % 10,
                (runningTime / 100) % 10,
                runningTime % 10);
    }

    public void setRunningTime(final int milliseconds) {
        this.runningTime = milliseconds;
        this.milliseconds = milliseconds;
    }

    public void resetTimer() {
        runningTime = milliseconds;
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}