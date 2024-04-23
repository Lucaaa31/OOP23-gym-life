package gymlife.view.bankgame;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * This class groups all the panels and shows them on screen.
 */
public final class BankGameView extends JFrame {
    private static final long serialVersionUID = -3972452455820596601L;

    /**
     * This method sets the dimensions of the plane image and the sky image,
     * moreover it sets the images' layering.
     */
    public BankGameView() {
        super("Plane Game");
        final Dimension screenDims = new Dimension(
                Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JLayeredPane layers = new JLayeredPane();

        final BankGameComponentImpl planeLayer = new AirplaneGameView();
        final BankGameComponentImpl skyLayer = new SkyGameView();

        planeLayer.setBounds(screenDims.width / 4, screenDims.height / 4,
                screenDims.height / 2,
                screenDims.height / 2);
        skyLayer.setBounds(0, 0, screenDims.width, screenDims.height);

        layers.add(skyLayer, JLayeredPane.DEFAULT_LAYER);
        layers.add(planeLayer, JLayeredPane.PALETTE_LAYER);

        this.setSize(screenDims.width / 3, screenDims.height / 3);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(layers);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                setLayersNewSize(skyLayer, planeLayer);
            }
        });
        this.setVisible(true);
    }

    private void setLayersNewSize(final BankGameComponentImpl skyLabel, final BankGameComponentImpl planeLabel) {
        final Dimension newSize = this.getSize();
        skyLabel.setBounds(0, 0, newSize.width, newSize.height);
        skyLabel.reload();
        planeLabel.setBounds(newSize.width / 4,
                newSize.height / 4, newSize.height / 2,
                newSize.height / 2);
        planeLabel.reload();
    }

    /**
     * This method closes the window of the game and frees the memory.
     */
    public void destroy() {
        this.dispose();
    }
}
