package gymlife.view;


import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class MinigameViewImpl {

    public MinigameViewImpl(){

        /**
         * Creating the frame manually, but already exist in the full implementation
         */

        JFrame mainFrame = new JFrame("TEMPORARY");
        Dimension screenSeize = Toolkit.getDefaultToolkit().getScreenSize();
        Integer widht = (int)screenSeize.getWidth();
        Integer height = (int)screenSeize.getHeight();


        mainFrame.setSize(widht, height);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLUE); // Set background color for illustration
        panel1.setPreferredSize(new Dimension(widht - (widht/3), height)); 

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.RED); // Set background color for illustration
        panel2.setPreferredSize(new Dimension(widht/3, height)); 

        mainPanel.add(panel1);
        mainPanel.add(panel2);

        mainFrame.setContentPane(mainPanel);

        mainFrame.setSize(widht, height);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }



}
