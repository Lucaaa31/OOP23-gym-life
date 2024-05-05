package gymlife.model.Minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;

import java.util.Random;

public class BenchMinigame implements Minigame, Runnable {
    private MinigameDifficulty difficulty = MinigameDifficulty.EASY;
    private Thread timerThread;
    private boolean isPressed;
    private char actualKey;


    public BenchMinigame(MinigameDifficulty difficulty) {
    }

    private void checkIfCorrect() {

    }

    @Override
    public void notifyKeyPressed(char keyChar) {
        isPressed = true;
    }

    @Override
    public void setTimer(Timer timer) {
        timerThread = new Thread(timer);
        timer.setRunningTime(difficulty.getReactionTime());
    }

    public void setActualKey() {
        Random random = new Random();
        actualKey = (char) (random.nextInt(26) + 'a');
    }

    @Override
    public void run() {
        new Thread(() -> {
            for (int numReps = 0; numReps < difficulty.getMaxReps(); numReps++) {
                setActualKey();
                System.out.println("Key: " + actualKey);
                timerThread.start();
                while (timerThread.isAlive()) {
                    if (isPressed) {
                        System.out.println("Pressed");
                        isPressed = false;
                    }
                }
                timerThread.interrupt();
            }
        }).start();

    }
}