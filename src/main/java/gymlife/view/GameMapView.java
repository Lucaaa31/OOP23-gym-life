package gymlife.view;


import java.io.Serial;

import javax.swing.JPanel;

/**
 * JPanel that shows the current map on which teh character is. it shows all the cells of the map loaded.
 */
public final class GameMapView extends JPanel {

    @Serial
    private static final long serialVersionUID = -3544425405075144844L;
   // private final transient Controller controller;
    //private transient Map<Position, JPanel> cells;

    /**
     * Constructor for the GameMapView. it requires an external controller, given by the MainView.

     */
    public GameMapView() {
        //this.controller = controller;


        this.setVisible(true);
    }

    /**
     * Method to return the controller.
     * @return Returns the controller.
     */
    //public Controller getController() {
    //    return this.controller;
    //}

}
