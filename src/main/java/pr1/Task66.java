package pr1;

import java.util.Scanner;

/**
 * Задача 66 из задачника.
 * Кусочная функция f(x):
 *   f(x) = -x,      если x <= 0
 *   f(x) = x^2,     если 0 < x < 2
 *   f(x) = 4,       если x >= 2
 */
public class Task66 {
    
    /**
     * Вычисляет значение кусочной функции.
     * @param x аргумент
     * @return значение функции
     */
    public static double calculate(double x) {
        if (x <= 0) {
            return -x;
        } else if (x < 2) {
            return x * x;
        } else {
            return 4.0;
        }
    }
    
    public static void run(Scanner scanner) {
        System.out.println("\n=== Задача 66 ===");
        System.out.println("Кусочная функция f(x):");
        System.out.println("  f(x) = -x,    если x <= 0");
        System.out.println("  f(x) = x^2,   если 0 < x < 2");
        System.out.println("  f(x) = 4,     если x >= 2");
        
        double x = 0.0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print("Введите значение x: ");
                String input = scanner.nextLine();
                x = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите вещественное число.");
            }
        }
        
        double f = calculate(x);
        System.out.printf("f(%.4f) = %.6f\n", x, f);
        
        // Вывод пояснения
        if (x <= 0) {
            System.out.println("При x <= 0: f(x) = -x");
        } else if (x < 2) {
            System.out.println("При 0 < x < 2: f(x) = x^2");
        } else {
            System.out.println("При x >= 2: f(x) = 4");
        }
        System.out.println();
    }
}