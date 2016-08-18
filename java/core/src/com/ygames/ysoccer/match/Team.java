package com.ygames.ysoccer.match;

import com.ygames.ysoccer.framework.Assets;
import com.ygames.ysoccer.math.Emath;

import java.util.Comparator;
import java.util.List;

public class Team {

    public enum Type {CLUB, NATIONAL}

    public enum ControlMode {UNDEFINED, COMPUTER, PLAYER, COACH}

    public String name;
    public Type type;
    public String path;
    public String country;
    public String city;
    public String stadium;
    public Coach coach;

    public String tactics;
    public ControlMode controlMode;

    public List<Player> players;

    public int won;
    public int drawn;
    public int lost;

    public int goalsFor;
    public int goalsAgainst;
    public int points;

    public Team() {
        controlMode = ControlMode.UNDEFINED;
    }

    public Player newPlayer() {
        if (players.size() == Const.FULL_TEAM) {
            return null;
        }

        Player player = new Player();
        player.name = "";
        player.shirtName = "";
        player.nationality = country;
        player.role = Player.Role.GOALKEEPER;

        // number
        for (int i = 1; i <= Const.FULL_TEAM; i++) {
            boolean used = false;
            for (Player ply : players) {
                if (Integer.parseInt(ply.number) == i) {
                    used = true;
                }
            }
            if (!used) {
                player.number = "" + i;
                break;
            }
        }
        player.hairType = "SMOOTH1";
        player.skills = new Player.Skills();
        players.add(player);
        return player;
    }

    public boolean deletePlayer(Player player) {
        if (players.size() > Const.BASE_TEAM) {
            return players.remove(player);
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        Team t = (Team) obj;
        return path.equals(t.path);
    }

    public static class CompareByStats implements Comparator<Team> {

        @Override
        public int compare(Team o1, Team o2) {
            // by points
            if (o1.points != o2.points) {
                return o2.points - o1.points;
            }

            // by goals diff
            int diff1 = o1.goalsFor - o1.goalsAgainst;
            int diff2 = o2.goalsFor - o2.goalsAgainst;
            if (diff1 != diff2) {
                return diff2 - diff1;
            }

            // by scored goals
            if (o1.goalsFor != o2.goalsFor) {
                return o2.goalsFor - o1.goalsFor;
            }

            // by names
            return o1.name.compareTo(o2.name);
        }
    }

    public void updateStats(int goalsFor, int goalsAgainst, int pointsForAWin) {
        this.goalsFor += goalsFor;
        this.goalsAgainst += goalsAgainst;
        if (goalsFor > goalsAgainst) {
            won += 1;
            points += pointsForAWin;
        } else if (goalsFor == goalsAgainst) {
            drawn += 1;
            points += 1;
        } else {
            lost += 1;
        }
    }

    public void generateScorers(int goals) {
        for (int g = 1; g <= goals; g++) {
            int i = Emath.floor(11 * Math.random());
            players.get(i).goals++;
        }
    }

    public Player playerAtPosition(int pos) {
        return playerAtPosition(pos, null);
    }

    public Player playerAtPosition(int pos, Tactics tcs) {
        if (tcs == null) {
            tcs = Assets.tactics[getTacticsIndex()];
        }
        if (pos < players.size()) {
            int ply = (pos < Const.TEAM_SIZE) ? Tactics.order[tcs.basedOn][pos] : pos;
            return players.get(ply);
        } else {
            return null;
        }
    }

    public int playerIndexAtPosition(int pos) {
        if (pos < players.size()) {
            int baseTactics = Assets.tactics[getTacticsIndex()].basedOn;
            return (pos < Const.TEAM_SIZE) ? Tactics.order[baseTactics][pos] : pos;
        } else {
            return -1;
        }
    }

    public int defenseRating() {
        int defense = 0;
        for (int p = 0; p < 11; p++) {
            defense += playerAtPosition(p).getDefenseRating();
        }
        return defense;
    }

    public int offenseRating() {
        int offense = 0;
        for (int p = 0; p < 11; p++) {
            offense += playerAtPosition(p).getOffenseRating();
        }
        return offense;
    }

    // TODO: replace with custom serialization
    public int getTacticsIndex() {
        for (int i = 0; i < Tactics.codes.length; i++) {
            if (Tactics.codes[i].equals(tactics)) {
                return i;
            }
        }
        return -1;
    }
}
