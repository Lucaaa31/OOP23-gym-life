package gymlife.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * This class shows the image of an airplane.
 */
public final class AirplaneGameView {

    /**
     * This is the constructor of the Airplane class.
     */
    public AirplaneGameView() {
        final ImageIcon image;
        final JFrame frame;
        final JLabel displayField;
        frame = new JFrame("testo immagine esempio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        image = new ImageIcon(ClassLoader.getSystemResource("gymlife/airplane/airplane.png"));
        displayField = new JLabel(image);
        frame.add(displayField);
        final Dimension screenDims = new Dimension(
                Toolkit.getDefaultToolkit().getScreenSize().width / 3,
                Toolkit.getDefaultToolkit().getScreenSize().height / 3);
        frame.setSize(screenDims);
        frame.setMinimumSize(screenDims);

        frame.setVisible(true);
    }

}
