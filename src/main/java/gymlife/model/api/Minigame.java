package gymlife.model.api;

import gymlife.model.Minigame.Timer;

public interface Minigame {

    void notifyKeyPressed(char keyChar);

    void setTimer(Timer timer);
}
