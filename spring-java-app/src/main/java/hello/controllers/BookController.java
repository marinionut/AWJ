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
public class BookController {
    private List<Book> books = new ArrayList<Book>();

    BookController() {
        Book b1 = new Book(0, "Amintiri din Copilarie");
        Book b2 = new Book(1, "Colt Alb");
        Book b3 = new Book(2, "Vrajitorul din Oz");

        books.add(b1);
        books.add(b2);
        books.add(b3);
    }

    @RequestMapping(value="/book", method = RequestMethod.GET)
    public List<Book> index() {
        return this.books;
    }

    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable("id") int id) {
        for(Book b : this.books) {
            if(b.getId() == id) {
                return new ResponseEntity<Book>(b, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("id") int id) {
        for(Book b : this.books) {
            if(b.getId() == id) {
                this.books.remove(b);
                return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/book", method = RequestMethod.POST)
    public ResponseEntity create(@RequestParam(value="name", defaultValue="Star Wars") String name) {
        Book b = new Book(books.size(), name);
        books.add(b);
        return new ResponseEntity<Book>(b, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") int id,
                                 @RequestParam(value="name", defaultValue="Star Wars") String name) {
        for(Book b: this.books) {
            if(b.getId() == id ){
                b.setName(name);
                return new ResponseEntity<Book>(b, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
