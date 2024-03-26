package gymlife.model;

import java.util.Optional;
import java.util.function.BiFunction;

import gymlife.utility.Position;
import gymlife.utility.Constants;
import gymlife.model.api.CharacterModel;
import gymlife.utility.Directions;
import gymlife.view.CharacterViewImpl;
/**
*  Keylistener responsable for managing movement inputs.
*/
public class CharacterModelImpl implements CharacterModel {

    private static Position pos = Constants.CHARACTER_START_POS;

    /**
    *  Keylistener responsable for managing movement inputs.
    * @return pos 
    */
    @Override
    public Position getCharacterPos() {
        return pos;
    }
    /**
    *  Keylistener responsable for managing movement inputs.
    * @param dir
    */
    @Override
    public void setDir(final Optional<Directions> dir) {
        dir.ifPresent(direction -> {
            final BiFunction<Integer, Integer, Position> newPosition = (x, y) -> new Position(x, y);
            pos = newPosition.apply(pos.X() + Directions.getOffset(direction).get().X(), 
                                     pos.Y() + Directions.getOffset(direction).get().Y());
            CharacterViewImpl.update(pos);
        });
    }
}
