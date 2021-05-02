package graphic;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer/* extends Thread*/ {

    private Clip clip;
    private AudioInputStream audioInputStream;
    private String filePath;

    public MusicPlayer(String filePath) {
        this.filePath = filePath;
        //super.start();
        try {
            play(filePath);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   /* @Override
    public void run() {
        try {
            play(filePath);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    private void play(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream
                = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        clip = AudioSystem.getClip();

        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        /*while (true) {

        }*/

    }

    public void stopMusic() {
        clip.stop();
        //super.stop();
    }
}
