package gymlife.view;

import javax.swing.JPanel;

import gymlife.controller.api.Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BenchView extends JPanel{
    private Controller controller;
    private QuickTimeEvent quickTimeEvent;

    public BenchView(final Controller controller){
        this.controller = controller;
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                controller.notifyTimerExpired(e.);
            }
        });
    }

    public void start(){

    }
}
