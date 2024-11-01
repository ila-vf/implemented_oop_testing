package model;

/**
 * Kelas ini merepresentasikan model Pengguna dengan atribut nama dan email.
 */
public class User {
    private String name; // Nama pengguna
    private String email; // Email pengguna

    // Konstruktor untuk membuat objek User
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getter untuk mendapatkan nama pengguna
    public String getName() {
        return name;
    }

    // Getter untuk mendapatkan email pengguna
    public String getEmail() {
        return email;
    }
}
