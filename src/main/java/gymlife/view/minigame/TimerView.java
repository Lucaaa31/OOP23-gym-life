package gymlife.view.minigame;

import gymlife.controller.api.Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class TimerView extends JPanel {

    private static JLabel label1 = new JLabel("");
    private static JLabel label2 = new JLabel("");
    private static JLabel label3 = new JLabel(":");
    private static JLabel label4 = new JLabel("");
    private static JLabel label5 = new JLabel("");
    private Controller controller;

    private final Consumer<List<Integer>> labelUpdater = digits -> {
        label1.setText(Integer.toString(digits.get(0)));
        label2.setText(Integer.toString(digits.get(1)));
        label4.setText(Integer.toString(digits.get(2)));
        label5.setText(Integer.toString(digits.get(3)));
    };

    public TimerView(final int height, Controller controller){
        this.controller = controller;
        int n = 10;

        setBackground(Color.PINK);

        setLayout(new GridLayout(1, 5));

        Consumer<JLabel> styleLabel = label -> label.setFont(new Font("Calibri", Font.ROMAN_BASELINE, height / n));
        styleLabel.accept(label1);
        styleLabel.accept(label2);
        styleLabel.accept(label3);
        styleLabel.accept(label4);
        styleLabel.accept(label5);

        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        setVisible(true);


        //update();
    }



    public void update(){
        new Thread (() -> {
            while(!controller.getTime().isEmpty()){
                labelUpdater.accept(controller.getTime());
            }
        }).start();
    }



}