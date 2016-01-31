package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.mygdx.breakout.util.PickupType;

/**
 * Created by Dubforce on 1/31/2016.
 */
public class PickupFactory {
    public static Entity pickup(MapObject spawn) {
        MapProperties props = spawn.getProperties();
        PickupType pt = PickupType.valueOf(props.get("type").toString());

        if(pt == null) {
            return null;
        }

        return null;
    }
}
