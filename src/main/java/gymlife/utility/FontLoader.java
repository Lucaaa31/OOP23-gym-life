package gymlife.utility;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/**
 * The FontLoader class is responsible for loading a custom font and providing access to it.
 */
public final class FontLoader {
    private static final float DEFAULT_FONT_SIZE = 25f;
    private static Font customFont;
    /**
     * Loads the custom font from the specified font file.
     * If the font file is not found or an error occurs during loading, a default font will be used.
     */
    public static void loadFont() {
        try {
            final String fontPath = "src"
                    + File.separator
                    + "main" + File.separator
                    + "resources" + File.separator
                    + "font"   + File.separator
                    + "font.ttf";
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
                    .deriveFont(DEFAULT_FONT_SIZE);
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            customFont = new Font("Arial", Font.BOLD, (int) DEFAULT_FONT_SIZE);
        }
    }
    /**
     * Returns the custom font with the specified font size.
     *
     * @param fontSize the size of the font
     * @return the custom font with the specified font size
     */
    public static Font getCustomFont(final float fontSize) {
        return customFont.deriveFont((float) fontSize);
    }
    /**
     * Private Constructor the custom font with the default font size.
     */
    private FontLoader() {
    }
}
