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
public class BookController {
    private List<Book> books = new ArrayList<Book>();

    BookController() {
        Book b1 = new Book(0, "Amintiri din Copilarie", "Olimp");
        Book b2 = new Book(1, "Colt Alb", "Teora");
        Book b3 = new Book(2, "Vrajitorul din Oz", "Olimp");

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
    public ResponseEntity create(@RequestBody Book book) {
        for(Book a: books) {
            if(a.getId()== book.getId())
                return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.FOUND);
        }
        books.add(book);
        return new ResponseEntity<Book>(book, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Book book) {
        for(Book b: this.books) {
            if(b.getId() == book.getId()){
                b.setTitlu(book.getTitlu());
                b.setEditura(book.getEditura());
                return new ResponseEntity<Book>(b, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
