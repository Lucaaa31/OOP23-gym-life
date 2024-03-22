package gymlife.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gymlife.controller.CharacterController;
import gymlife.model.CharacterModel;
import gymlife.utility.Directions;

/**
 * 
 * Class responsible for managing Character movements .
 */

public class CharacterController {
    public CharacterModel charMod = new CharacterModel();
    public static final long serialVersionUID = 4328743;

    public CharacterController(CharacterModel charMod) {
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

    public KeyListener getChaListener() {
        return characterListener;
    }
}
