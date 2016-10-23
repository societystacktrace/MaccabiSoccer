package com.ysoccer.android.ysdemo.match;

import com.ysoccer.android.framework.impl.GLGame;
import com.ysoccer.android.framework.math.Emath;

public class AiStateThrowingIn extends AiState {

    public AiStateThrowingIn(Ai ai) {
        super(ai);
        id = AiFsm.STATE_THROWING_IN;
    }

    @Override
    void doActions() {
        super.doActions();
        if (timer > 1.5f * GLGame.VIRTUAL_REFRATE) {
            ai.x0 = player.team.side;
        }
        ai.fire10 = Emath.isIn(timer, 2.0f * GLGame.VIRTUAL_REFRATE, 2.31f * GLGame.VIRTUAL_REFRATE);
    }

    @Override
    State checkConditions() {
        PlayerState playerState = player.fsm.getState();
        if (playerState.checkId(PlayerFsm.STATE_THROW_IN_ANGLE)) {
            return null;
        }
        if (playerState.checkId(PlayerFsm.STATE_THROW_IN_SPEED)) {
            return null;
        }

        return ai.fsm.stateIdle;
    }

}