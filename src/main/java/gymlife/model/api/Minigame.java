package gymlife.model.api;

import gymlife.model.Minigame.Timer;

public interface Minigame {

    void notifyButtonPressed();

    void setTimer(Timer timer);

    int getState();
}
