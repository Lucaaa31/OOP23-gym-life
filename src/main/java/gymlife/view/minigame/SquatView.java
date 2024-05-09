package gymlife.view.minigame;

import gymlife.controller.ControllerImpl;
import gymlife.controller.api.Controller;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SquatView extends JPanel implements KeyListener {
    final Controller controller;
    JButton squatButton = new JButton("Squat");

    public SquatView(final ControllerImpl controller){
        this.controller = controller;
        addKeyListener(this);
        this.add(squatButton);
        squatButton.addActionListener(e -> System.out.println("Squat button pressed"));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key typed: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyChar());
        controller.notifyKeyPressed(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
