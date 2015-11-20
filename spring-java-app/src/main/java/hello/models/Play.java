package hello.models;

/**
 * Created by ionut on 20.11.2015.
 */
public class Play {
    private long id;
    private Player player;
    private Team team;
    private int salary;

    public Play(long id, Team team, Player player, int salary) {
        this.id = id;
        this.salary = salary;
        this.team = team;
        this.player = player;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
