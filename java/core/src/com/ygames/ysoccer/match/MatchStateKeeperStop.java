package com.ygames.ysoccer.match;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ygames.ysoccer.framework.GLGame;

import static com.ygames.ysoccer.match.PlayerFsm.Id.STATE_REACH_TARGET;
import static com.ygames.ysoccer.match.PlayerFsm.Id.STATE_STAND_RUN;

class MatchStateKeeperStop extends MatchState {

    private Player keeper;
    private Team keeperTeam;
    private Team opponentTeam;

    MatchStateKeeperStop(MatchFsm fsm) {
        super(fsm);
        id = MatchFsm.STATE_KEEPER_STOP;

        displayControlledPlayer = true;
        displayBallOwner = true;
        displayTime = true;
        displayWindVane = true;
        displayRadar = true;
    }

    @Override
    void entryActions() {
        super.entryActions();

        keeper = match.ball.holder;
        keeperTeam = match.team[keeper.team.index];
        opponentTeam = match.team[1 - keeper.team.index];

        match.stats[opponentTeam.index].overallShots += 1;
        match.stats[opponentTeam.index].centeredShots += 1;

        keeperTeam.assignAutomaticInputDevices(keeper);
        opponentTeam.assignAutomaticInputDevices(null);

        keeperTeam.setPlayersState(STATE_REACH_TARGET, keeper);
        opponentTeam.setPlayersState(STATE_REACH_TARGET, null);

        keeperTeam.updateTactics(true);
        opponentTeam.updateTactics(true);
    }

    @Override
    void doActions(float deltaTime) {
        super.doActions(deltaTime);

        float timeLeft = deltaTime;

        while (timeLeft > GLGame.SUBFRAME_DURATION) {

            if (match.subframe % GLGame.SUBFRAMES == 0) {
                match.updateAi();
            }

            match.updateBall();
            match.updatePlayers(true);

            if ((match.subframe % GLGame.VIRTUAL_REFRESH_RATE) == 0) {
                match.ball.updatePrediction();
            }

            match.nextSubframe();

            match.save();

            matchRenderer.updateCameraX(ActionCamera.CF_NONE, ActionCamera.CS_NORMAL);
            matchRenderer.updateCameraY(ActionCamera.CF_BALL, ActionCamera.CS_NORMAL);

            timeLeft -= GLGame.SUBFRAME_DURATION;
        }
    }

    @Override
    void checkConditions() {
        if (match.ball.holder == null) {
            keeperTeam.setPlayersState(STATE_STAND_RUN, keeper);
            opponentTeam.setPlayersState(STATE_STAND_RUN, null);
            fsm.pushAction(MatchFsm.ActionType.NEW_FOREGROUND, MatchFsm.STATE_MAIN);
            return;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            quitMatch();
            return;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            replay();
            return;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            fsm.pushAction(MatchFsm.ActionType.HOLD_FOREGROUND, MatchFsm.STATE_PAUSE);
            return;
        }
    }
}
