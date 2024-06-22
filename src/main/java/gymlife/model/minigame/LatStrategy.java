package gymlife.model.minigame;

import gymlife.utility.minigame.MinigameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a lat machine minigame that implements the Minigame interface.
 */
public final class LatStrategy extends Minigame {
    private List<Integer> sequence;
    private int numberPressed;
    private int index;
    private long startMinigame;
    private final Random random = new Random();


    /**
     * Constructs a new LatMachineMinigame object and initializes the instance variables.
     */
    public LatStrategy() {
        super();
        this.index = 0;
        sequence = new ArrayList<>();
    }

    @Override
    public void notifyUserAction(final String buttonCode) {
        if (getMinigameState() == MinigameState.NOT_STARTED) {
            startMinigame = System.nanoTime();
            createRandomSequence();
            setMinigameState(MinigameState.PRESSED_START);
        } else {
            incrementNTimePressed();
            numberPressed = Integer.parseInt(buttonCode);
            setMinigameState(MinigameState.RUNNING);
            validatePress();
        }
    }

    @Override
    public void notifyUserAction() {
        notifyUserAction("0");
    }


    /**
     * Validates the user's press timing and updates the game state accordingly.
     */
    @Override
    public void validatePress() {
        if (conditionOfMinigame(numberPressed)) {
            index++;
            if (handleValidPress()) {
                createRandomSequence();
                index = 0;
                super.checkIfMinigameHasEnded(startMinigame);
            }
        } else {
            index = 0;
            handleInvalidPress();
            createRandomSequence();
        }
    }

    /**
     * Returns true if the user's pressed button is correct, false otherwise.
     *
     * @param buttonPressed the user's pressed button
     * @return true if the user's press timing is correct, false otherwise
     */
    public boolean conditionOfMinigame(final int buttonPressed) {
        return sequence.get(index) == buttonPressed;
    }

    /**
     * Creates a random sequence of numbers.
     */
    public void createRandomSequence() {
        sequence = new ArrayList<>();
        for (int i = 0; i < getDifficulty().getTouchForLift(); i++) {
            sequence.add(random.nextInt(4) + 1);
        }
    }

    /**
     * Returns the sequence of numbers.
     *
     * @return the sequence of numbers
     */
    @Override
    public List<String> getSequence() {
        return List.copyOf(sequence.stream()
                .map(Object::toString)
                .toList());
    }



}
