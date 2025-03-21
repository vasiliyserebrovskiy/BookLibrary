package repository;

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
                new User(currenUserId.getAndIncrement(), "admin@example.com", "Admin#123", Role.ADMIN),
                new User(currenUserId.getAndIncrement(), "user1@example.com", "User1_pass!", Role.USER),
                new User(currenUserId.getAndIncrement(), "user2@example.com", "Secure*987", Role.USER),
                new User(currenUserId.getAndIncrement(), "user3@example.com", "TestUser@22", Role.USER),
                new User(currenUserId.getAndIncrement(), "user4@example.com", "Pa$$w0rd99", Role.USER)
        );
    }

    @Override
    public User addUser(String email, String password) {
        User user = new User(currenUserId.getAndIncrement(), email, password, Role.USER);
        users.add(user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        User user = getUserByEmail(email);
        user.setPassword(newPassword);
        return true;
    }

    @Override
    public boolean deleteUser(String email) {
        User user = getUserByEmail(email);
        return users.remove(user);
    }

    @Override
    public boolean isEmailExist(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MyList<User> getAllUsers() {
        return users;
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
