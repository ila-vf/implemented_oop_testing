import controller.UserController;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(); // Inisialisasi objek UserController sebelum setiap pengujian
    }

    @Test
    void testAddUserWithInvalidEmail() {
        // Uji penambahan pengguna dengan email yang tidak valid
        boolean result = userController.addUser("Jane Doe", "jane.doeexample.com");
        assertFalse(result); // Pastikan pengguna tidak berhasil ditambahkan
        assertEquals(0, userController.listUsers().size()); // Pastikan tidak ada pengguna dalam daftar
    }

    @Test
    void testEditUserWithInvalidEmail() {
        // Menambahkan pengguna untuk diuji
        userController.addUser("Alice", "alice@example.com");
        boolean result = userController.editUser(0, "Alice Smith", "invalid-email");
        assertFalse(result); // Pastikan pengeditan gagal karena email tidak valid
        List<User> users = userController.listUsers();
        assertEquals("Alice", users.get(0).getName()); // Pastikan nama pengguna tetap tidak berubah
    }

    @Test
    void testRemoveUserFromEmptyList() {
        // Uji penghapusan dari daftar yang kosong
        boolean result = userController.removeUser(0);
        assertFalse(result); // Pastikan penghapusan gagal karena tidak ada pengguna untuk dihapus
    }

    @Test
    void testEditUserInvalidIndex() {
        // Uji pengeditan dengan index yang tidak valid
        boolean result = userController.editUser(10, "New Name", "new.email@example.com");
        assertFalse(result); // Pastikan pengeditan gagal karena index tidak valid
    }

    @Test
    void testAddUserWithEmptyName() {
        // Uji penambahan pengguna dengan nama kosong
        boolean result = userController.addUser("", "empty.name@example.com");
        assertFalse(result); // Pastikan pengguna tidak berhasil ditambahkan
        assertEquals(0, userController.listUsers().size()); // Pastikan tidak ada pengguna dalam daftar
    }

    @Test
    void testEditUserWithValidDataButFailingAssert() {
        // Menambahkan pengguna untuk diuji
        userController.addUser("Charlie", "charlie@example.com");
        boolean result = userController.editUser(0, "Charlie Brown", "charlie.brown@example.com");
        assertTrue(result); // Pastikan pengeditan berhasil
        
        // Uji yang akan gagal: memeriksa nama yang salah
        assertEquals("Wrong Name", userController.listUsers().get(0).getName()); // Ini akan gagal
    }
}
