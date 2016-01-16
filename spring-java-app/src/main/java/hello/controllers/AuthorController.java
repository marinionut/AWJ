package hello.controllers;

import hello.models.*;
import org.springframework.web.bind.annotation.*;
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
        Author a1 = new Author(0, "Creanga", "Ion" );
        Author a2 = new Author(1, "Eminescu", "Mihai" );
        Author a3 = new Author(2, "Sadoveanu", "Mihail");

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
    public ResponseEntity create(@RequestBody Author author)
                                /*(@RequestParam(value="nume", defaultValue="Bacovia") String nume,
                                 @RequestParam(value="prenume", defaultValue="George") String prenume)
                                */{
        for(Author a: authors) {
           if(a.getNume().equals(author.getNume()) && author.getPrenume().equals(a.getPrenume()))
               return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.FOUND);
        }
        authors.add(author);
        return new ResponseEntity<Author>(author, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/author/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Author author)
                                /*
                                 @PathVariable("id") int id,
                                 @RequestParam(value="name", defaultValue="Bacovia") String nume,
                                 @RequestParam(value="prenume", defaultValue="George") String prenume
                                 */ {
        for(Author a: this.authors) {
            if(a.getId() == author.getId() ){
                a.setNume(author.getNume());
                a.setPrenume(author.getPrenume());
                return new ResponseEntity<Author>(a, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
