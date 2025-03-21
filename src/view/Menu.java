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
            System.out.println("3  Меню администратора");
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
                //TODO: show car menu
                break;
            case 2:
                //TODO show user menu
                break;
            case 3:
                //TODO show admin menu
                break;
            default:
                System.out.println("Сделайте корректный выбор");
                waitRead();
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
