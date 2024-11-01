import controller.BookController;
import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {
    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookController = new BookController();
    }

    @Test
    void testAddBook() {
        // Menambahkan buku
        bookController.addBook("Clean Code", "Robert C. Martin");
        List<Book> books = bookController.listBooks();
        assertEquals(1, books.size()); // Memastikan ukuran daftar buku adalah 1
        assertEquals("Clean Code", books.get(0).getTitle()); // Memastikan judul buku yang ditambahkan benar
    }

    @Test
    void testEditBook() {
        // Menambahkan buku dan kemudian mengeditnya
        bookController.addBook("Clean Code", "Robert C. Martin");
        boolean result = bookController.editBook(0, "Refactoring", "Martin Fowler");
        assertTrue(result); // Memastikan pengeditan berhasil
        List<Book> books = bookController.listBooks();
        assertEquals("Refactoring", books.get(0).getTitle()); // Memastikan judul buku yang diedit benar
    }

    @Test
    void testRemoveBook() {
        // Menambahkan buku dan kemudian menghapusnya
        bookController.addBook("Clean Code", "Robert C. Martin");
        boolean result = bookController.removeBook(0);
        assertTrue(result); // Memastikan penghapusan berhasil
        assertEquals(0, bookController.listBooks().size()); // Memastikan daftar buku kosong
    }

    @Test
    void testEditBookInvalidIndex() {
        // Uji pengeditan dengan index yang tidak valid
        boolean result = bookController.editBook(0, "New Title", "New Author");
        assertFalse(result); // Memastikan pengeditan gagal karena index tidak valid
    }

    @Test
    void testRemoveBookFromEmptyList() {
        // Uji penghapusan dari daftar yang kosong
        boolean result = bookController.removeBook(0);
        assertFalse(result); // Memastikan penghapusan gagal karena tidak ada buku untuk dihapus
    }

    @Test
    void testAddBookWithEmptyTitle() {
        // Uji menambahkan buku dengan judul kosong
        bookController.addBook("", "Author Name"); // Menambahkan buku dengan judul kosong
        List<Book> books = bookController.listBooks(); // Mendapatkan daftar buku
        assertEquals(0, books.size()); // Memastikan tidak ada buku dalam daftar
    }

    @Test
    void testAddBookWithNullAuthor() {
        // Uji menambahkan buku dengan penulis null
        bookController.addBook("Book Title", null); // Menambahkan buku dengan penulis null
        List<Book> books = bookController.listBooks(); // Mendapatkan daftar buku
        assertEquals(0, books.size()); // Memastikan tidak ada buku dalam daftar
    }
}
