package gymlife.controller.api;

import java.awt.event.KeyListener;

import gymlife.model.api.CharacterModel;
/**
* Interface that handle key inputs from keyboard to move the character.
*/
public interface CharacterController {

    long serialVersionUID = 4328743;
    /**
    *  Keylistener responsable for managing movement inputs.
    * @return KeyListener
    */
    KeyListener getCharacterListener();
    /**
    *  Set char mod.
    * @param charMod
    */
    void setCharactermodel(CharacterModel charMod);
}
