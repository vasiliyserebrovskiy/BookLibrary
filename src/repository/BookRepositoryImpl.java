package repository;

import model.Book;
import utils.MyList;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (20.03.2025)
 */
public class BookRepositoryImpl implements BookRepository {
    @Override
    public Book addBook(String title, String author) {
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
    public MyList<Book> getMyBooks() {
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
    public boolean deleteBookById(int id) {
        return false;
    }
}
