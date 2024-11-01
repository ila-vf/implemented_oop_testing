package model;

/**
 * Kelas ini merepresentasikan model Buku dengan atribut judul dan pengarang.
 */
public class Book {
    private String title; // Judul buku
    private String author; // Pengarang buku

    // Konstruktor untuk membuat objek Book
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Getter untuk mendapatkan judul buku
    public String getTitle() {
        return title;
    }

    // Getter untuk mendapatkan pengarang buku
    public String getAuthor() {
        return author;
    }
}
