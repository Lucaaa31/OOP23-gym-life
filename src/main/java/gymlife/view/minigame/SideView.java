package gymlife.view;

import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JPanel;

import gymlife.controller.api.Controller;

public class SideView extends JPanel{
    private Controller controller;
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;
    private TextArea textArea = new TextArea("getToolTipText()");
    

    public SideView(final Controller controller){
        this.controller = controller;
        this.add(textArea);
    }

    public void start(){

    }
}
