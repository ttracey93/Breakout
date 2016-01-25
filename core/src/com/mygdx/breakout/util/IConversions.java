package com.mygdx.breakout.util;

/**
 * Created by Dubforce on 1/23/2016.
 */
public interface IConversions {
    float PIXELS_PER_METER = 1/64f;
    float METERS_PER_PIXEL = 1/PIXELS_PER_METER;

    // shorthand
    float PPM = PIXELS_PER_METER;
    float MPP = METERS_PER_PIXEL;
}
