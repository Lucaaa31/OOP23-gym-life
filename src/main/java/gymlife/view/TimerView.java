package gymlife.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gymlife.model.TimerImpl;


public class TimerView extends JPanel {

    private static JLabel label1 = new JLabel();
    private static JLabel label2 = new JLabel();
    private static JLabel label3 = new JLabel();
    private static JLabel label4 = new JLabel();
    private static JLabel label5 = new JLabel();



    public TimerView(final int height){

        int n = 10;

        setBackground(Color.PINK);

        setLayout(new GridLayout(1, 5));

        label1.setFont(new Font("Calibri", Font.ROMAN_BASELINE, height/n));
        label2.setFont(new Font("Calibri", Font.ROMAN_BASELINE, height/n));
        label3.setFont(new Font("Calibri", Font.ROMAN_BASELINE, height/9));
        label4.setFont(new Font("Calibri", Font.ROMAN_BASELINE, height/n));
        label5.setFont(new Font("Calibri", Font.ROMAN_BASELINE, height/n));
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        setVisible(true);
    }

    public static void update(final int firstDigit, final int secondDigit, final int thirdDigit, final int forthDigit ){
        label1.setText(Integer.toString(firstDigit));
        label2.setText(Integer.toString(secondDigit));
        label4.setText(Integer.toString(thirdDigit));
        label5.setText(Integer.toString(forthDigit));
    }

    
}
