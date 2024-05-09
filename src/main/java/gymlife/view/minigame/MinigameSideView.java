package gymlife.view.minigame;

import javax.swing.*;

import gymlife.controller.ControllerImpl;
import gymlife.controller.api.Controller;

import java.awt.*;

public class MinigameSideView extends JPanel{
    private TimerView timerView;
    

    public MinigameSideView(final ControllerImpl controller){
        timerView = new TimerView(controller);
        this.add(timerView);
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        timerView.setPreferredSize(new Dimension(1000, 500));
        this.setVisible(true);


    }


}
