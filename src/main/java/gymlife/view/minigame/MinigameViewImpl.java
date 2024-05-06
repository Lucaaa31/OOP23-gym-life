package gymlife.view.minigame;


import gymlife.controller.api.Controller;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class MinigameViewImpl{

    public MinigameViewImpl(Controller controller){

        /**
         * Creating the frame manually, but already exist in the full implementation
         */



        JFrame mainFrame = new JFrame("TEMPORARY");
        Dimension screenSeize = Toolkit.getDefaultToolkit().getScreenSize();
        int widht = 500;
        int height = 300;


        mainFrame.setSize(widht/2, height/2);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

//        JPanel panel1 = new JPanel();
//        panel1.setBackground(Color.BLUE);
//        panel1.setPreferredSize(new Dimension(widht - (widht/3), height));

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.RED);
        panel2.setPreferredSize(new Dimension(widht/3, height));

        BenchView benchView = new BenchView(controller);
        benchView.setPreferredSize(new Dimension(widht - (widht/3), height));

        mainPanel.add(benchView);
        mainPanel.add(panel2);

        mainFrame.setContentPane(mainPanel);

        mainFrame.setSize(widht, height);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        //TimerView panelTimer = new TimerView(height, controller);


       // panelTimer.setPreferredSize(new Dimension(widht/5, height/5));
        
        benchView.setLayout(new FlowLayout(FlowLayout.LEFT));
       // panelTimer.setVisible(true);
       // benchView.add(panelTimer);


        benchView.setVisible(true);
        //benchView.setBackground(Color.BLACK);
        benchView.setFocusable(true);

        benchView.setVisible(true);
        panel2.setVisible(true);
        mainPanel.setVisible(true);
        mainFrame.setVisible(true);



    }



}
