package com.ygames.ysoccer.math;

public class Emath {

    private static float TO_RADIANS = (float) Math.PI / 180.0f;
    private static float TO_DEGREES = 180.0f / (float) Math.PI;

    public static boolean isIn(float v, float a, float b) {
        return (b > a) ? (a <= v) && (v <= b) : (b <= v) && (v <= a);
    }

    public static int bound(int value, int valueMin, int valueMax) {
        return Math.min(Math.max(value, valueMin), valueMax);
    }

    public static float aTan2(float y, float x) {
        return (float) (Math.atan2(y, x) * TO_DEGREES);
    }

    public static float cos(float a) {
        return (float) Math.cos(a * TO_RADIANS);
    }

    public static float sin(float a) {
        return (float) Math.sin(a * TO_RADIANS);
    }

    public static float hypo(float diffX, float diffY) {
        return (float) Math.sqrt(diffX * diffX + diffY * diffY);
    }

    public static float dist(float x1, float y1, float x2, float y2) {
        return hypo(x2 - x1, y2 - y1);
    }

    public static float angle(float x1, float y1, float x2, float y2) {
        return aTan2(y2 - y1, x2 - x1);
    }

    public static float angleDiff(float a1, float a2) {
        float d = Math.abs(a2 - a1) % 360;
        return d > 180 ? 360 - d : d;
    }

    public static int rotate(int value, int low, int high, int direction) {
        return ((value - low + (high - low + 1) + direction) % (high - low + 1)) + low;
    }

    public static <T extends Enum<T>> int rotate(Enum<T> value, Enum<T> low, Enum<T> high, int direction) {
        return ((value.ordinal() - low.ordinal() + (high.ordinal() - low.ordinal() + 1) + direction) % (high.ordinal() - low.ordinal() + 1)) + low.ordinal();
    }

    public static <T extends Enum<T>> T rotate(Enum<T> value, Class<T> c, int direction) {
        int values = c.getEnumConstants().length;
        return c.getEnumConstants()[(value.ordinal() + (values) + direction) % values];
    }

    public static int slide(int value, int low, int high, int step) {
        return Math.min(Math.max(value + step, low), high);
    }

    public static int sgn(float value) {
        return value > 0 ? 1 : (value < 0 ? -1 : 0);
    }

    public static int floor(double value) {
        return (int) Math.floor(value);
    }

    // random integer between min and max (included)
    public static int rand(int min, int max) {
        return min + ((int) Math.floor((max - min + 1) * Math.random()));
    }

    public static <T extends Enum<T>> T randomPick(T... element) {
        return element[rand(0, element.length - 1)];
    }

    public static <T extends Enum<T>> T randomPick(Class<T> c) {
        int values = c.getEnumConstants().length;
        return c.getEnumConstants()[Emath.rand(0, values - 1)];
    }

    public static float roundBy(float value, float step) {
        return step * Math.round(value / step);
    }
}