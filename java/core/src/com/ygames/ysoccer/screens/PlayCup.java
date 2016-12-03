package com.ygames.ysoccer.screens;

import com.ygames.ysoccer.competitions.Cup;
import com.ygames.ysoccer.framework.Assets;
import com.ygames.ysoccer.framework.Font;
import com.ygames.ysoccer.framework.GLGame;
import com.ygames.ysoccer.framework.GLScreen;
import com.ygames.ysoccer.gui.Button;
import com.ygames.ysoccer.gui.Label;
import com.ygames.ysoccer.gui.Widget;
import com.ygames.ysoccer.match.Match;
import com.ygames.ysoccer.match.Team;
import com.ygames.ysoccer.math.Emath;

import java.util.ArrayList;
import java.util.Collections;

import static com.ygames.ysoccer.match.Match.AWAY;
import static com.ygames.ysoccer.match.Match.HOME;

class PlayCup extends GLScreen {

    Cup cup;
    private int matches;
    private int offset;
    private ArrayList<Widget> resultWidgets;

    PlayCup(GLGame game) {
        super(game);

        cup = (Cup) game.competition;

        background = game.stateBackground;

        Widget w;

        w = new TitleBar(cup.getMenuTitle(), game.stateColor.body);
        widgets.add(w);

        matches = cup.calendarCurrent.size();
        offset = 0;
        if ((matches > 8) && (cup.currentMatch > 4)) {
            offset = Math.min(cup.currentMatch - 4, matches - 8);
        }

        ArrayList<Team> teamsList = (ArrayList<Team>) cup.teams.clone();
        Collections.sort(teamsList, new Team.CompareByStats());

        int dy = 100;
        if (matches < 8) {
            dy = dy + 64 * (8 - matches) / 2;
        }

        // calendar
        resultWidgets = new ArrayList<Widget>();
        for (int m = 0; m < matches; m++) {
            Match match = cup.calendarCurrent.get(m);

            w = new TeamButton(335, dy + 64 * m, cup.teams.get(match.teams[HOME]), Font.Align.RIGHT);
            resultWidgets.add(w);
            widgets.add(w);

            // result (home goals)
            w = new Label();
            w.setGeometry(640 - 45, dy + 64 * m, 30, 26);
            w.setText("", Font.Align.RIGHT, Assets.font10);
            if (match.ended) {
                w.setText(match.result[HOME]);
            }
            resultWidgets.add(w);
            widgets.add(w);

            w = new VersusLabel(dy + 64 * m, match);
            resultWidgets.add(w);
            widgets.add(w);

            // result (away goals)
            w = new Label();
            w.setGeometry(640 + 15, dy + 64 * m, 30, 26);
            w.setText("", Font.Align.LEFT, Assets.font10);
            if (match.ended) {
                w.setText(match.result[AWAY]);
            }
            resultWidgets.add(w);
            widgets.add(w);

            w = new TeamButton(705, dy + 64 * m, cup.teams.get(match.teams[AWAY]), Font.Align.LEFT);
            resultWidgets.add(w);
            widgets.add(w);

            // status
            w = new Label();
            w.setGeometry(game.gui.WIDTH / 2 - 360, dy + 26 + 64 * m, 720, 26);
            // TODO: use green charset
            w.setText(match.status, Font.Align.CENTER, Assets.font10);
            resultWidgets.add(w);
            widgets.add(w);
        }
        updateResultWidgets();

        // home team
        w = new Label();
        w.setGeometry(240, 618, 322, 36);
        w.setText(cup.getTeam(HOME).name, Font.Align.RIGHT, Assets.font14);
        widgets.add(w);

        Match match = cup.getMatch();

        // result (home goals)
        w = new Label();
        w.setGeometry(game.gui.WIDTH / 2 - 60, 618, 40, 36);
        w.setText("", Font.Align.RIGHT, Assets.font14);
        if (match.ended) {
            w.setText(match.result[HOME]);
        }
        widgets.add(w);

        // versus / -
        w = new Label();
        w.setGeometry(game.gui.WIDTH / 2 - 20, 618, 40, 36);
        w.setText("", Font.Align.CENTER, Assets.font14);
        if (match.ended) {
            w.setText("-");
        } else {
            w.setText(Assets.strings.get("ABBREVIATIONS.VERSUS"));
        }
        widgets.add(w);

        // result (away goals)
        w = new Label();
        w.setGeometry(game.gui.WIDTH / 2 + 20, 618, 40, 36);
        w.setText("", Font.Align.LEFT, Assets.font14);
        if (match.ended) {
            w.setText(match.result[AWAY]);
        }
        widgets.add(w);

        // away team
        w = new Label();
        w.setGeometry(720, 618, 322, 36);
        w.setText(cup.getTeam(AWAY).name, Font.Align.LEFT, Assets.font14);
        widgets.add(w);

        w = new ViewStatisticsButton();
        widgets.add(w);

        Widget exitButton = new ExitButton();
        widgets.add(exitButton);

        if (cup.isEnded()) {

            setSelectedWidget(exitButton);

        } else {

            if (matches > 8) {
                w = new ScrollButton(94, -1);
                widgets.add(w);

                w = new ScrollButton(548, +1);
                widgets.add(w);
            }

            if (match.ended) {
                w = new NextMatchButton();
                widgets.add(w);
                setSelectedWidget(w);
            } else {
                Widget playMatchButton = new PlayViewMatchButton();
                widgets.add(playMatchButton);

                Widget viewResultButton = new ViewResultButton();
                widgets.add(viewResultButton);

                if (cup.bothComputers() || cup.userPrefersResult) {
                    setSelectedWidget(viewResultButton);
                } else {
                    setSelectedWidget(playMatchButton);
                }
            }
        }
    }

