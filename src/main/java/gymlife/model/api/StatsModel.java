package gymlife.model.api;
/**
*  Keylistener responsable for managing movement inputs.
*/
public interface StatsModel {
    int getMass();
    int getStamina();
    int getHumor();
    boolean incMass();
    boolean incStamina();
    boolean inchumor();
}
