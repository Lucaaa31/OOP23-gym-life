package gymlife.controller.api;

/**
 * Interface that handles key inputs from the keyboard to move the character.
 */
public interface Controller {
    /**
     * Moves the character up.
     */
    public void moveUp();

    /**
     * Moves the character down.
     */
    public void moveDown();

    /**
     * Moves the character to the right.
     */
    public void moveRight();

    /**
     * Moves the character to the left.
     */
    public void moveLeft();
}
