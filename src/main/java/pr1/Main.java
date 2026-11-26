package pr1;

import java.util.Scanner;

/**
 * Практика 1. JDK и линейные вычисления
 * Вариант 11
 * Задачи: 26, 40, 66, 55
 * 
 * @author Круковский Иван Антонович
 * @group БОМ31ПРИ
 */
public class Main {

    public static void main(String[] args) {
        // Вывод ФИО, группы и номера варианта
        System.out.println("============================================================");
        System.out.println("Практика 1. JDK и линейные вычисления");
        System.out.println("Студент: Круковский Иван Антонович");
        System.out.println("Группа: БОМ31ПРИ");
        System.out.println("Вариант: 11 (задачи: 26, 40, 66, 55)");
        System.out.println("============================================================");

        if (args.length > 0) {
            runFromArgs(args);
            return;
        }

        // Иначе — интерактивный режим через Scanner
        runInteractive();
    }

    private static void runFromArgs(String[] args) {
        System.out.println("\n[Режим: аргументы командной строки]");
        System.out.println("Получено аргументов: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("  args[" + i + "] = " + args[i]);
        }
        System.out.println();

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: первый аргумент должен быть номером задачи (1-4).");
            return;
        }

        switch (taskNumber) {
            case 1 -> runTask26FromArgs(args);
            case 2 -> runTask40FromArgs(args);
            case 3 -> runTask66FromArgs(args);
            case 4 -> runTask55FromArgs(args);
            default -> System.out.println("Ошибка: неизвестный номер задачи: " + taskNumber);
        }
    }

    private static void runTask26FromArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("Ошибка: для задачи 26 нужен 1 аргумент — значение x.");
            System.out.println("Пример: java pr1.Main 1 4");
            return;
        }
        try {
            double x = Double.parseDouble(args[1]);
            System.out.println("=== Задача 26 ===");
            System.out.printf("При x = %.4f, y = %.6f%n", x, Task26.calculate(x));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: значение x должно быть числом.");
        }
    }

    private static void runTask40FromArgs(String[] args) {
        if (args.length < 7) {
            System.out.println("Ошибка: для задачи 40 нужно 6 аргументов — A1 B1 C1 A2 B2 C2.");
            System.out.println("Пример: java pr1.Main 2 2 3 8 3 2 7");
            return;
        }
        try {
            double a1 = Double.parseDouble(args[1]);
            double b1 = Double.parseDouble(args[2]);
            double c1 = Double.parseDouble(args[3]);
            double a2 = Double.parseDouble(args[4]);
            double b2 = Double.parseDouble(args[5]);
            double c2 = Double.parseDouble(args[6]);

            System.out.println("=== Задача 40 ===");
            double[] result = Task40.solve(a1, b1, c1, a2, b2, c2);
            if (result == null) {
                System.out.println("Система не имеет единственного решения (D = 0).");
            } else {
                System.out.printf("x = %.6f%n", result[0]);
                System.out.printf("y = %.6f%n", result[1]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: все коэффициенты должны быть числами.");
        }
    }

    private static void runTask66FromArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("Ошибка: для задачи 66 нужен 1 аргумент — значение x.");
            System.out.println("Пример: java pr1.Main 3 1.5");
            return;
        }
        try {
            double x = Double.parseDouble(args[1]);
            System.out.println("=== Задача 66 ===");
            System.out.printf("f(%.4f) = %.6f%n", x, Task66.calculate(x));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: значение x должно быть числом.");
        }
    }

    private static void runTask55FromArgs(String[] args) {
        if (args.length < 4) {
            System.out.println("Ошибка: для задачи 55 нужно 3 аргумента — A B C.");
            System.out.println("Пример: java pr1.Main 4 5 2 8");
            return;
        }
        try {
            double a = Double.parseDouble(args[1]);
            double b = Double.parseDouble(args[2]);
            double c = Double.parseDouble(args[3]);

            System.out.println("=== Задача 55 ===");
            double sum = Task55.sumOfTwoLargest(a, b, c);
            double[] largest = Task55.findTwoLargest(a, b, c);
            System.out.printf("Два наибольших: %.4f и %.4f%n", largest[0], largest[1]);
            System.out.printf("Сумма: %.4f%n", sum);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: все три числа должны быть числами.");
        }
    }

    /**
     * Интерактивный режим через Scanner.
     */
    private static void runInteractive() {
        System.out.println("\n[Режим: интерактивный ввод через Scanner]");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            System.out.print("Выберите задачу (1-4) или 0 для выхода: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 0 до 4.\n");
                continue;
            }

            if (choice == 0) {
                System.out.println("Программа завершена.");
                break;
            }

            switch (choice) {
                case 1 -> Task26.run(scanner);
                case 2 -> Task40.run(scanner);
                case 3 -> Task66.run(scanner);
                case 4 -> Task55.run(scanner);
                default -> System.out.println("Ошибка: выберите число от 0 до 4.\n");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n--- Меню задач ---");
        System.out.println("1. Задача 26: y = 4(x-3)^6 - 7(x-3)^3 + 2");
        System.out.println("2. Задача 40: система линейных уравнений");
        System.out.println("3. Задача 66: кусочная функция f(x)");
        System.out.println("4. Задача 55: сумма двух наибольших чисел");
        System.out.println("0. Выход");
        System.out.println("-------------------");
    }
}