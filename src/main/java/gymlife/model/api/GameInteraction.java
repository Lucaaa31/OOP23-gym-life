package gymlife.model.api;

/**
 * An interface for Interactions in the game.
 * @param <T> Type of the game element on which the interaction has to happen.
 * @param <R> Type of the eventual input Object required by the interaction.
 */
public interface GameInteraction<T, R> {
    /**
     * Method to actually do the interaction. When implemented it should call a method of gameElementToChange.
     * @param gameElementToChange the Game element on which to do the interaction.
     * @param input input for the interaction.
     */
    void interactWithInput(T gameElementToChange, R input);

    /**
     * Method to actually do the interaction. When implemented it should call a method of gameElementToChange.
     * @param gameElementToChange the Game element on which to do the interaction.
     */
    void interact(T gameElementToChange);
}
