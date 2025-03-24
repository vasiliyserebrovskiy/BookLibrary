
package service;

import model.Book;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import utils.MyList;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MainServiceTests {

    BookRepository bookRepository;
    UserRepository userRepository;
    MainService service;

    @BeforeEach
    void setUp() {
        // Метод создающий изначальную архитектуру системы перед каждым тестом
        bookRepository = new BookRepositoryImpl();
        userRepository = new UserRepositoryImpl();
        service = new MainServiceImpl(bookRepository, userRepository);
    }

    // Тесты для методов по пользователю -------------------
    // Проверка метода createUser --------------------------------------------------------------
    @ParameterizedTest
    @MethodSource("testCreateUser")
    void testValidCreateUser(String email, String password) {

        // int id = 6;
        User user = service.createUser(email, password);
        assertNotEquals(null, user); //Объект должен был создаться
        assertEquals(email, user.getEmail()); // проверка email
        assertEquals(password, user.getPassword()); //проверка пароля
        //assertEquals(user.getUserId(),id);
    }

    static Stream<Arguments> testCreateUser() {
        return Stream.of(
                Arguments.of("test@test.com", "P@ssw0rd"),
                Arguments.of("test2@test.ru", "P@ssw0rd1"),
                Arguments.of("test34@mail.ru", "P@ssw0rd12")
        );
    }

    // Попытка заведения пользователя с некорректными данными
    @ParameterizedTest
    @MethodSource("testNotCreateUser")
    void testNotValidCreateUser(String email, String password) {

        User user = service.createUser(email, password);

        assertNull(user); //Пользователь не должен был создаться

    }

    static Stream<Arguments> testNotCreateUser() {
        return Stream.of(
                Arguments.of("test@@test.com", "P@ssw0rd"),
                Arguments.of("1test2@mail.ru", "1qazXsw@"),
                Arguments.of("test2@mailr.u", "1qazXsw@"),
                Arguments.of("test2@mail.ru.", "1qazXsw@"),
                Arguments.of("richy@richy.de", "qwerty"),
                Arguments.of("richy@richy.de", "QwertY"),
                Arguments.of("richy@richy.de", "Q!ertY"),
                Arguments.of("richy@richy.de", "Q1ertY"),
                Arguments.of("richy@richy.de", "Qw1@r")
        );
    }

    // Тестируем метод getUserByEmail

    // Тестируем метод getUserById

    //Тестируем метод getAllUsers


    //Проверка метода updatePassword
    @ParameterizedTest
    @MethodSource("testUpdatePassword")
    void testValidUpdatePassword(String email, String password, String newPassword) {

        // Пользователь должен быть залогиненым, для смены пароля
        service.login(email, password); // логинемся
        User user = service.updatePassword(newPassword);
        assertEquals(newPassword, user.getPassword()); //проверка пароля
    }

    static Stream<Arguments> testUpdatePassword() {
        return Stream.of(
                Arguments.of("user2@example.com", "Secure*987", "P@ssw0rd1"),
                Arguments.of("user3@example.com", "TestUser@22", "P@ssw0rd@"),
                Arguments.of("user4@example.com", "Pa$$w0rd99", "Qwert!y1")
        );
    }

    // Попытка заведения пользователя с некорректными данными
    @ParameterizedTest
    @MethodSource("testNotUpdatePassword")
    void testNotValidUpdatePassword(String email, String password, String newPassword) {

        // Пользователь должен быть залогиненым, для смены пароля
        service.login(email, password); // логинемся
        User user = service.updatePassword(newPassword);
        assertNull(user); //проверка пароля

    }

    static Stream<Arguments> testNotUpdatePassword() {
        return Stream.of(
                Arguments.of("user2@example.com", "Secure*987", "qwerty"),
                Arguments.of("user2@example.com", "Secure*987", "qweRty"),
                Arguments.of("user3@example.com", "TestUser@22", "Qwer3ty"),
                Arguments.of("user3@example.com", "TestUser@22", "QWE@R$TY"),
                Arguments.of("user4@example.com", "Pa$$w0rd99", "qwerty"),
                Arguments.of("user4@example.com", "Pa$$w0rd99", "Qw1@e")
        );
    }

    // Тестируем метод deleteUser

    // Тестируем метод giveUserAdminRole

    //Проверка метода login() -------------------------------------------------------
    @ParameterizedTest
    @MethodSource("testLogin")
    void testValidLogin(String email, String password) {

        // Пользователь должен быть залогиненым, для смены пароля
        boolean isLogin = service.login(email, password); // логинемся
        assertEquals(true, isLogin);
        assertNotNull(service.getActiveUser());
    }

    static Stream<Arguments> testLogin() {
        return Stream.of(
                Arguments.of("user2@example.com", "Secure*987"),
                Arguments.of("user3@example.com", "TestUser@22"),
                Arguments.of("user4@example.com", "Pa$$w0rd99")
        );
    }

    @ParameterizedTest
    @MethodSource("testNotLogin")
    void testNotValidLogin(String email, String password) {

        // Пользователь должен быть залогиненым, для смены пароля
        boolean isLogin = service.login(email, password); // логинемся
        assertEquals(false, isLogin);
        assertNull(service.getActiveUser());
    }

    static Stream<Arguments> testNotLogin() {
        return Stream.of(
                Arguments.of("user2@example.com", "P@ssw0rd"),
                Arguments.of("user3@example.com", "Pqssw0rd"),
                Arguments.of("user4@example.com", "P@ssw0rd345")
        );
    }

    // Проверка метода logout ---------------------------------------------------------------------------
    // В нашем случае некорректный logout не проверить. Он слишком простой.
    @ParameterizedTest
    @MethodSource("testLogout")
    void testValidLogout(String email, String password) {

        // Пользователь должен быть залогиненым, для смены пароля
        service.login(email, password); // логинемся
        service.logout();
        assertNull(service.getActiveUser());
    }

    static Stream<Arguments> testLogout() {
        return Stream.of(
                Arguments.of("user2@example.com", "Secure*987"),
                Arguments.of("user3@example.com", "TestUser@22"),
                Arguments.of("user4@example.com", "Pa$$w0rd99")
        );
    }

    // Проверка метода getActiveUser
    @ParameterizedTest
    @MethodSource("testValidGetActiveUser")
    void testValidGetActiveUser(String email, String password) {

        service.login(email, password);
        User expectedUser = service.getUserByEmail(email);
        User actualUser = service.getActiveUser();
        assertEquals(expectedUser, actualUser);
        assertEquals(expectedUser.getUserId(), actualUser.getUserId());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getRole(), actualUser.getRole());

    }

    static Stream<Arguments> testValidGetActiveUser() {
        return Stream.of(
                Arguments.of("user2@example.com", "Secure*987"),
                Arguments.of("user3@example.com", "TestUser@22"),
                Arguments.of("user4@example.com", "Pa$$w0rd99")
        );
    }

    @ParameterizedTest
    @MethodSource("testNotValidGetActiveUser")
    void testNotValidGetActiveUser(String email, String password) {

        service.login(email, password);
        // User expectedUser = service.getUserByEmail(email);
        User actualUser = service.getActiveUser();
        assertNull(actualUser);

    }

    static Stream<Arguments> testNotValidGetActiveUser() {
        return Stream.of(
                Arguments.of("user5@example.com", "Secure*987"),
                Arguments.of("user6@example.com", "TestUser@22"),
                Arguments.of("user7@example.com", "Pa$$w0rd99")
        );
    }

    // Тестируем метод unblockUser by email

    // Тестируем метод unblockUser by id

    // Тестируем метод blockUser by email

    // Тестируем метод blockUser by id

    // ----------------------------------------------------------------------------------------------
    // ТЕСТЫ МЕТОДОВ ПО КНИГАМ
    @Test
    void testGetAllBook() {
        MyList<Book> books = service.getAllBooks();
        assertNotNull(books);
    }

    @Test
    void testGetAvailableBooks() {
        boolean login = service.login("user2@example.com", "Secure*987");
        //System.out.println(login);
        Book book = service.userGetBook(1);
        Book book2 = service.userGetBook(7);
//        System.out.println(book);
//        System.out.println(book2);
        MyList<Book> books = service.getAvailableBooks();

        assertEquals(9, books.size());
    }

    @Test
    void testGetAvailableBooks2() {
        boolean login = service.login("user2@example.com", "Secure*987");
        //System.out.println(login);

        MyList<Book> books = service.getAllBooks();
        for (int i = 1; i <= books.size(); i++) {
            service.userGetBook(i);
        }
        MyList<Book> availabldeBook = service.getAvailableBooks();
        assertEquals(0, availabldeBook.size());
    }

    // Тестируем метод getBorrowedBooks
    @Test
    void testGetBorrowedBooks() {
        boolean login = service.login("user2@example.com", "Secure*987");
        MyList<Book> borrowedBook = service.getBorrowedBooks();
        assertEquals(0, borrowedBook.size());
    }

    @Test
    void testGetBorrowedBooks2() {
        boolean login = service.login("user2@example.com", "Secure*987");
        Book book = service.userGetBook(1);
        Book book2 = service.userGetBook(7);
        MyList<Book> borrowedBook = service.getBorrowedBooks();

        assertEquals(2, borrowedBook.size());
    }

    // Тестируем метод getMyBooks
    @Test
    void testGetMyBook() {
        boolean login = service.login("user2@example.com", "Secure*987");
        MyList<Book> borrowedBook = service.getMyBooks();

        assertEquals(0, borrowedBook.size());
    }
    @Test
    void testGetMyBook2() {
        boolean login = service.login("user2@example.com", "Secure*987");
        Book book = service.userGetBook(1);
        Book book2 = service.userGetBook(7);
        Book book3 = service.userGetBook(8);
        MyList<Book> borrowedBook = service.getMyBooks();

        assertEquals(3, borrowedBook.size());
    }

    // Тестирование метода createBook
    @ParameterizedTest
    @MethodSource("testValidCreateBook")
    void testValidCreateBook(String title, String author, String dateYear, String bookGenre) {

        boolean login = service.login("1", "1");
        // User expectedUser = service.getUserByEmail(email);
        Book book = service.createBook(title, author, dateYear, bookGenre);
        assertNotNull(book);
    }

    static Stream<Arguments> testValidCreateBook() {
        return Stream.of(
                Arguments.of("Test1","Test Author","1950","Horror"),
                Arguments.of("Test2","Test Author","2000","Komedy"),
                Arguments.of("Test3","Test Author","2020","Роман")
        );
    }

    @ParameterizedTest
    @MethodSource("testNotValidCreateBook")
    void testNotValidCreateBook(String title, String author, String dateYear, String bookGenre) {

        boolean login = service.login("1", "1");
        // User expectedUser = service.getUserByEmail(email);
        Book book = service.createBook(title, author, dateYear, bookGenre);
        assertNull(book);
    }

    static Stream<Arguments> testNotValidCreateBook() {
        return Stream.of(
                Arguments.of("","Test Author","1950","Horror"),
                Arguments.of("Test2","","2000","Komedy"),
                Arguments.of("Test3","Test Author","","Роман"),
                Arguments.of("","","",""),
                Arguments.of("Test3","Test Author","2020","")
        );
    }

    // Тестируем метод getBookById
    @ParameterizedTest
    @ValueSource(ints = {1,4,9})
    void testValidGetBookById(int id) {
        Book book = service.getBookById(id);
        assertNotNull(book);
    }
    @ParameterizedTest
    @ValueSource(ints = {15,42,96})
    void testNotValidGetBookById(int id) {
        Book book = service.getBookById(id);
        assertNull(book);
    }

    //Тестируем метод getBooksByTitle

    // Тестируем метод getBooksByAuthor

    // Тестируем метод userGetBook

    // Тестируем метод userReturnBook

    // Тестируем метод whoReadBook

    // Тестируем метод deleteBookById

    // Тестируем метод updateTitle

    // Тестируем метод updateAuthor

    // Тестируем метод updateDateYear

    // Тестируем метод updateGenre

} // END OF

