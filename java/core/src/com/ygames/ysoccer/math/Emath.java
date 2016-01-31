package com.ygames.ysoccer.math;

public class Emath {

    public static float TO_DEGREES = 180.0f / (float) Math.PI;

    static public float aTan2(float y, float x) {
        return (float) (Math.atan2(y, x) * TO_DEGREES);
    }

    static public float hypo(float diffX, float diffY) {
        return (float) Math.sqrt(diffX * diffX + diffY * diffY);
    }

    public static int rotate(int value, int low, int high, int direction) {
        return ((value - low + (high - low + 1) + direction) % (high - low + 1)) + low;
    }

    public static int slide(int value, int low, int high, int step) {
        return Math.min(Math.max(value + step, low), high);
    }

    public static int floor(double value) {
        return (int) Math.floor(value);
    }
}