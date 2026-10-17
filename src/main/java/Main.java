import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean running = true, caseRunning = false;
        final Library lib = new Library();
        try {
            lib.load();
        } catch (IOException e){
            System.out.println("Будет создан файл LIBRARY.txt!");
        } finally {
            System.out.println("Библиотека загружена с файла LIBRARY.txt!");
        }
        String name, author;
        while(running){
            menu();
            String input = scanner.nextLine();
            switch (input){
                case "1":
                    System.out.print("Введите пожалуйста автора книги: ");
                    author = scanner.nextLine();
                    System.out.print("Введите пожалуйста название книги: ");
                    name = scanner.nextLine();
                    lib.addBook(new Book(author, name));
                    System.out.println("\nДобавлена новая книга!");
                    break;

                case "2":
                    System.out.println(lib);
                    break;

                case "3":
                    caseRunning = true;
                    while(caseRunning){
                        System.out.println("Вот актуальный список:");
                        System.out.println(lib);
                        System.out.print("Введите пожалуйста название книги (для выхода введите 0):  ");
                        name = scanner.nextLine();
                        if ("0".equals(name)){
                            caseRunning = false;
                            break;
                        }
                        try {
                            lib.getBook(name);
                            caseRunning = false;
                        } catch (BookIsIssuedException e) {
                            System.out.println("Извините, вы не можете взять эту книгу (Она выдана или её нету)\n");
                        }
                    }
                    break;

                case "4":
                    caseRunning = true;
                    while(caseRunning){
                        System.out.println("Вот актуальный список:");
                        System.out.println(lib);
                        System.out.print("Введите пожалуйста название книги (для выхода введите 0):  ");
                        name = scanner.nextLine();
                        if ("0".equals(name)){
                            caseRunning = false;
                            break;
                        }
                        try {
                            lib.returnBook(name);
                            caseRunning = false;
                        } catch (BookIsIssuedException e) {
                            System.out.println("Извините, вы не можете вернуть эту книгу (Она свободна или её нету)\n");
                        }
                    }
                    break;

                case "0":
                    System.out.println("Завершение программы!");
                    running = false;
                    try {
                        lib.save();
                    } catch (IOException e) {
                        System.out.println("Ошибка! Не удалось сохранить в файл: " + e.getMessage());
                    }
                    break;
            }
        }
        scanner.close();
    }


    private static void menu(){
        System.out.println("Выберите действие:");
        System.out.println("1) Добавить книгу");
        System.out.println("2) Вывести список");
        System.out.println("3) Взять книгу");
        System.out.println("4) Вернуть книгу");
        System.out.println("0) Выход");
    }
}
