package gymlife.utility;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

/**
 * The FontLoader class is responsible for loading a custom font and providing access to it.
 */
public final class FontLoader {
    private static final float DEFAULT_FONT_SIZE = 25f;
    private static Font customFont;

    static {
        loadFont();
    }

    /**
     * Loads the custom font from the specified font file.
     * If the font file is not found or an error occurs during loading, a default font will be used.
     */
    public static void loadFont() {
        try {
            final String fontPath = "/font/font.ttf";
            final InputStream is = FontLoader.class.getResourceAsStream(fontPath);
            if (is == null) {
                throw new IOException("Font resource not found: " + fontPath);
            }
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(DEFAULT_FONT_SIZE);
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
        if (customFont == null) {
            loadFont();
        }
        return customFont.deriveFont(fontSize);
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private FontLoader() {
    }
}
