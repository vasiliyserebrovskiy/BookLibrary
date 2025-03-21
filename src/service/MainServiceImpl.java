package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;


public class MainServiceImpl implements MainService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private User activeUser;

    public MainServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.activeUser = null;
    }

    @Override
    public User createUser(String email, String password) {
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
    public MyList<User> getAllUsers() {
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
    public Book createBook(String title, String author) {
        return null;
    }

    @Override
    public Book getBookById(int id) {
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        return null;
    }

    @Override
    public MyList<Book> getBorrowedBooks() {
        return null;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        return null;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        return null;
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public User getActiveUser() {
        return null;
    }

    @Override
    public boolean userGetBook(int bookId) {
        return false;
    }

    @Override
    public boolean userReturnBook(int bookId) {
        return false;
    }

    @Override
    public boolean deleteBookById(int id) {
        return false;
    }

    @Override
    public MyList<Book> getMyBooks(User user) {
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
    public boolean blockUser(int userId) {
        return false;
    }
}
