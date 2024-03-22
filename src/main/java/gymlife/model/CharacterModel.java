package gymlife.model;

import java.util.Optional;

import gymlife.utility.Position;
import gymlife.utility.Constants;

import gymlife.utility.Directions;
import gymlife.view.CharacterView;

public class CharacterModel {

    private static Position pos = Constants.CHARACTER_START_POS;

    public Position getCharacterPos() {
        return pos;
    }

    public void setDir(Optional<Directions> dir) {
        if (dir.isPresent()) {
            int tmpX = pos.X() + Directions.GetOffset(dir.get()).get().X();
            int tmpY = pos.Y() + Directions.GetOffset(dir.get()).get().Y();
            pos = new Position(tmpX, tmpY);
            CharacterView.update(pos);
        }
    }
}
