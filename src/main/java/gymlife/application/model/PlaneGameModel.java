package gymlife.application.model;

import java.util.Random;

public class PlaneGameModel {
    private int treshold;
    private double multiplier;

    public PlaneGameModel() {
        multiplier = 1.0d;
        Random r = new Random();
        treshold = r.nextInt(5);
    }

    public boolean rangeControl() {
        return treshold == multiplier;
    }

}
