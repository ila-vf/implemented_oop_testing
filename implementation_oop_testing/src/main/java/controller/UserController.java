package controller;

import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas ini mengatur logika untuk manajemen pengguna,
 * termasuk menambah, mengedit, menghapus, dan mendapatkan daftar pengguna.
 */
public class UserController {
    private List<User> users = new ArrayList<>(); // Daftar pengguna

    // Menambahkan pengguna baru ke dalam daftar dan mengembalikan true jika berhasil
    public boolean addUser(String name, String email) {
        if (name == null || name.isEmpty() || email == null || !email.contains("@")) {
            return false; // Gagal menambahkan pengguna jika nama kosong atau email tidak valid
        }
        users.add(new User(name, email));
        return true; // Berhasil menambahkan pengguna
    }

    // Mendapatkan daftar semua pengguna
    public List<User> listUsers() {
        return new ArrayList<>(users); // Mengembalikan salinan daftar pengguna
    }

    // Mengedit pengguna berdasarkan index dan mengembalikan true jika berhasil
    public boolean editUser(int index, String name, String email) {
        if (index >= 0 && index < users.size() && name != null && !name.isEmpty() && email != null && email.contains("@")) {
            users.set(index, new User(name, email));
            return true; // Pengeditan berhasil
        }
        return false; // Pengeditan gagal
    }

    // Menghapus pengguna berdasarkan index dan mengembalikan true jika berhasil
    public boolean removeUser(int index) {
        if (index >= 0 && index < users.size()) {
            users.remove(index);
            return true; // Penghapusan berhasil
        }
        return false; // Penghapusan gagal
    }
}
