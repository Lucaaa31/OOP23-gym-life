package gymlife.utility;
import java.util.Optional;

public enum PressedKeys {
    /** 
     * Direction UP.
    */
    UP('w'),
    /** 
     * Direction RIGHT.
    */
    RIGHT('d'),
    /** 
     * Direction Left.
    */
    LEFT('a'),
    /** 
     * Direction DOWN.
    */
    DOWN('s'),
    /** 
     * Interaction.
    */
    INTERACTION('e'),
    /** 
    * Interaction.
    */
    JUMP('j');


    private final char key; 

    PressedKeys(char key){
        this.key = key;
    }
    
    public static Optional<PressedKeys> getCommand(final char key) {
        for (final PressedKeys elem : PressedKeys.values()) {
            if (elem.key == key) {
                return Optional.of(elem);
            }
        }
        return Optional.empty();
    }
}
