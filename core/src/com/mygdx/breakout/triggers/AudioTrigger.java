package com.mygdx.breakout.triggers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.breakout.managers.Destroyables;
import com.mygdx.breakout.managers.Sounds;

/**
 * Created by Dubforce on 1/24/2016.
 */
public class AudioTrigger implements Trigger {
    private Body triggerBody;
    private OccurenceLevel occurences;
    private Sound sound;

    public AudioTrigger(Body triggerBody, OccurenceLevel occurences, Sound sound) {
        this.triggerBody = triggerBody;
        this.occurences = occurences;
        this.sound = sound;
    }

    @Override
    public void trigger() {
        if(sound != null) {
            Sounds.playSound(sound);
        }

        if(occurences.equals(OccurenceLevel.ONCE)) {
            Destroyables.destroy(triggerBody);
        }
    }

    @Override
    public void dispose() {
        if(sound != null) {
            sound.dispose();
        }
    }
}