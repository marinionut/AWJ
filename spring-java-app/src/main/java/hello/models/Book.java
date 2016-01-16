package hello.models;

/**
 * Created by ionut on 18.11.2015.
 */
public class Book {
    private int id;
    private String titlu;
    private String editura;


    public Book() {
    }

    public Book(int id, String titlu, String editura) {
        this.titlu = titlu;
        this.editura = editura;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }
}