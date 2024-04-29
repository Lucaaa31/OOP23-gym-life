package gymlife.model.api;


public interface GameInteraction <T, R> {

    void interact(T gameElementToChange, R input);

}
