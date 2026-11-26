package pr1;

import java.util.Scanner;

/**
 * Задача 26 из задачника.
 * Найти значение функции y = 4(x-3)^6 - 7(x-3)^3 + 2 при данном значении x.
 */
public class Task26 {
    
    /**
     * Вычисляет значение функции y = 4(x-3)^6 - 7(x-3)^3 + 2
     * @param x значение аргумента
     * @return значение функции
     */
    public static double calculate(double x) {
        double t = x - 3;
        double t3 = t * t * t;      // (x-3)^3
        double t6 = t3 * t3;        // (x-3)^6
        return 4 * t6 - 7 * t3 + 2;
    }
    
    public static void run(Scanner scanner) {
        System.out.println("\n=== Задача 26 ===");
        System.out.println("y = 4(x-3)^6 - 7(x-3)^3 + 2");
        
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
        
        double y = calculate(x);
        System.out.printf("При x = %.4f, y = %.6f\n", x, y);
        System.out.println("Результат сохранён: y = " + y);
        System.out.println();
    }
}