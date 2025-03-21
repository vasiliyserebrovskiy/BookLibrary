package service;

import model.User;
import model.Book;
import utils.MyList;

public interface MainService {
    // Методу по работе с пользователем

    User createUser(String email, String password);

    User getUserByEmail(String email);

    User getUserById(int id);

    MyList<User> getAllUsers(); // Для отображения всех зарегистрированных пользователей

    boolean updatePassword(String email, String newPassword);

    boolean deleteUser(String email);

    // Методы по работе с книгами

    Book createBook(String title, String author);

    Book getBookById(int id);

    MyList<Book> getAllBooks();

    MyList<Book> getAvailableBooks();

    MyList<Book> getBorrowedBooks();

    MyList<Book> getMyBooks(User user);

    MyList<Book> getBooksByTitle(String title); // contains
    MyList<Book> getBooksByAuthor(String author); // contains

    boolean login(String email, String password);
    boolean logout();
    User getActiveUser();

    boolean unblockUser(String email);
    boolean unblockUser(int userId);

    boolean blockUser(String email);
    boolean blockUser(int userId);

    // Взять книгу из библиотеки
    boolean userGetBook(int bookId);
    // Вернуть книгу
    boolean userReturnBook(int bookId);
    // Обновить книгу - для Админа

    boolean deleteBookById(int id);

}
