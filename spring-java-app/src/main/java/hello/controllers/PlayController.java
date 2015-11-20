package hello.controllers;

import hello.models.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ionut on 20.11.2015.
 */
@RestController
public class PlayController {
    private List<Team> teams = new ArrayList<Team>();
    private List<Player> players = new ArrayList<Player>();
    private List<Play> plays = new ArrayList<Play>();
    PlayController() {

        players = new PlayerController().index();
        teams = new TeamController().index();
        for(int i = 0; i < players.size(); i++) {
            Play pt = new Play (i, teams.get(new Random().nextInt(teams.size())), players.get(i), new Random().nextInt(100)*100);
            plays.add(pt);
        }
    }
    @RequestMapping(value = "/play", method = RequestMethod.GET)
    public List<Play> index(){ return this.plays; }

    @RequestMapping(value = "/play/{id}", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable("id") int id) {
        for(Play p : plays) {
            if(p.getId() == id) {
                return new ResponseEntity<Play>(p, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/team/{idTeam}/player", method = RequestMethod.GET)
    public ResponseEntity index(@PathVariable("idTeam") int idTeam) {
        List<Play> teamPlayers = new ArrayList<Play>();
        for(Play p : plays) {
            if(p.getTeam().getId() == idTeam) {
                teamPlayers.add(p);
            }
        }
        if(!teamPlayers.isEmpty())
            return new ResponseEntity<List<Play>>(teamPlayers, new HttpHeaders(), HttpStatus.OK);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/team/{idTeam}/player/{idPlayer}", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable("idTeam") int idTeam,
                               @PathVariable("idPlayer") int idPlayer) {
        for(Play p : plays) {
            if(p.getTeam().getId() == idTeam && p.getPlayer().getId() == idPlayer) {
                return new ResponseEntity<Play>(p, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/team/{idTeam}/player/{idPlayer}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("idTeam") int idTeam,
                               @PathVariable("idPlayer") int idPlayer) {
        for(Play p : plays) {
            if(p.getTeam().getId() == idTeam && p.getPlayer().getId() == idPlayer) {
                this.teams.remove(p);
                return new ResponseEntity<Play>(p, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/play/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("id") int id) {
        for(Play p : plays) {
            if(p.getId() == id) {
                this.plays.remove(p);
                return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/team/{idTeam}/player/{idPlayer}", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable("idTeam") int idTeam,
                                 @PathVariable("idPlayer") int idPlayer,
                                 @RequestParam(value = "salary", defaultValue = "456") int salary) {
        Team expectedTeam = null;
        Player expectedPlayer = null;
        for(Team t : teams) {
            if(t.getId() == idTeam) {
                expectedTeam = t;
                break;
            }
        }

        for(Player p : players) {
            if(p.getId() == idPlayer) {
                expectedPlayer = p;
                break;
            }
        }
        if(expectedPlayer != null && expectedTeam != null) {
            Play p = new Play(plays.size(), expectedTeam, expectedPlayer, salary);
            plays.add(p);
            return new ResponseEntity<Play>(p, new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/team/{idTeam}/player/{idPlayer}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("idTeam") int idTeam,
                               @PathVariable("idPlayer") int idPlayer,
                               @RequestParam(value ="newIdTeam") int newIdTeam,
                               @RequestParam(value ="salary", defaultValue = "678") int salary) {
        for(Play p : plays) {
            if(p.getTeam().getId() == idTeam && p.getPlayer().getId() == idPlayer) {
                Team expectedTeam = null;
                for(Team t : teams) {
                    if(t.getId() == newIdTeam) {
                        expectedTeam = t;
                        break;
                    }
                }
                p.setTeam(expectedTeam);
                p.setSalary(salary);
                return new ResponseEntity<Play>(p, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
