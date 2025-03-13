/**
 * File name: GameController.java
 * Student Name: JOHN RYCCA BELCINA
 * Student Number: 041128039
 * Course: CST 8221 – JAP, Lab Section: 300/303
 * Professor: Cormier, Daniel | Singh, Ramanjeet
 * Date: 2025-02-09
 * Compiler: IntelliJ IDEA
 * Purpose:
 * This project involves developing a software version of the Crazy Eights card game using a standard deck of playing cards.
 * The game will follow the first three variation rules listed on Wikipedia, along with additional custom rules.
 * Understanding these variations is essential for proper implementation.
 * This project is a mandatory requirement for passing the Algonquin CST 8221 – JAP course.
 * Copyright © 2025 John Rycca Belcina. All rights reserved.
 */
package model;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * The MusicManager is a singleton class responsible for managing the background music
 * playback in the application. It handles initializing, playing, pausing, and muting
 * the background music. The class ensures continuous playback by looping the audio file.
 */
public class MusicManager {
    private static MusicManager instance;
    private MediaPlayer mediaPlayer;
    private boolean isMuted = false;

    /**
     * Initializes the MusicManager by setting up and starting playback of the background music.
     * This constructor is private to enforce the singleton pattern.
     *
     * The music file is loaded from a predefined path within the application resources. A MediaPlayer
     * is created to handle the playback of the music. The following configurations are applied:
     * - The music is set to loop indefinitely by seeking to the start of the track when it finishes.
     * - The initial volume is set to 30% of the maximum.
     * - Playback begins automatically after initialization.
     */
    private MusicManager() {
            String musicPath = getClass().getResource("/com/zimgo/crazyeight/music/backgroundMusic.mp3").toExternalForm();
            Media media = new Media(musicPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO)); // Loop music
            mediaPlayer.setVolume(0.3);
            mediaPlayer.play();
    }

    /**
     * Retrieves the singleton instance of the MusicManager class. If the instance
     * does not already exist, it is created and initialized.
     *
     * @return the singleton instance of MusicManager
     */
    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    /**
     * Plays the background music if the MediaPlayer is initialized and the music is not muted.
     * This method checks the state of the MediaPlayer and the mute setting before initiating playback.
     */
    public void play() {
        if (mediaPlayer != null && !isMuted) {
            mediaPlayer.play();
        }
    }

    /**
     * Pauses the playback of the background music if the MediaPlayer is initialized.
     *
     * This method checks if the MediaPlayer object is not null and, if valid,
     * invokes the pause operation on the MediaPlayer to temporarily stop the music.
     * This action does not terminate the playback; music can be resumed later.
     */
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    /**
     * Toggles the mute state of the background music. When invoked, this method
     * switches the current mute state between muted and unmuted.
     *
     * If the background music is currently unmuted, it will be muted after calling this method.
     * Conversely, if the music is currently muted, it will be unmuted upon invocation.
     *
     * This method utilizes the `MediaPlayer` instance to apply the mute state and updates
     * the `isMuted` field accordingly.
     */
    public void toggleMute() {
        isMuted = !isMuted;
        mediaPlayer.setMute(isMuted);
    }

    /**
     * Checks whether the background music is currently muted.
     *
     * @return {@code true} if the background music is muted, otherwise {@code false}.
     */
    public boolean isMuted() {
        return isMuted;
    }
}
