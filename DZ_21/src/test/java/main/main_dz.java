package main;

import animals.Animal;
import birds.Duck;
import pets.Cat;
import pets.Dog;
import db.DatabaseManager;
import menu.Command;
import java.util.Scanner;


public class main_dz {



    public static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Введите команду (add/list/update/filter/exit): ");
                Command command = Command.fromString(scanner.nextLine());
                if (command == null) {
                    System.out.println("Неизвестная команда.");
                    continue;
                }

                switch (command) {
                    case ADD:
                        addAnimal(scanner);
                        break;
                    case LIST:
                        DatabaseManager.listAnimals(null);
                        break;
                    case UPDATE:
                        updateAnimal(scanner);
                        break;
                    case FILTER:
                        filterAnimals(scanner);
                        break;
                    case EXIT:
                        System.out.println("Выход из программы.");
                        return;
                }
            }
        }

        private static void addAnimal(Scanner scanner) {
            System.out.print("Какое животное? (cat/dog/duck): ");
            String type = scanner.nextLine().trim().toLowerCase();

            System.out.print("Введите имя: ");
            String name = scanner.nextLine().trim();

            System.out.print("Введите возраст: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введите вес: ");
            double weight = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Введите цвет: ");
            String color = scanner.nextLine().trim();

            Animal animal;
            switch (type) {
                case "cat":
                    animal = new Cat(name, age, weight, color);
                    break;
                case "dog":
                    animal = new Dog(name, age, weight, color);
                    break;
                case "duck":
                    animal = new Duck(name, age, weight, color);
                    break;
                default:
                    System.out.println("Неизвестное животное.");
                    return;
            }

            DatabaseManager.saveAnimal(animal, type);
            animal.say();
        }

        private static void updateAnimal(Scanner scanner) {
            System.out.print("Введите ID животного для редактирования: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введите новое имя: ");
            String name = scanner.nextLine().trim();

            System.out.print("Введите новый возраст: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введите новый вес: ");
            double weight = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Введите новый цвет: ");
            String color = scanner.nextLine().trim();

            System.out.print("Какой новый тип животного? (cat/dog/duck): ");
            String type = scanner.nextLine().trim().toLowerCase();

            Animal animal;
            switch (type) {
                case "cat":
                    animal = new Cat(name, age, weight, color);
                    break;
                case "dog":
                    animal = new Dog(name, age, weight, color);
                    break;
                case "duck":
                    animal = new Duck(name, age, weight, color);
                    break;
                default:
                    System.out.println("Неизвестное животное.");
                    return;
            }

            DatabaseManager.updateAnimal(id, animal, type);
        }

        private static void filterAnimals(Scanner scanner) {
            System.out.print("Введите тип животного для фильтрации (cat/dog/duck): ");
            String type = scanner.nextLine().trim().toLowerCase();
            DatabaseManager.listAnimals(type);
        }
    }

        }






