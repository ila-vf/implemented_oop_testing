package controller;

import model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas ini mengatur logika untuk manajemen buku,
 * termasuk menambah, mengedit, menghapus, dan mendapatkan daftar buku.
 */
public class BookController {
    private List<Book> books = new ArrayList<>(); // Daftar buku

    // Menambahkan buku baru ke dalam daftar
    public void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    // Mendapatkan daftar semua buku
    public List<Book> listBooks() {
        return books;
    }

    // Mengedit buku berdasarkan index
    public boolean editBook(int index, String title, String author) {
        if (index >= 0 && index < books.size()) {
            books.set(index, new Book(title, author));
            return true; // Pengeditan berhasil
        }
        return false; // Pengeditan gagal
    }

    // Menghapus buku berdasarkan index
    public boolean removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
            return true; // Penghapusan berhasil
        }
        return false; // Penghapusan gagal
    }
}