    private class TeamButton extends Button {

        TeamButton(int x, int y, Team team, Font.Align align) {
            setGeometry(x, y, 240, 26);
            switch (team.controlMode) {
                case COMPUTER:
                    setColors(0x981E1E, 0x000001, 0x000001);
                    break;

                case PLAYER:
                    setColors(0x0000C8, 0x000001, 0x000001);
                    break;

                case COACH:
                    setColors(0x009BDC, 0x000001, 0x000001);
                    break;
            }
            setText(team.name, align, Assets.font10);
            setActive(false);
        }
    }

    private class VersusLabel extends Label {

        VersusLabel(int y, Match match) {
            setGeometry((game.gui.WIDTH - 30) / 2, y, 30, 26);
            // NOTE: max 2 characters
            setText(Assets.strings.get("ABBREVIATIONS.VERSUS"), Font.Align.CENTER, Assets.font10);
            if (match.ended) {
                setText("-");
            }
            setActive(false);
        }
    }

    private class ScrollButton extends Button {

        int direction;

        ScrollButton(int y, int direction) {
            this.direction = direction;
            setGeometry(228, y, 20, 36);
            setColors(0x000000, 0x1BC12F, 0x004814);
            // TODO: replace text with scroll icon
            setText((direction == 1) ? "D" : "U", Font.Align.CENTER, Assets.font10);
        }

        @Override
        public void onFire1Down() {
            scroll(direction);
        }

        @Override
        public void onFire1Hold() {
            scroll(direction);
        }

        private void scroll(int direction) {
            offset = Emath.slide(offset, 0, matches - 8, direction);
            updateResultWidgets();
        }
    }

    private class PlayViewMatchButton extends Button {

        PlayViewMatchButton() {
            setGeometry(game.gui.WIDTH / 2 - 430, 660, 220, 36);
            setColors(0x138B21, 0x1BC12F, 0x004814);
            setText("", Font.Align.CENTER, Assets.font14);
            if (cup.bothComputers()) {
                setText(Assets.strings.get("VIEW MATCH"));
            } else {
                setText("- " + Assets.strings.get("MATCH") + " -");
            }
        }

