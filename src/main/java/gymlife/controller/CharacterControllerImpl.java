package gymlife.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gymlife.controller.api.CharacterController;
import gymlife.model.api.CharacterModel;
import gymlife.utility.Directions;

/**
 * 
 * Class responsible for managing Character movements.
 */
public class CharacterControllerImpl implements CharacterController {
    private CharacterModel charMod;
    /**
     * 
     * Class responsible for managing Character movements.
     * @param charMod
     */
    @Override
    public void setCharactermodel(final CharacterModel charMod) {
        this.charMod = charMod;
    }
    private final transient KeyListener characterListener = new KeyListener() {
        @Override
        public void keyTyped(final KeyEvent e) {
            charMod.setDir(Directions.getDir(e.getKeyChar()));
        }

        @Override
        public void keyPressed(final KeyEvent e) {
        }

        @Override
        public void keyReleased(final KeyEvent e) {
        }
    };
    /**
     * 
     * Method that return characterListener.
     * @return characterListener
     */
    @Override
    public KeyListener getCharacterListener() {
        return characterListener;
    }
}
