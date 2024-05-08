package gymlife.view.minigame;

import javax.imageio.ImageIO;
import javax.swing.*;

import gymlife.controller.ControllerImpl;
import gymlife.controller.api.Controller;

import java.awt.*;

public class BenchView extends JPanel{
    private final Controller controller;
    private final JButton button = new JButton("Press me!");
    private ImageIcon image;
    private JLabel label = new JLabel();
    private TimerView timerView;

    public BenchView(final ControllerImpl controller){
        this.controller = controller;
        timerView = new TimerView(controller);
        this.setLayout(new BorderLayout());
        updateImage(controller.getState());
        label.setPreferredSize(new Dimension(1000, 700));
        button.addActionListener(e -> {
            controller.notifyButtonPressed();
            updateImage(controller.getState());
        });
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    private void updateImage(int state) {
        this.image = new ImageIcon("src/main/resources/images/Minigame/bench_press/sprite_" + state + ".png");
        this.label.setIcon(image);
        this.label.setLayout(new FlowLayout());
        setRandomPositionButton();
        this.add(button);
        this.label.add(timerView, BoxLayout.X_AXIS);
        this.add(label);
        this.repaint();
    }

    private void setRandomPositionButton(){
        int x = (int) (Math.random() * 250);
        int y = (int) (Math.random() * 300);
        this.button.setBounds(x, y, button.getWidth(), button.getHeight());
    }


}
