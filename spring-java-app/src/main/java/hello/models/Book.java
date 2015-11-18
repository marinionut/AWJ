package hello.models;

/**
 * Created by ionut on 18.11.2015.
 */
public class Book {
    private String name;
    private int id;

    public Book() {}

    public Book(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
