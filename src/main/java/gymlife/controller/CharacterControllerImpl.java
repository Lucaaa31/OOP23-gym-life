package gymlife.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gymlife.controller.CharacterControllerImpl;
import gymlife.controller.api.CharacterController;
import gymlife.model.CharacterModelImpl;
import gymlife.model.api.CharacterModel;
import gymlife.utility.Directions;

/**
 * 
 * Class responsible for managing Character movements .
 */

public class CharacterControllerImpl implements CharacterController {
    public CharacterModel charMod = new CharacterModelImpl();
    public CharacterControllerImpl(CharacterModel charMod) {
        this.charMod = charMod;
    }

    private final transient KeyListener characterListener = new KeyListener() {
        @Override
        public void keyTyped(final KeyEvent e) {
            charMod.setDir(Directions.GetDir(e.getKeyChar()));
        }

        @Override
        public void keyPressed(final KeyEvent e) {
        }

        @Override
        public void keyReleased(final KeyEvent e) {
        }
    };

    @Override
    public KeyListener getChaListener() {
        return characterListener;
    }
}
