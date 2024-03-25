package gymlife.model.api;

import java.util.Optional;

import gymlife.utility.Directions;
import gymlife.utility.Position;

public interface CharacterModel {

    Position getCharacterPos();

    void setDir(Optional<Directions> dir);

}