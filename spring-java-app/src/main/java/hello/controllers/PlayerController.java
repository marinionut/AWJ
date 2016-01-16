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
    public ResponseEntity create(@RequestBody Player player) {
//                                @RequestParam(value = "name", defaultValue = "Ronaldinho") String name,
//                                 @RequestParam(value = "age", defaultValue = "24") int age) {
        for(Player p: players) {
            if(p.getId()== player.getId())
                return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.FOUND);
        }
        players.add(player);
        return new ResponseEntity<Player>(player, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.PUT)
    public ResponseEntity update( @RequestBody Player player) {
//                                 @PathVariable("id") int id,
//                                 @RequestParam(value = "name") String name,
//                                 @RequestParam(value = "age") int age) {
        for(Player p : this.players) {
            if(p.getId() == player.getId()) {
                p.setName(player.getName());
                p.setAge(player.getAge());
                return new ResponseEntity<Player>(p, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
