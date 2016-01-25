package com.mygdx.breakout.triggers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.breakout.managers.Destroyables;
import com.mygdx.breakout.screens.BreakoutScreen;

/**
 * Created by Dubforce on 1/24/2016.
 */
public class CutsceneTrigger implements Trigger {
    private BreakoutScreen game;
    private Body triggerBody;
    private Music audio;

    public CutsceneTrigger(BreakoutScreen game, Body triggerBody, String audioFile) {
        this.game = game;
        this.triggerBody = triggerBody;

        audio = Gdx.audio.newMusic(Gdx.files.internal("sounds/" + audioFile));
    }

    @Override
    public void trigger() {
        //game.setState(BreakoutScreen.State.CUSTSCNE);

        audio.play();

        audio.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                //game.setState(BreakoutScreen.State.PLAYING);
            }
        });

        Destroyables.destroy(triggerBody);
    }

    @Override
    public void dispose() {
        if(audio != null) {
            audio.dispose();
        }
    }
}
