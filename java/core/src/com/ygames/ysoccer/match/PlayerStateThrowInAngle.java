package com.ygames.ysoccer.match;

import com.ygames.ysoccer.framework.GLGame;
import com.ygames.ysoccer.math.Emath;

import static com.ygames.ysoccer.match.PlayerFsm.Id.STATE_THROW_IN_ANGLE;

class PlayerStateThrowInAngle extends PlayerState {

    private int animationCountdown;

    PlayerStateThrowInAngle(PlayerFsm fsm, Player player) {
        super(STATE_THROW_IN_ANGLE, fsm, player);
    }

    @Override
    void entryActions() {
        super.entryActions();

        player.a = 45 * (2 * ball.xSide + 2);
        player.x = ball.xSide * (Const.TOUCH_LINE + 6);
        player.fmx = 2 * ball.xSide + 2;
        player.fmy = 8;
        ball.setOwner(player);
        animationCountdown = GLGame.SUBFRAMES_PER_SECOND;
    }

    @Override
    void doActions() {
        super.doActions();
        if (animationCountdown > 0) {
            animationCountdown--;
        }

        int x = player.inputDevice.x1;
        int y = player.inputDevice.y1;

        // prevent throwing outside
        if (x != ball.xSide) {
            boolean value = (x != 0) || (y != 0);
            int angle = Math.round(Emath.aTan2(y, x));

            if (value) {
                // stop animation
                if (angle != player.a) {
                    animationCountdown = GLGame.SUBFRAMES_PER_SECOND;
                }
                player.a = angle;
                player.fmx = (Math.round(angle / 45.0f) + 8) % 8;
            }
        }

        // do animation
        int a = 0;
        if (animationCountdown == 0) {
            a = ((timer % 200) > 100) ? 1 : 0;
        }

        player.fmy = 8 + a;
        ball.x = player.x + 6 * Emath.cos(player.a) * a;
        ball.y = player.y + 6 * Emath.sin(player.a) * a;
        ball.z = Const.PLAYER_H + 2 + 0 * a;
    }

    @Override
    State checkConditions() {
        if (player.inputDevice.fire1Down()) {
            return player.fsm.stateThrowInSpeed;
        }
        return null;
    }
}
