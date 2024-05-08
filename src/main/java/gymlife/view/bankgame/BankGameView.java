package gymlife.view.bankgame;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import gymlife.controller.api.Controller;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serial;

/**
 * This class groups all the panels and shows them on screen.
 */
public final class BankGameView extends JLayeredPane {
    @Serial
    private static final long serialVersionUID = -3972452455820596601L;

    private final TextLabelView numberLabel;
    private final static float START_MULTIPLIER = 1;
    private static boolean STARTED = false;
    private JTextField text_money;
    Font myFont = new Font("PLAIN", Font.PLAIN, 20);
    float moneyToPlay = 4;

    /**
     * This method sets the dimensions of the plane image and the sky image,
     * moreover it sets the images' layering.
     */
    public BankGameView(Controller controller) {
        numberLabel = new MultiplierGameView();
        final ImageLabelView planeLayer = new AirplaneGameView();
        final ImageLabelView skyLayer = new SkyGameView();
        final JButton button = new JButton();
        text_money = new JTextField();
        

        this.add(skyLayer, JLayeredPane.DEFAULT_LAYER);
        this.add(planeLayer, JLayeredPane.PALETTE_LAYER);
        this.add(numberLabel, JLayeredPane.MODAL_LAYER);
        this.add(button, JLayeredPane.MODAL_LAYER);
        this.add(text_money, JLayeredPane.MODAL_LAYER);

        numberLabel.setText(String.format("%.0fx", START_MULTIPLIER));

        
        text_money.setFont(myFont);
        //text_money.setEditable(false);
       text_money.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                e.consume();
            }
        }
       });


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                setLayersNewSize(skyLayer, planeLayer, numberLabel, button);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (STARTED == false) {
                    updateMulti(controller);
                    showsMulti(controller);
                    STARTED = true;
                } else if (STARTED == true) {
                    controller.controllerStopMultiplier();
                }

            }
        });

        this.setVisible(true);
    }


    public void showsMulti(Controller controller) {
        new Thread(() -> {
            while (controller.getMultiplier() != controller.getTreshold()) {
                float multiplier = controller.getMultiplier();
                multiplier = Math.round(multiplier * 1000.0f) / 1000.0f;
                if (numberLabel instanceof MultiplierGameView mpgv) {
                    mpgv.updateText(multiplier);
                }
            }
        }).start();
    }

    public void updateMulti(Controller controller) {
        new Thread(() -> {
            controller.startMultiplier(moneyToPlay);
        }).start();
    }

    private void setLayersNewSize(final ImageLabelView skyLabel, final ImageLabelView planeLabel,
            final TextLabelView numberLabel, final JButton button) {
        final Dimension newSize = this.getSize();
        skyLabel.setBounds(0, 0, newSize.width, newSize.height);
        skyLabel.reload();
        button.setBounds(newSize.width / 35,
                newSize.height / 2, newSize.height / 4,
                newSize.height / 11);
        planeLabel.setBounds(newSize.width / 4,
                newSize.height / 4, newSize.height / 2,
                newSize.height / 2);
        planeLabel.reload();
        numberLabel.setBounds(newSize.width / 3,
                newSize.height / 3, newSize.height / 1,
                newSize.height / 1);
        numberLabel.reload();
        text_money.setBounds(newSize.width / 3,
        newSize.height / 3, newSize.height / 12,
        newSize.height / 10);
    }
}
