package gymlife.view.bankgame;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serial;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabelView extends JLabel implements BankGameComponent {
    @Serial
    //TODO: Va modificato
    private static final long serialVersionUID = 1L;
    private final Image icon;

    protected ImageLabelView(final String imagePath) {
        this.icon = new ImageIcon(ClassLoader.getSystemResource(imagePath)).getImage();
        this.setIcon(new ImageIcon(icon));
    }

    @Override
    public final void reload() {
        final Rectangle bounds = this.getBounds();
        final Image scaledImage = this.icon.getScaledInstance(bounds.width, bounds.height, Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(scaledImage));
    }
}