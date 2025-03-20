package repository;

import model.User;
import utils.MyList;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (20.03.2025)
 */
public class UserRepositoryImpl implements UserRepository {
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
}
