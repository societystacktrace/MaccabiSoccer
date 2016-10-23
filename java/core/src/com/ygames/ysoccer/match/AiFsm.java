package com.ygames.ysoccer.match;

import com.ygames.ysoccer.framework.Ai;

public class AiFsm extends Fsm {

    public static final int STATE_IDLE = 1;
    public static final int STATE_KICKING_OFF = 2;
    public static final int STATE_POSITIONING = 3;
    public static final int STATE_SEEKING = 4;
    public static final int STATE_DEFENDING = 5;
    public static final int STATE_ATTACKING = 6;
    public static final int STATE_PASSING = 7;
    public static final int STATE_KICKING = 8;
    public static final int STATE_GOAL_KICKING = 9;
    public static final int STATE_THROWING_IN = 10;
    public static final int STATE_CORNER_KICKING = 11;
    public static final int STATE_KEEPER_KICKING = 12;

    public AiFsm(Ai ai) {
    }
}