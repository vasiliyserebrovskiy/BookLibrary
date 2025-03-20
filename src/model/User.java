package model;

import utils.MyArrayList;
import utils.MyList;

import java.util.Objects;

import static utils.UserValidation.isEmailValid;


public class User {

    private String email; // Для авторизации
    private String password; // Для авторизации
    private final int userId; // Для поиска пользователя по id
    //private String name;
    private Role role;
    private final MyList<Book> myBooks; // Список книг, которые находятся у пользователя на руках


    public User(int id, String email, String password) {
        this.userId = id;
        this.email = email;; // проверки корректности
        this.password = password; // проверки
        this.role = Role.USER; // Только Администратор сможет менять тип доступа у пользователя!
        this.myBooks = new MyArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public MyList<Book> getMyBooks() {
        return myBooks;
    }

    @Override
    public String toString() {
        return  String.format("Пользователь { id = %d, email = \"%s\", роль = \"%s\" } ", userId, email, role);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role && Objects.equals(myBooks, user.myBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, userId, role, myBooks);
    }
}
