package gymlife.view.api;

import javax.swing.JPanel;

public abstract class GamePanel extends JPanel {

    public abstract void resizeComponents();

    public abstract String getPanelName();
}
