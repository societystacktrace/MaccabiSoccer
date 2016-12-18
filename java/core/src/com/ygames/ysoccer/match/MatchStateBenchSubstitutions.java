package com.ygames.ysoccer.match;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ygames.ysoccer.framework.GLGame;
import com.ygames.ysoccer.math.Emath;

import static com.ygames.ysoccer.match.ActionCamera.CF_TARGET;
import static com.ygames.ysoccer.match.ActionCamera.CS_FAST;
import static com.ygames.ysoccer.match.Coach.Status.LOOK_BENCH;
import static com.ygames.ysoccer.match.Const.TEAM_SIZE;
import static com.ygames.ysoccer.match.MatchFsm.ActionType.NEW_FOREGROUND;
import static com.ygames.ysoccer.match.MatchFsm.STATE_BENCH_EXIT;
import static com.ygames.ysoccer.match.MatchFsm.STATE_BENCH_FORMATION;
import static com.ygames.ysoccer.match.PlayerFsm.STATE_BENCH_OUT;
import static com.ygames.ysoccer.match.PlayerFsm.STATE_BENCH_SITTING;
import static com.ygames.ysoccer.match.PlayerFsm.STATE_BENCH_STANDING;
import static com.ygames.ysoccer.match.PlayerFsm.STATE_OUTSIDE;

class MatchStateBenchSubstitutions extends MatchState {

    MatchStateBenchSubstitutions(MatchFsm fsm) {
        super(fsm);
        id = MatchFsm.STATE_BENCH_SUBSTITUTIONS;
    }

    @Override
    void entryActions() {
        super.entryActions();
        displayBenchPlayers = true;
    }

    @Override
    void doActions(float deltaTime) {
        super.doActions(deltaTime);

        float timeLeft = deltaTime;
        while (timeLeft >= GLGame.SUBFRAME_DURATION) {

            match.updateBall();
            match.ball.inFieldKeep();

            match.updatePlayers(true);

            match.updateCoaches();

            match.nextSubframe();

            match.save();

            matchRenderer.updateCameraX(CF_TARGET, CS_FAST, fsm.benchStatus.targetX, false);
            matchRenderer.updateCameraY(CF_TARGET, CS_FAST, fsm.benchStatus.targetY);

            timeLeft -= GLGame.SUBFRAME_DURATION;
        }

        // move selection
        if (fsm.benchStatus.inputDevice.yMoved()) {

            // if remaining substitutions
            if (fsm.benchStatus.team.substitutionsCount < match.settings.substitutions) {
                fsm.benchStatus.selectedPosition = Emath.rotate(fsm.benchStatus.selectedPosition, -1, match.settings.benchSize - 1, fsm.benchStatus.inputDevice.y1);
            }

            // reset positions
            for (int i = 0; i < match.settings.benchSize; i++) {
                Player player = fsm.benchStatus.team.lineup.get(TEAM_SIZE + i);
                if (!player.fsm.getState().checkId(STATE_OUTSIDE)) {
                    player.fsm.setState(STATE_BENCH_SITTING);
                }
            }

            // move selected player
            if (fsm.benchStatus.selectedPosition != -1) {
                Player player = fsm.benchStatus.team.lineup.get(TEAM_SIZE + fsm.benchStatus.selectedPosition);
                if (!player.fsm.getState().checkId(STATE_OUTSIDE)) {
                    // coach calls player
                    Coach coach = fsm.benchStatus.team.coach;
                    coach.status = LOOK_BENCH;
                    coach.timer = 250;

                    player.fsm.setState(STATE_BENCH_STANDING);
                }
            }
        }
    }

    @Override
    void checkConditions() {

        if (fsm.benchStatus.inputDevice.fire1Down()) {
            if (fsm.benchStatus.selectedPosition == -1) {
                fsm.pushAction(NEW_FOREGROUND, STATE_BENCH_FORMATION);
                return;
            } else {
                // if no previous selection
                if (fsm.benchStatus.substPosition == -1) {

                    // out the player for substitution
                    Player player = fsm.benchStatus.team.lineup.get(TEAM_SIZE + fsm.benchStatus.selectedPosition);

                    if (!player.fsm.getState().checkId(STATE_OUTSIDE)) {

                        player.fsm.setState(STATE_BENCH_OUT);

                        fsm.benchStatus.substPosition = TEAM_SIZE + fsm.benchStatus.selectedPosition;
                        fsm.benchStatus.selectedPosition = fsm.benchStatus.team.nearestBenchPlayerByRole(player.role);

                        fsm.pushAction(NEW_FOREGROUND, STATE_BENCH_FORMATION);
                        return;
                    }
                }
            }
        }

        if (fsm.benchStatus.inputDevice.xReleased() || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            fsm.pushAction(NEW_FOREGROUND, STATE_BENCH_EXIT);
            return;
        }
    }
}