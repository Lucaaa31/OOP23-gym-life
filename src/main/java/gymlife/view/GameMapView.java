package gymlife.view;

import java.util.Map;

import javax.swing.*;

import gymlife.controller.Controller;
import gymlife.model.api.Pair;

public class GameMapView extends JPanel {

    private final Controller controller;
    private Map<Pair<Integer, Integer>, JPanel> cells;

    public GameMapView() {
        this.controller = new Controller();


        this.setVisible(true);
    }

    public Map<Pair<Integer, Integer>, JPanel> getCells() {
        return cells;
    }

    public Controller getController() {
        return this.controller;
    }

}
