package gymlife.view.bankgame;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import gymlife.controller.BankGameController;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.Serial;

/**
 * This class groups all the panels and shows them on screen.
 */
public final class BankGameView extends JLayeredPane {
    @Serial
    private static final long serialVersionUID = -3972452455820596601L;

    final TextLabelView numberLabel;
    // float num;

    /**
     * This method sets the dimensions of the plane image and the sky image,
     * moreover it sets the images' layering.
     */
    public BankGameView(BankGameController controller) {
        numberLabel = new MultiplierGameView();
        final ImageLabelView planeLayer = new AirplaneGameView();
        final ImageLabelView skyLayer = new SkyGameView();
        final JButton button = new JButton();

        this.add(skyLayer, JLayeredPane.DEFAULT_LAYER);
        this.add(planeLayer, JLayeredPane.PALETTE_LAYER);
        this.add(numberLabel, JLayeredPane.MODAL_LAYER);
        this.add(button, JLayeredPane.MODAL_LAYER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                setLayersNewSize(skyLayer, planeLayer, numberLabel, button);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMulti(controller);
                showsMulti(controller);
            }
        });

        this.setVisible(true);
    }

    public void showsMulti(BankGameController controller) {
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

    public void updateMulti(BankGameController controller) {
        new Thread(() -> {
            controller.startMultiplier();
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
    }
}
