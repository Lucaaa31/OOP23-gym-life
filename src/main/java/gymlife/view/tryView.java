package gymlife.view;

import javax.swing.*;
import java.awt.*;

public class tryView extends JPanel {
    public static final long serialVersionUID = 4328743;
    private final JPanel mainPanel = new JPanel();


    public JPanel getPanel() {
        this.setSize(100, 100);
        this.setBackground(Color.BLUE);
        return this;
    }
}
