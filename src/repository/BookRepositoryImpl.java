package repository;

import model.Book;
import model.User;
import utils.MyArrayList;
import utils.MyList;

import java.nio.file.DirectoryStream;
import java.util.concurrent.atomic.AtomicInteger;


public class BookRepositoryImpl implements BookRepository {

    private final MyList<Book> books;
    private final AtomicInteger currentBookId = new AtomicInteger(1);

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        addStartBooks();
    }

    private void addStartBooks() {
        books.addAll(
                new Book(currentBookId.getAndIncrement(), "Войни и Мир", "Лев Николаевич Толстой", "1876", "роман") // ,
        );
    }

    @Override
    public Book addBook(String title, String author, String dateYear, String bookGenre) {
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
    public MyList<Book> getMyBooks(User user) {
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
    public boolean updateBook(Book book) {
        return false;
    }

    @Override
    public void userReturnBook(int id) {
        Book book = getBookById(id);
        book.setReadingUser(null);
    }

    @Override
    public boolean deleteBookById(int id) {
        return false;
    }
}
