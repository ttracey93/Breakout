package com.mygdx.breakout.managers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.triggers.Trigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dubforce on 1/24/2016.
 */
public class Triggers {
    private static Map<Body, Trigger> triggerMap;
    private static List<Body> triggerables;
    private static World world;

    public static void load() {
        triggerMap = new HashMap<Body, Trigger>();
        triggerables = new ArrayList<Body>();
    }

    public static void addTrigger(Body body, Trigger trigger) {
        triggerMap.put(body, trigger);
    }

    public static void activate(Body body) {
        Trigger trigger = triggerMap.get(body);

        if(trigger != null) {
            trigger.trigger();
        }
    }

    public static void activateWhenPossible(Body body) {
        triggerables.add(body);
    }

    public static void update() {
        for(Body body : triggerables) {
            Trigger trigger = triggerMap.get(body);

            if(trigger != null) {
                trigger.trigger();
            }
        }

        triggerables.clear();
    }

    public static void dispose() {
        for(Map.Entry<Body, Trigger> entry : triggerMap.entrySet()) {
            entry.getValue().dispose();
        }

        triggerMap.clear();
    }

    public static void setWorld(World world) {
        Triggers.world = world;
    }
}