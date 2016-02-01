package com.mygdx.breakout.pickups;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.JumpComponent;
import com.mygdx.breakout.components.TextureComponent;
import com.mygdx.breakout.managers.Destroyables;
import com.mygdx.breakout.managers.Pickups;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dubforce on 1/31/2016.
 */
public class JumpRefresher extends Pickup {
    private static int DELAY = 3;

    private ComponentMapper<JumpComponent> jm;
    private ComponentMapper<BodyComponent> bm;

    public JumpRefresher() {
        jm = ComponentMapper.getFor(JumpComponent.class);
        bm = ComponentMapper.getFor(BodyComponent.class);
    }

    @Override
    public void pickup(Entity target, final Entity self) {
        JumpComponent jc = jm.get(target);
        BodyComponent bc = bm.get(self);

        if(jc != null) {
            jc.canDoubleJump = true;
            Pickups.remove(self);

            if(bc != null) {
                //Destroyables.destroy(bc.body);
                bc.body.setAwake(false);
                final TextureComponent tc = (TextureComponent)self.remove(TextureComponent.class);

                ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        refresh(self, tc);
                    }
                };

                service.schedule(task, DELAY, TimeUnit.SECONDS);
            }


        }
    }

    private void refresh(Entity self, TextureComponent tc) {
        BodyComponent bc = bm.get(self);

        if(bc != null) {
            bc.body.setAwake(true);
            self.add(tc);
            Pickups.put(self, this);
        }
    }
}
