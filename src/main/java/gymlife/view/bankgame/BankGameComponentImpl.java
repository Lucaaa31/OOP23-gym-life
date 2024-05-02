package gymlife.view.bankgame;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * This abstract class makes the images resizeable everytime that the frame changes.
 */
public abstract class BankGameComponentImpl extends BGLabelView {

    private final Image icon;
    private static final long serialVersionUID = -8559784539802201917L;

    /**
     * This constructor initializes the icon field.
     * 
     * @param imagePath It's the path of the image.
     */
    protected BankGameComponentImpl(final String imagePath) {
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
