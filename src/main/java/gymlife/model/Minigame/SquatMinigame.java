package gymlife.model.Minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;

import javax.swing.*;

public class SquatMinigame implements Minigame {

    public void start() {
        System.out.println("Squat minigame started");
        while (true) {

        }
        //System.out.println("Squat minigame finished");
    }

    @Override
    public void notifyButtonPressed() {
        System.out.println("Button pressed");
    }

    @Override
    public void setTimer(Timer timer) {

    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public void setDifficulty(MinigameDifficulty selectedDifficulty) {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void notifyKeyPressed(char keyChar) {

    }

}