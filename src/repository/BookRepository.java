package repository;

import model.Book;
import model.User;
import utils.MyList;

public interface BookRepository {

    Book addBook(String title, String author, String dateYear, String bookGenre);

    Book getBookById(int id);

    MyList<Book> getAllBooks();

    MyList<Book> getAvailableBooks();

    MyList<Book> getBorrowedBooks();

    MyList<Book> getMyBooks(User user);

    MyList<Book> getBooksByTitle(String title); // contains
    MyList<Book> getBooksByAuthor(String author); // contains

    boolean updateBook(Book book);

//    boolean isBook(int id);

    void userReturnBook(int id);

    boolean deleteBookById(int id);

}
