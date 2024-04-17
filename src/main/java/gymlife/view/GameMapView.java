package gymlife.view;

import java.util.Map;

import javax.swing.*;
import gymlife.controller.Controller;
import gymlife.model.api.Pair;

public class GameMapView extends JFrame{

    private Controller controller;
    private Map<Pair<Integer,Integer>, JPanel> cells;
    private final int DIMENSIONS = 100;

    public GameMapView(){
        this.controller = new Controller();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.setVisible(true);
    }

}