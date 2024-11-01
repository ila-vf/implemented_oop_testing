package view;

import model.Book;
import controller.BookController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**  
 * Kelas ini bertanggung jawab untuk menampilkan informasi tentang buku dalam bentuk GUI.
 */
public class BookViewGUI extends JFrame {
    private JTextArea textArea;
    private JButton addButton, listButton, updateButton, deleteButton, backButton;
    private BookController bookController;

    public BookViewGUI(BookController bookController, Runnable callback) {
        this.bookController = bookController;

        setTitle("Manage Books");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(500, 75));
        addButton = new JButton("Add Book");
        listButton = new JButton("List Books");
        updateButton = new JButton("Update Book");
        deleteButton = new JButton("Delete Book");
        backButton = new JButton("Back to Main Menu");
        
        buttonPanel.add(addButton);
        buttonPanel.add(listButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners untuk masing-masing tombol
        addButton.addActionListener(e -> addBook());
        listButton.addActionListener(e -> printBooks(bookController.listBooks()));
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
        backButton.addActionListener(e -> {
            dispose(); // Tutup jendela BookViewGUI
            callback.run(); // Tampilkan kembali Main Menu
        });

        setVisible(true);
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog(this, "Enter Title:");
        String author = JOptionPane.showInputDialog(this, "Enter Author:");
        bookController.addBook(title, author);
        printSuccessMessage("Book added successfully!");
    }

    private void updateBook() {
        int index = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Book Index to update:")) - 1;
        String newTitle = JOptionPane.showInputDialog(this, "Enter New Title:");
        String newAuthor = JOptionPane.showInputDialog(this, "Enter New Author:");
        if (bookController.editBook(index, newTitle, newAuthor)) {
            printSuccessMessage("Book updated successfully!");
        } else {
            printErrorMessage("Book not found.");
        }
    }

    private void deleteBook() {
        int index = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Book Index to delete:")) - 1;
        if (bookController.removeBook(index)) {
            printSuccessMessage("Book deleted successfully!");
        } else {
            printErrorMessage("Book not found.");
        }
    }

    // Menampilkan daftar buku di area teks
    public void printBooks(List<Book> books) {
        textArea.setText("=== List of Books ===\n");
        for (int i = 0; i < books.size(); i++) {
            textArea.append((i + 1) + ". Title: " + books.get(i).getTitle() + ", Author: " + books.get(i).getAuthor() + "\n");
        }
    }

    // Menampilkan pesan sukses dalam bentuk pop-up
    public void printSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Menampilkan pesan kesalahan dalam bentuk pop-up
    public void printErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
