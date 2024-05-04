package gymlife.view.minigame;

import gymlife.controller.api.Controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.Set;

public class QuickTimeEvent {
    private
    Controller controller;
    private Timer timerQte;


    public QuickTimeEvent(final int reactionTime, final Set<Character> keys, final Controller controller) {
        this.controller = controller;
        this.timerQte = new Timer(reactionTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.notifyTimerExpired();
            }
        });
    }

    public void start() {
        timerQte.start();
    }

}
