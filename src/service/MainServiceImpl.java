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
    public User updatePassword(String newPassword) {
        // валидность пароля
        if (!UserValidation.isPasswordValid(newPassword)) {
            return null;
        }
        return userRepository.updatePassword(activeUser.getEmail(), newPassword);
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
        return userRepository.blockUser(email);
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
        return userRepository.blockUser(userId);
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
        return userRepository.unblockUser(email);
    }

    @Override
    public User unblockUser(int userId) {
        if (activeUser.getRole() != Role.ADMIN) {
            return null;
        }
        if (userId <= 0) {
            return null;
        }
        User user = userRepository.getUserById(userId);
        if (user == null) {
            return null;
        }
        return userRepository.unblockUser(userId);
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
    public Book userGetBook(int bookId) {

        return null;
    }

    @Override
    public Book userReturnBook(int bookId) {
        if (bookId > 0) {
            Book book = bookRepository.getBookById(bookId);
            if (book != null) {
                Book book2 = bookRepository.userReturnBook(bookId);
                return book2;
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteBookById(int id) {
        if (id > 0) {
            return bookRepository.deleteBookById(id);
        }
        return false;
    }

    @Override
    public MyList<Book> getMyBooks() {
        return bookRepository.getMyBooks(activeUser);
    }

    @Override
    public User giveUserAdminRole(int id) {
        return null;
    }

    @Override
    public Book updateTitle(int id, String title) {
        if (id > 0 && !title.isEmpty()) {
            if (bookRepository.isBookExist(id)) {
                //проверяем что книга не на руках
                Book book = getBookById(id);
                if (book.getReadingUser() == null) {
                    return bookRepository.updateTitle(id, title);
                }
            }
        }
        return null;
    }

    @Override
    public Book updateAuthor(int id, String author) {
        if (id > 0 && !author.isEmpty()) {
            if (bookRepository.isBookExist(id)) {
                //проверяем что книга не на руках
                Book book = getBookById(id);
                if (book.getReadingUser() == null) {
                    return bookRepository.updateAuthor(id, author);
                }
            }
        }
        return null;
    }

    @Override
    public Book updateDateYear(int id, String dateYear) {
        if (id > 0 && !dateYear.isEmpty()) {
            if (bookRepository.isBookExist(id)) {
                //проверяем что книга не на руках
                Book book = getBookById(id);
                if (book.getReadingUser() == null) {
                    return bookRepository.updateDateYear(id, dateYear);
                }
            }
        }
        return null;
    }

    @Override
    public Book updateGenre(int id, String bookGenre) {
        if (id >0) {
            if(bookRepository.isBookExist(id)) {
                return bookRepository.getBookById(id);
            }
        }
        return null;
    }

    @Override
    public Book whoReadBook(int id) {
        return null;
    }
}
