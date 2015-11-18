package hello.controllers;

import hello.models.Player;
import hello.models.Team;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ionut on 18.11.2015.
 */
@RestController
public class TeamController {
    private List<Team> teams = new ArrayList<Team>();

    TeamController() {
        Team t1 = new Team(0, "Steaua");
        Team t2 = new Team(1, "Dinamo");
        Team t3 = new Team(2, "Rapid");

        teams.add(t1);
        teams.add(t2);
        teams.add(t3);
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
    public ResponseEntity create(@RequestParam(value = "name", defaultValue = "AllStars Team") String name) {
        Team t = new Team(teams.size(), name);
        teams.add(t);
        return new ResponseEntity<Team>(t, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/team/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") int id,
                                 @RequestParam(value = "name", defaultValue = "AllStars Team") String name) {
        for(Team t : this.teams) {
            if(t.getId() == id) {
                t.setName(name);
                return new ResponseEntity<Team>(t, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
