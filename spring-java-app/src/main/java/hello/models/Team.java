package hello.models;

import java.util.Collections;
import java.util.List;

/**
 * Created by ionut on 18.11.2015.
 */
public class Team {
    private long id;
    private String name;
    private String founderName;
    private int league;

    public Team(long id, String name, String founderName, int league) {
        this.id = id;
        this.league = league;
        this.founderName = founderName;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFounderName() {
        return founderName;
    }

    public void setFounderName(String founderName) {
        this.founderName = founderName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeague() {
        return league;
    }

    public void setLeague(int league) {
        this.league = league;
    }
}
