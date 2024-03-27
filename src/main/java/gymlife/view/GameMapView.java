package gymlife.view;

import javax.swing.JFrame;
import gymlife.controller.Controller;

public class GameMapView extends JFrame{

    private Controller controller;

    public GameMapView(){
        this.controller = new Controller("houseMap.txt");
    }

}