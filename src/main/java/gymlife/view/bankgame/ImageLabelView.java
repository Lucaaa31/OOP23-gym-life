package gymlife.view.bankgame;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A custom JLabel component for displaying an image.
 */
public final class ImageLabelView extends JLabel implements BankGameComponent {
    private final Image icon;
    private static final long serialVersionUID = -5097068949874278343L;

    /**
     * Constructs an ImageLabelView with the specified image path.
     *
     * @param imagePath The path to the image file.
     */
    public ImageLabelView(final String imagePath) {
        this.icon = new ImageIcon(ClassLoader.getSystemResource(imagePath)).getImage();
        this.setIcon(new ImageIcon(icon));
    }

    @Override
    public void reload() {
        final Rectangle bounds = this.getBounds();
        final Image scaledImage = this.icon.getScaledInstance(bounds.width, bounds.height, Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(scaledImage));
    }
}
