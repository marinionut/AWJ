package hello.controllers;

import hello.models.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by ionut on 18.11.2015.
 */
@RestController
public class AuthorController {
    private List<Author> authors = new ArrayList<Author>();

    AuthorController() {
        Author a1 = new Author(0, "Creanga");
        Author a2 = new Author(1, "Eminescu");
        Author a3 = new Author(2, "Sadoveanu");

        authors.add(a1);
        authors.add(a2);
        authors.add(a3);
    }

    @RequestMapping(value="/author", method = RequestMethod.GET)
    public List<Author> index() {
        return this.authors;
    }

    @RequestMapping(value="/author/{id}", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable("id") int id) {
        for(Author a : this.authors) {
            if(a.getId() == id) {
                return new ResponseEntity<Author>(a, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("id") int id) {
        for(Author a : this.authors) {
            if(a.getId() == id) {
                this.authors.remove(a);
                return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/author", method = RequestMethod.POST)
    public ResponseEntity create(@RequestParam(value="name", defaultValue="Bacovia") String name) {
        Author a = new Author(authors.size(), name);
        authors.add(a);
        return new ResponseEntity<Author>(a, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/author/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") int id,
                                 @RequestParam(value="name", defaultValue="Bacovia") String name) {
        for(Author a: this.authors) {
            if(a.getId() == id ){
                a.setName(name);
                return new ResponseEntity<Author>(a, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
