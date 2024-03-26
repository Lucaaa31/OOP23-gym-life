package gymlife.model;

import gymlife.model.api.CharacterModel;
import gymlife.model.api.GameState;
import gymlife.utility.Directions;
import gymlife.utility.StateType;

public class GameStateImpl implements GameState{
    private StateType gameState = StateType.MAIN_MAP;
    private static final CharacterModel charMod = new CharacterModelImpl();

    public void setGameState(StateType newState) {
        gameState = newState;
    }
    public StateType getGameState() {
        return gameState;
    }
    @Override
    public void setKey(char key) {
        switch (gameState) {
            case MAIN_MAP:
            charMod.setDir(Directions.getDir(key));
                break;
            default:
                break;
        }
    }
}
