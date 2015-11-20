package hello.controllers;

import hello.models.*;
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
public class PlayerController {
    private List<Player> players = new ArrayList<Player>();

    PlayerController() {
        Player p1 = new Player(0, "Hagi", 24);
        Player p2 = new Player(1, "Dobrin", 35);
        Player p3 = new Player(2, "Popescu", 26);

        players.add(p1);
        players.add(p2);
        players.add(p3);
    }
    @RequestMapping(value = "/player", method = RequestMethod.GET)
    public List<Player> index(){ return this.players; }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable("id") int id) {
        for(Player p : players) {
            if(p.getId() == id) {
                return new ResponseEntity<Player>(p, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("id") int id) {
        for(Player p : players) {
            if(p.getId() == id) {
                this.players.remove(p);
                return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public ResponseEntity create(@RequestParam(value = "name", defaultValue = "Ronaldinho") String name,
                                 @RequestParam(value = "age", defaultValue = "24") int age) {
        Player p = new Player(players.size(), name, age);
        players.add(p);
        return new ResponseEntity<Player>(p, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") int id,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "age") int age) {
        for(Player p : this.players) {
            if(p.getId() == id) {
                p.setName(name);
                p.setAge(age);
                return new ResponseEntity<Player>(p, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
