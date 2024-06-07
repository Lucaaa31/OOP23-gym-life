package gymlife.utility;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that represents a music player.
 * This music player can open, play, pause, resume, and close audio files.
 */
public class MusicPlayer {
    private static final Logger LOGGER = Logger.getLogger(MusicPlayer.class.getName());

    // The filename of the audio file.
    private final String filename;
    // The clip that will play the audio.
    private Clip clip;
    // The audio stream that will be played.
    private AudioInputStream audioStream;
    // The position in the audio stream where the clip is currently at.
    private long clipTimePosition;

    /**
     * Constructs a new MusicPlayer with a specified filename.
     *
     * @param filename The filename of the audio file.
     */
    public MusicPlayer(final String filename) {
        this.filename = filename;
    }

    /**
     * Opens the audio file and prepares it for playing.
     */
    public void open() {
        try {
            final File file = new File(filename);
            audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            LOGGER.log(Level.SEVERE, "Failed to open audio file: " + filename, e);
        }
    }

    /**
     * Starts playing the audio file from the beginning.
     */
    public void play() {
        if (clip != null) {
            clip.setMicrosecondPosition(0);
            clip.start();
        } else {
            LOGGER.log(Level.WARNING, "Clip is not initialized. Call open() before play().");
        }
    }

    /**
     * Pauses the audio file at the current position.
     */
    public void pause() {
        if (clip != null && clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
        } else {
            LOGGER.log(Level.WARNING, "Clip is not running. Cannot pause.");
        }
    }

    /**
     * Resumes playing the audio file from the position it was paused at.
     */
    public void resume() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
        } else {
            LOGGER.log(Level.WARNING, "Clip is already running or not initialized. Cannot resume.");
        }
    }

    /**
     * Closes the audio file and releases any system resources associated with it.
     */
    public void close() {
        if (clip != null) {
            clip.close();
        }
        if (audioStream != null) {
            try {
                audioStream.close();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to close audio stream", e);
            }
        }
    }
}
