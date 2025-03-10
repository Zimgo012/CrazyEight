package model;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicManager {
    private static MusicManager instance;
    private MediaPlayer mediaPlayer;
    private boolean isMuted = false;

    private MusicManager() {
            String musicPath = getClass().getResource("/com/zimgo/crazyeight/music/backgroundMusic.mp3").toExternalForm();
            Media media = new Media(musicPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO)); // Loop music
            mediaPlayer.setVolume(0.3);
            mediaPlayer.play();
    }

    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    public void play() {
        if (mediaPlayer != null && !isMuted) {
            mediaPlayer.play();
        }
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void toggleMute() {
        isMuted = !isMuted;
        mediaPlayer.setMute(isMuted);
    }

    public boolean isMuted() {
        return isMuted;
    }
}
