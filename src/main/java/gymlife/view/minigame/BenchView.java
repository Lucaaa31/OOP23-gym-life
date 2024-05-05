package gymlife.view.minigame;

import javax.swing.JPanel;

import gymlife.controller.api.Controller;
import gymlife.model.Minigame.QuickTimeEvent;
import gymlife.utility.MinigameDifficulty;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BenchView extends JPanel{
    private final Controller controller;
    private QuickTimeEvent quickTimeEvent;

    public BenchView(final Controller controller){
        this.controller = controller;
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Key typed: " + e.getKeyChar());
                controller.notifyKeyPressed(e.getKeyChar());
            }
        });
        this.setVisible(true);
    }


}
