package gymlife.model.map.api;

/**
 * An interface for Interactions in the game.
 */
public interface GameInteraction {
    /**
     * Method to actually do the interaction. When implemented it should call a method of gameElementToChange.
     * @param gameElementToChange the Game element on which to do the interaction.
     */
    void interact(InteractionsManager gameElementToChange);
}
