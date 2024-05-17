package gymlife.view.api;

import javax.swing.JPanel;
import java.io.Serial;

/**
 * Abstract class that extends JPanel, adding methods for resizing the components inside.
 */
public abstract class GamePanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1052956620209509463L;
    /**
     * Method to resize the components inside the GamePanel.
     */
    public abstract void resizeComponents();

    /**
     * Method to return a representative name for the classes that extend this.
     * @return a string representing said name.
     */
    public abstract String getPanelName();
}
