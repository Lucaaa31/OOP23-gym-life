package gymlife.view;

import java.awt.Image;
import java.io.Serial;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import gymlife.utility.Directions;

/**
 * The CharacterView class represents a panel that displays a character.
 * It extends the JLabel class. It uses an instance of DimensionGetter to get the dimensions for the character image.
 */
public class CharacterView extends JLabel {

    @Serial
    private static final long serialVersionUID = -3544425405375144844L;
    // An instance of DimensionGetter to get the dimensions for the character image
    private final DimensionGetter dimensionGetter;
    private int level = 1;
    private Directions dir = Directions.DOWN;
    /**
     * Constructs a CharacterView object with the specified DimensionGetter.
     * It sets the initial image for the character and sets the size of the JLabel to the cell dimension.
     * @param dimensionGetter the DimensionGetter to be used to get the dimensions for the character image
     */
    public CharacterView(final DimensionGetter dimensionGetter) {
        this.dimensionGetter = dimensionGetter;
        final String p = "images/character/level1_down.png";
        final ImageIcon img = new ImageIcon(new ImageIcon(ClassLoader.
                getSystemResource(p)).
                getImage().
                getScaledInstance(dimensionGetter.getCellDimension().width,
                        dimensionGetter.getCellDimension().height,
                        Image.SCALE_SMOOTH));
        this.setIcon(img);
        this.setSize(dimensionGetter.getCellDimension());
    }

    /**
     * Changes the image of the character based on the level and direction.
     * @param level the level of the character
     * @param dir the direction of the character
     */
    public void changeImage(final int level, final Directions dir) {
        this.level = level;
        this.dir = dir;
        final ImageIcon img = new ImageIcon(new ImageIcon(ClassLoader.
                getSystemResource("images/character/level"
                        + level
                        + "_"
                        + dir.toString().toLowerCase()
                        + ".png"))
                .getImage()
                .getScaledInstance(dimensionGetter.getCellDimension().width, dimensionGetter.getCellDimension().height,
                        Image.SCALE_SMOOTH));
        this.setIcon(img);
    }

    /**
     * Resizes the image of the character to the cell dimension.
     */
    public void resizeImage() {
        final ImageIcon img = new ImageIcon(new ImageIcon(ClassLoader.
                getSystemResource("images/character/level"
                        + this.level
                        + "_"
                        + this.dir.toString().toLowerCase()
                        + ".png"))
                .getImage()
                .getScaledInstance(dimensionGetter.getCellDimension().width, dimensionGetter.getCellDimension().height,
                        Image.SCALE_SMOOTH));
        this.setIcon(img);
        this.setSize(dimensionGetter.getCellDimension());
    }
}