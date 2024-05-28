package gymlife.utility;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private String filename;
    private Clip clip;
    private AudioInputStream audioStream;
    private long clipTimePosition;

    public MusicPlayer(String filename) {
        this.filename = filename;
    }

    public void open() {
        try {
            File file = new File(filename);
            audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    public void resume() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
        if (audioStream != null) {
            try {
                audioStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer("src/main/resources/HWR-Zyzz.wav");
        player.open();
        player.play();

        // Use Thread.sleep or some other mechanism to pause/resume as needed
        // For example, to pause after 5 seconds and resume after another 5 seconds:
        try {
            Thread.sleep(100000);
            player.pause();
            Thread.sleep(5000);
            player.resume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
