package repository;

import model.Book;
import model.User;
import utils.MyList;

public interface UserRepository {
    //CRUD
    User addUser(String email, String password); // При реализации не забыть генерировать уникальный id первым параметров!

    User getUserByEmail(String email);

    User getUserById(int id);

    User login(String email, String password);

    MyList<User> getAllUsers(); // Для отображения всех зарегистрированных пользователей

    boolean updatePassword(String email, String newPassword);

    boolean deleteUser(String email); // Только ADMIN

    boolean isEmailExist(String email);

    boolean unblockUser(String email);
    boolean unblockUser(int userId);

    boolean blockUser(String email);
    boolean blockUser(int id);

}
