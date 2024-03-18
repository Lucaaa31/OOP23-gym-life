package gymlife.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import gymlife.model.TimerImpl;

public class TimerView extends JPanel {

    public TimerView(final int widht, final int height ){
        JPanel panelTimer = new JPanel();
        panelTimer.setBackground(Color.BLACK);
        panelTimer.setPreferredSize(new Dimension(widht/5, height/5));

        TimerImpl logic = new TimerImpl(10000);
        logic.start();

        while (true) {
            System.out.print(logic.getFirstDigit());
            System.out.print(logic.getSecondDigit());
            System.out.print(logic.getThirdDigit());
            System.out.println(logic.getFirstDigit());

            

        }
        
    }
}
