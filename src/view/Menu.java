package view;

import model.Role;
import model.User;
import service.MainService;
import utils.MyList;

import java.awt.print.Book;
import java.util.Scanner;

public class Menu {

    private final MainService service;
    private Scanner scanner = new Scanner(System.in);

    public Menu(MainService service) {
        this.service = service;
    }

    public void start() {
        showMainMenu();
    }

    private void showMainMenu() {
        while (true) {

            System.out.println("Добро пожаловать в библиотеку \"Знание Века\"");
            System.out.println("1 Меню книги");
            System.out.println("2 Меню пользователя");
            System.out.println("3 Меню администратора");
            System.out.println("0 Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания!");
                // Завершение работы приложения
                System.exit(0);
            }

            showSubMenu(choice);

        }
    }

    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                //TODO: show book menu
                showBookMenu();
                break;
            case 2:
                //TODO show user menu
                showUserMenu();
                break;
            case 3:
                //TODO show admin menu
                showAdminMenu();
                break;
            default:
                System.out.println("Сделайте корректный выбор");
                waitRead();
        }
    }

    // Меню книг
    private void showBookMenu() {
        while(true) {

            if (service.getActiveUser() == null) {
                System.out.println("Меню книг:");
                System.out.println("1 Список всех книг");
                System.out.println("2 Поиск книги по названию");
                System.out.println("3 Поиск книги по автору");
                System.out.println("0 Выход в предыдущее меню");

            } else {
                System.out.println("Меню книг:");
                System.out.println("1 Список всех книг");
                System.out.println("2 Поиск книги по названию");
                System.out.println("3 Поиск книги по автору");
                System.out.println("4 Список всех свободных книг");
                System.out.println("5 Список всех взятых книг");
                System.out.println("6 Список моих книг");
                System.out.println("7 Взять книгу");
                System.out.println("8 Вернуть книгу");
                System.out.println("9 Кто читает книгу");
                System.out.println("0 Выйти в предыдущее меню");
                System.out.println("\n Выберите номер пункта меню");
            }

            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;

            handleBookMenuInput(input);
        }
    }

    private void handleBookMenuInput(int input) {
         switch(input) {
             case 1:
                 //TODO Список всех книг
                 break;
             case 2:
                 //TODO Поиск книг по названию
                 break;
             case 3:
                 //TODO поиск книги по автору
                 break;
             case 4:
                 //TODO Список всех свободных книг
                 break;
             case 5:
                 //TODO список всех взятых книг
                 break;
             case 6:
                 //TODO Списко моих книг
                 break;
             case 7:
                 //TODO Взять книгу
                 break;
             case 8:
                 //TODO вернуть книгу
                 break;
             case 9:
                 //TODO кто читает книгу
                 break;

        }
    }

    //Меную пользователя
    private void showUserMenu() {
        while(true) {

            if (service.getActiveUser() == null) {
                System.out.println("Меню пользователя:");
                System.out.println("1 Вход в систему");
                System.out.println("2 Зарегистрироваться");
                System.out.println("0 Выйти в предыдущее меню");
            } else {
                System.out.println("Меню пользователя:");
                System.out.println("1 Вход в систему");
                System.out.println("2 Зарегистрироваться");
                System.out.println("3 Изменить пароль");
                System.out.println("4 Выйти");
                System.out.println("0 Выйти в предыдущее меню");
            }

            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;

            handleUserMenuInput(input);
        }
    }

    private void handleUserMenuInput(int input) {
        switch (input) {
            case 1:
                //TODO Вход в систему
                break;
            case 2:
                //TODO Зарегистрироваться
                break;
            case 3:
                //TODO изменить пароль
                break;
            case 4:
                //TODO выйти logout
                break;
        }
    }

    // Меню админа
    private void showAdminMenu() {
        while (true) {
            if (service.getActiveUser() != null) {
                if ( service.getActiveUser().getRole() == Role.ADMIN) {
                    System.out.println("Меню администратора");
                    System.out.println("1 Добавить книгу");
                    System.out.println("2 Редактировать информацию о книге");
                    System.out.println("3 Разблокировать пользователя");
                    System.out.println("4 Дать пользователю права администратора");
                    System.out.println("5 Список всех пользователей");
                    System.out.println("0 Выйти в предыдущее меню");
                } else {
                    System.out.println("Простите, Вы не являетесь администратором.");
                    waitRead();
                    break;
                }

            } else {
                System.out.println("Простите, Вы не вошли в систему.");
                waitRead();
                break;
            }

            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;

            handleAdminMenuInput(input);
        }
    }

    private void handleAdminMenuInput(int input) {
        switch (input) {
            case 1:
                //TODO добавить книгу
                break;
            case 2:
                //TODO редактировать информацию о книге
                break;
            case 3:
                //TODO разблокировать пользователя
                break;
            case 4:
                //TODO Дать пользователю права администратора
                break;
            case 5:
                //TODO список всех пользователей
                break;
        }
    }

    // Печать пользователей
    private void showBooksList(MyList<Book> books) {
        for (Book book : books) {
            //System.out.printf("%d. %s (%d г. выпуска). Цена 2.%f\n", );
        }
    }

    // Печать пользователей
    private void showUsersList(MyList<User> users) {
        for (User user : users) {
            //System.out.printf("%d. %s (%d г. выпуска). Цена 2.%f\n", );
        }
    }

    // Задержка в меню
    private void waitRead() {
        System.out.println("\n Для продолжения нажмите Enter");
        scanner.nextLine();
    }


} // END CLASS MENU