        @Override
        public void onFire1Down() {
            cup.userPrefersResult = false;

            Team homeTeam = cup.getTeam(HOME);
            Team awayTeam = cup.getTeam(AWAY);

            // reset input devices
            game.inputDevices.setAvailability(true);
            homeTeam.setInputDevice(null);
            homeTeam.releaseNonAiInputDevices();
            awayTeam.setInputDevice(null);
            awayTeam.releaseNonAiInputDevices();

            // choose the menu to set
            if (homeTeam.controlMode != Team.ControlMode.COMPUTER) {
                game.setScreen(new SetTeam(game, null, null, cup, homeTeam, awayTeam, HOME));
            } else if (awayTeam.controlMode != Team.ControlMode.COMPUTER) {
                game.setScreen(new SetTeam(game, null, null, cup, homeTeam, awayTeam, AWAY));
            } else {
                game.setScreen(new MatchSetup(game, null, null, cup, homeTeam, awayTeam));
            }
        }
    }

    private class NextMatchButton extends Button {

        NextMatchButton() {
            setGeometry(game.gui.WIDTH / 2 - 430, 660, 460, 36);
            setColors(0x138B21, 0x1BC12F, 0x004814);
            setText(Assets.strings.get("NEXT MATCH"), Font.Align.CENTER, Assets.font14);
        }

        @Override
        public void onFire1Down() {
            nextMatch();
        }

        @Override
        public void onFire1Hold() {
            nextMatch();
        }

        private void nextMatch() {
            cup.nextMatch();
            game.setScreen(new PlayCup(game));
        }
    }

    private class ViewResultButton extends Button {

        ViewResultButton() {
            setGeometry(game.gui.WIDTH / 2 - 190, 660, 220, 36);
            setColors(0x138B21, 0x1BC12F, 0x004814);
            setText("", Font.Align.CENTER, Assets.font14);
            if (cup.bothComputers()) {
                setText(Assets.strings.get("VIEW RESULT"));
            } else {
                setText("- " + Assets.strings.get("RESULT") + " -");
            }
        }

        @Override
        public void onFire1Down() {
            viewResult();
        }

        @Override
        public void onFire1Hold() {
            if (cup.bothComputers()) {
                viewResult();
            }
        }

        private void viewResult() {
            if (!cup.bothComputers()) {
                cup.userPrefersResult = true;
            }

            cup.generateResult();

            game.setScreen(new PlayCup(game));
        }
    }

    private class ViewStatisticsButton extends Button {

        ViewStatisticsButton() {
            setGeometry(game.gui.WIDTH / 2 + 50, 660, 180, 36);
            setColors(0x138B21, 0x1BC12F, 0x004814);
            setText(Assets.strings.get("STATS"), Font.Align.CENTER, Assets.font14);
        }

        @Override
        public void onFire1Down() {
            game.setScreen(new ViewStatistics(game));
        }
    }

    private class ExitButton extends Button {

        ExitButton() {
            setGeometry(game.gui.WIDTH / 2 + 250, 660, 180, 36);
            setColors(0xC84200, 0xFF6519, 0x803300);
            setText(Assets.strings.get("EXIT"), Font.Align.CENTER, Assets.font14);
        }

        @Override
        public void onFire1Down() {
            game.setScreen(new Main(game));
        }
    }

    private void updateResultWidgets() {
        if (matches > 8) {
            int m = 0;
            for (Widget w : resultWidgets) {
                if ((m >= 6 * offset) && (m < 6 * (offset + 8))) {
                    w.y = 120 + 64 * (m / 6 - offset) + ((m % 6) == 5 ? 26 : 0);
                    w.setVisible(true);
                } else {
                    w.setVisible(false);
                }
                m = m + 1;
            }
        }
    }
}