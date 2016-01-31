package com.mygdx.breakout.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class Sounds {
    public static Sound bulletSound;
    public static Sound test;
    public static Music mainTheme;

    public static void load() {
        // sound asset loading
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        mainTheme = Gdx.audio.newMusic(Gdx.files.internal("music/ransom.wav"));
    }

    public static void playSound(Sound sound) {
        if(Settings.soundEnabled) {
            sound.play(Settings.volume);
        }
    }

    public static void playSound(Sound sound, float volume) {
        if(Settings.soundEnabled) {
            sound.play(volume);
        }
    }

    public static void loop(Music music) {
        music.setLooping(true);
        music.setVolume(0f);
        music.play();
    }

    public static void dispose() {
        bulletSound.dispose();
        test.dispose();
    }
}
