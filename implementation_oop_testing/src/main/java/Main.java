import controller.BookController;
import controller.UserController;
import view.BookViewGUI;
import view.UserViewGUI;

import javax.swing.*;

public class Main {
    private static JFrame mainMenuFrame;

    public static void main(String[] args) {
        BookController bookController = new BookController();
        UserController userController = new UserController();

        SwingUtilities.invokeLater(() -> {
            mainMenuFrame = createMainMenuFrame(bookController, userController);
            mainMenuFrame.setVisible(true);
        });
    }

    private static JFrame createMainMenuFrame(BookController bookController, UserController userController) {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = new JPanel(); // Use a panel for better layout management
        JButton manageBooksButton = new JButton("Manage Books");
        JButton manageUsersButton = new JButton("Manage Users");
        JButton exitButton = new JButton("Exit");

        panel.add(manageBooksButton);
        panel.add(manageUsersButton);
        panel.add(exitButton);

        manageBooksButton.addActionListener(e -> {
            frame.setVisible(false); // Hide the main menu
            new BookViewGUI(bookController, () -> {
                frame.setVisible(true); // Show main menu when returning
            });
        });

        manageUsersButton.addActionListener(e -> {
            frame.setVisible(false); // Hide the main menu
            new UserViewGUI(userController, () -> {
                frame.setVisible(true); // Show main menu when returning
            });
        });

        exitButton.addActionListener(e -> System.exit(0)); // Exit the application

        frame.getContentPane().add(panel); // Add the panel to the frame
        frame.setVisible(true); // Make the frame visible
        return frame; // Return the frame
    }
}
