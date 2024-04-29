package gymlife.model.api;

/**
 * An interface for Interactions in the game.
 * @param <T> Type of the game element on which the interaction has to happen.
 */
public interface GameInteraction<T> {
    /**
     * Method to actually do the interaction. When implemented it should call a method of gameElementToChange.
     * @param gameElementToChange the Game element on which to do the interaction.
     */
    void interact(T gameElementToChange);
}
