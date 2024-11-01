package view;

import model.User;
import controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Kelas ini bertanggung jawab untuk menampilkan informasi tentang pengguna dalam bentuk GUI.
 */
public class UserViewGUI extends JFrame {
    private JTextArea textArea;
    private JButton addButton, listButton, updateButton, deleteButton, backButton;
    private UserController userController;

    public UserViewGUI(UserController userController, Runnable callback) {
        this.userController = userController;

        setTitle("Manage Users");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create the button panel and set its preferred size
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(500, 75)); // Set preferred size here

        addButton = new JButton("Add User");
        listButton = new JButton("List Users");
        updateButton = new JButton("Update User");
        deleteButton = new JButton("Delete User");
        backButton = new JButton("Back to Main Menu");

        buttonPanel.add(addButton);
        buttonPanel.add(listButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for each button
        addButton.addActionListener(e -> addUser());
        listButton.addActionListener(e -> printUsers(userController.listUsers()));
        updateButton.addActionListener(e -> updateUser());
        deleteButton.addActionListener(e -> deleteUser());
        backButton.addActionListener(e -> {
            dispose(); // Close the UserViewGUI window
            callback.run(); // Callback to Main Menu
        });

        setVisible(true);
    }

    private void addUser() {
        String name = JOptionPane.showInputDialog(this, "Enter Name:");
        String email = JOptionPane.showInputDialog(this, "Enter Email:");
        userController.addUser(name, email);
        printSuccessMessage("User added successfully!");
    }

    private void updateUser() {
        int index = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter User Index to update:")) - 1;
        String newName = JOptionPane.showInputDialog(this, "Enter New Name:");
        String newEmail = JOptionPane.showInputDialog(this, "Enter New Email:");
        if (userController.editUser(index, newName, newEmail)) {
            printSuccessMessage("User updated successfully!");
        } else {
            printErrorMessage("User not found.");
        }
    }

    private void deleteUser() {
        int index = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter User Index to delete:")) - 1;
        if (userController.removeUser(index)) {
            printSuccessMessage("User deleted successfully!");
        } else {
            printErrorMessage("User not found.");
        }
    }

    public void printUsers(List<User> users) {
        textArea.setText("=== List of Users ===\n");
        for (int i = 0; i < users.size(); i++) {
            textArea.append((i + 1) + ". Name: " + users.get(i).getName() + ", Email: " + users.get(i).getEmail() + "\n");
        }
    }

    public void printSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void printErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
