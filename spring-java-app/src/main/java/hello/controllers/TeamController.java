package hello.controllers;


import hello.models.Play;
import hello.models.Player;
import hello.models.Team;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ionut on 18.11.2015.
 */
@RestController
public class TeamController {
    private List<Team> teams = new ArrayList<Team>();
    private List<Player> players = new ArrayList<Player>();

    TeamController() {
        Team t1 = new Team(0, "Steaua", "Liga I");
        Team t2 = new Team(1, "Dinamo", "Liga I");
        Team t3 = new Team(2, "Rapid", "Liga I");
        teams.add(t1);
        teams.add(t2);
        teams.add(t3);

        Player p1 = new Player(0, "Hagi", 24);
        Player p2 = new Player(1, "Dobrin", 35);
        Player p3 = new Player(2, "Popescu", 26);
        Player p4 = new Player(3, "Ronaldo", 33);
        Player p5 = new Player(4, "Messi", 32);
        Player p6 = new Player(5, "Ronaldinho", 23);
        Player p7 = new Player(6, "Kaka", 54);
        Player p8 = new Player(7, "Deco", 23);
        Player p9 = new Player(8, "Dica", 32);
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);
        players.add(p6);
        players.add(p7);
        players.add(p8);
        players.add(p9);

        for(Player p : players) {
            teams.get(new Random().nextInt(3)).getTeamPlayers().add(p);
        }
    }
    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public List<Team> index(){ return this.teams; }

    @RequestMapping(value = "/team/{id}", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable("id") int id) {
        for(Team t : teams) {
            if(t.getId() == id) {
                return new ResponseEntity<Team>(t, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/team/player/{id}", method = RequestMethod.GET)
    public List<Player> showPlayers(@PathVariable("id") int id) {
        for(Team team : this.teams) {
            if(team.getId() == id) {
                return team.getTeamPlayers();
            }
        }
        return new ArrayList<Player>();
    }

//    @RequestMapping(value="/team/player/{id}", method = RequestMethod.GET)
//    public ResponseEntity showPlayers(@PathVariable("id") int id) {
//        for(Team team : this.teams) {
//            if(team.getId() == id) {
//                return new ResponseEntity<List<Player>>(team.getTeamPlayers(), new HttpHeaders(), HttpStatus.FOUND);
//            }
//        }
//        return new ResponseEntity<String>("Error", new HttpHeaders(), HttpStatus.NOT_FOUND);
//    }

    @RequestMapping(value = "/team/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("id") int id) {
        for(Team t : teams) {
            if(t.getId() == id) {
                this.teams.remove(t);
                return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/team", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Team team) {
        for(Team a: teams) {
            if(a.getId()== team.getId())
                return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.FOUND);
        }
        teams.add(team);
        return new ResponseEntity<Team>(team, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/team/{id}", method = RequestMethod.PUT)
    public ResponseEntity update( @RequestBody Team team)
//                                 @PathVariable("id") int id,
//                                 @RequestParam(value = "name", defaultValue = "AllStars Team") String name,
//                                 @RequestParam(value = "founderName", defaultValue = "Abramovici") String founderName,
//                                 @RequestParam(value = "league", defaultValue = "3") int league)
                                {
        for(Team t : this.teams) {
            if(t.getId() == team.getId()) {
                t.setName(team.getName());
                t.setLeague(team.getLeague());
                return new ResponseEntity<Team>(t, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}

