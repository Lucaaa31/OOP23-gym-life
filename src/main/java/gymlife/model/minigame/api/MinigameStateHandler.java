package gymlife.model.minigame.api;


public interface MinigameStateHandler {

    void notStarted();

    void pressedStartButton();

    void running();

    void validPress();

    void invalidPress();

    void repReached();

    void miniGameEndedWon();

    void miniGameEndedLost();



}
