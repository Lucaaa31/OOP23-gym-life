package gymlife.model;

import java.util.Optional;

import gymlife.utility.Position;
import gymlife.utility.api.Constants;
import gymlife.model.api.CharacterModel;
import gymlife.utility.Directions;
import gymlife.view.CharacterViewImpl;

public class CharacterModelImpl implements CharacterModel {

    private static Position pos = Constants.CHARACTER_START_POS;

    @Override
    public Position getCharacterPos() {
        return pos;
    }

    @Override
    public void setDir(Optional<Directions> dir) {
        if (dir.isPresent()) {
            int tmpX = pos.X() + Directions.GetOffset(dir.get()).get().X();
            int tmpY = pos.Y() + Directions.GetOffset(dir.get()).get().Y();
            pos = new Position(tmpX, tmpY);
            CharacterViewImpl.update(pos);
        }
    }
}
