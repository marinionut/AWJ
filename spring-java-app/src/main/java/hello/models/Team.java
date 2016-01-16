package hello.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ionut on 18.11.2015.
 */
public class Team {
    private long id;
    private String name;
    private String league;
    private List<Player> teamPlayers;

    public Team() {

    }

    public Team(long id, String name, String league) {
        this.id = id;
        this.league = league;
        this.name = name;
        teamPlayers = new ArrayList<>();
    }

    public Team(long id, String league, String name, List<Player> teamPlayers) {
        this.id = id;
        this.teamPlayers = teamPlayers;
        this.league = league;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(List<Player> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }
}
