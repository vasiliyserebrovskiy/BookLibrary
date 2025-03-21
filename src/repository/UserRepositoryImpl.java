package repository;

import model.Book;
import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (20.03.2025)
 */
public class UserRepositoryImpl implements UserRepository {

    private final MyList<User> users;
    private final AtomicInteger currenUserId = new AtomicInteger(1);

    public UserRepositoryImpl() {
        this.users = new MyArrayList<>();
        addStartUsers();
    }

    private void addStartUsers() {
        users.addAll(
                new User(currenUserId.getAndIncrement(), "1", "1", Role.ADMIN),
                new User(currenUserId.getAndIncrement(), "2", "2", Role.USER)
        );
    }

    @Override
    public User addUser(String email, String password) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }

    @Override
    public boolean isEmailExist(String email) {
        return false;
    }

    @Override
    public MyList<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean unblockUser(String email) {
        return false;
    }

    @Override
    public boolean unblockUser(int userId) {
        return false;
    }

    @Override
    public boolean blockUser(String email) {
        return false;
    }

    @Override
    public boolean blockUser(int id) {
        return false;
    }
}
