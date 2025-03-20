package repository;

import model.Book;
import model.User;
import utils.MyList;

public interface BookRepository {

    Book addBook(String title, String author);

    Book getBookById(int id);

    MyList<Book> getAllBooks();

    MyList<Book> getAvailableBooks();

    MyList<Book> getBorrowedBooks();

    MyList<Book> getMyBooks();

    MyList<Book> getBooksByTitle(String title); // contains
    MyList<Book> getBooksByAuthor(String author); // contains

    boolean updateBook(Book book);

    boolean deleteBookById(int id);

}
