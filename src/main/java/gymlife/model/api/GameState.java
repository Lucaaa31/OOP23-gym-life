package gymlife.model.api;

import gymlife.utility.StateType;

public interface GameState {
    void setGameState(StateType newState);
    StateType getGameState ();
    void setKey(char key);
}
