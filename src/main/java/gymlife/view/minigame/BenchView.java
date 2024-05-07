package gymlife.view.minigame;

import javax.imageio.ImageIO;
import javax.swing.*;

import gymlife.controller.api.Controller;

import java.awt.*;

public class BenchView extends JPanel{
    private final Controller controller;
    private final JButton button = new JButton("Press me!");
    private ImageIcon image;
    private JLabel label = new JLabel();

    public BenchView(final Controller controller){
        this.controller = controller;
        this.setLayout(new BorderLayout());
        updateImage(controller.getState());
        label.setPreferredSize(new Dimension(500, 300));
        //this.button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(e -> {
            controller.notifyButtonPressed();
            updateImage(controller.getState());
            setRandomPositionButton();
        });
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    private void updateImage(int state) {
        this.image = new ImageIcon("src/main/resources/images/Minigame/bench_press/sprite_" + state + ".png");
        this.label.setIcon(image);
        this.label.setLayout(new FlowLayout());
        this.label.add(button);
        this.add(label, BorderLayout.CENTER);
        this.repaint();
    }

    private void setRandomPositionButton(){
        int x = (int) (Math.random() * 250);
        int y = (int) (Math.random() * 300);
        this.button.setBounds(x, y, button.getWidth(), button.getHeight());
    }


}
