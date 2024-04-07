package gymlife.view.bankgame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class BankGameComponentImpl extends JLabel implements BankGameComponent {
    private final Image icon;
    private static final long serialVersionUID = -8559784539802201917L;

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
