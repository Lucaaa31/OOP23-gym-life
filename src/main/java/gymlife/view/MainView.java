package gymlife.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import gymlife.view.CharacterView;
import gymlife.controller.api.Controller;
import gymlife.controller.ControllerImpl;
import gymlife.utility.Constants;

public class MainView extends JFrame {
    
    final Controller controller = new ControllerImpl();
    final CharacterView charView = new CharacterView(controller);
    final JPanel mainPanel = new JPanel();

    public MainView() {
    }
    
    public void start() {
        this.setSize(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        charView.setSize(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        charView.setLayout(null);
        charView.setVisible(true);

        charView.setFocusable(true);
        charView.requestFocusInWindow();

        this.add((JPanel)charView);
        charView.setVisible(true);
        this.setVisible(true);
    }
}
