package service;

import model.Book;
import model.Role;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyArrayList;
import utils.MyList;
import utils.UserValidation;


public class MainServiceImpl implements MainService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private User activeUser;

    public MainServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.activeUser = null;
    }

    // Users

    @Override
    public User createUser(String email, String password) {
        // проверяем валидность эмеил
        if (!UserValidation.isEmailValid(email)) {
            return null;
        }
        // проверяем существует ли пользователь с таким эмеил
        if (userRepository.isEmailExist(email)) {
            return null;
        }
        // проверяем валидность пароля
        if (!UserValidation.isPasswordValid(password)) {
            return null;
        }
        // создаем юзера
        return userRepository.addUser(email,password);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public MyList<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        // есть ли пользователь
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        // валидность пароля
        if (!UserValidation.isPasswordValid(newPassword)) {
            return false;
        }
        return userRepository.updatePassword(email, newPassword);
    }

    @Override
    public boolean deleteUser(String email) {
        // проверка на Админа
        if (activeUser.getRole() != Role.ADMIN) {
            return false;
        }
        // проверю валидность
        if (!UserValidation.isEmailValid(email)) {
            return false;
        }
        // проверка в репозитории
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        return userRepository.deleteUser(email);
    }

    @Override
    public boolean blockUser(String email) {
        if (activeUser.getRole() != Role.ADMIN) {
            return false;
        }
        if (!UserValidation.isEmailValid(email)) {
            return false;
        }
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        user.setBlocked(true);
        return true;
    }

    @Override
    public boolean blockUser(int userId) {
        if (activeUser.getRole() != Role.ADMIN) {
            return false;
        }
        if (userId <= 0) {
            return false;
        }

        User user = userRepository.getUserById(userId);
        if (user == null) {
            return false;
        }
        user.setBlocked(true);
        return true;
    }

    @Override
    public boolean unblockUser(String email) {
        if (activeUser.getRole() != Role.ADMIN) {
            return false;
        }
        if (!UserValidation.isEmailValid(email)) {
            return false;
        }
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        user.setBlocked(false);
        return true;
    }

    @Override
    public boolean unblockUser(int userId) {
        if (activeUser.getRole() != Role.ADMIN) {
            return false;
        }
        if (userId <= 0) {
            return false;
        }
        User user = userRepository.getUserById(userId);
        if (user == null) {
            return false;
        }
        user.setBlocked(false);
        return true;
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }

    @Override
    public boolean login(String email, String password) {
        // валидность почты и пароля
        if (!UserValidation.isEmailValid(email) || !UserValidation.isPasswordValid(password)) {
            return false;
        }
        // получаем пользователя
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        // сверяем пароль с репозиторием
        if (user.getPassword().equals(password)) {
            // делаем пользователя активным
            activeUser = user;
            return true;
        }
        return false;
    }

    @Override
    public boolean logout() {
        if (activeUser == null) {
            return false; // нет активного пользователя
        }
        activeUser = null;
        return true; //  выход
    }



    // Books

    @Override
    public Book createBook(String title, String author, String dateYear, String bookGenre) {
        if (title.isEmpty() && author.isEmpty() && dateYear.isEmpty() && bookGenre.isEmpty()) {
            return null;
        } else {
            return bookRepository.addBook(title, author, dateYear, bookGenre);
        }
    }

    @Override
    public Book getBookById(int id) {
        if (id > 0) {
            return bookRepository.getBookById(id);
        }
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        return bookRepository.getAvailableBooks();
    }

    @Override
    public MyList<Book> getBorrowedBooks() {
        return bookRepository.getBorrowedBooks();
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        if (!title.isEmpty()) {
            return bookRepository.getBooksByTitle(title);
        }
        return null;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        if (!author.isEmpty()) {
            return bookRepository.getBooksByAuthor(author);
        }
        return null;
    }

    @Override
    public boolean userGetBook(int bookId) {

        return false;
    }

    @Override
    public boolean userReturnBook(int bookId) {
        if (bookId > 0) {
            Book book = bookRepository.getBookById(bookId);
            if (book != null) {
                bookRepository.userReturnBook(bookId);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteBookById(int id) {
        if (id > 0) {
            return bookRepository.deleteBookById(id);
        }
        return false;
    }

    @Override
    public MyList<Book> getMyBooks(User user) {
        return bookRepository.getMyBooks(user);
    }

}
