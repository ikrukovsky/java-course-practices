package pr1;

import java.util.Scanner;

/**
 * Задача 40 из задачника.
 * Решение системы линейных уравнений:
 * A1*x + B1*y = C1
 * A2*x + B2*y = C2
 */
public class Task40 {
    
    /**
     * Решает систему линейных уравнений методом Крамера.
     * @param a1, b1, c1 коэффициенты первого уравнения
     * @param a2, b2, c2 коэффициенты второго уравнения
     * @return массив {x, y} или null, если система не имеет единственного решения
     */
    public static double[] solve(double a1, double b1, double c1,
                                  double a2, double b2, double c2) {
        double d = a1 * b2 - a2 * b1;  // главный определитель
        
        // Проверка: система должна иметь единственное решение
        if (Math.abs(d) < 1e-15) {
            return null;
        }
        
        double x = (c1 * b2 - c2 * b1) / d;
        double y = (a1 * c2 - a2 * c1) / d;
        
        return new double[]{x, y};
    }
    
    public static void run(Scanner scanner) {
        System.out.println("\n=== Задача 40 ===");
        System.out.println("Решение системы линейных уравнений:");
        System.out.println("  A1*x + B1*y = C1");
        System.out.println("  A2*x + B2*y = C2");
        
        double a1 = 0, b1 = 0, c1 = 0;
        double a2 = 0, b2 = 0, c2 = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print("Введите A1: ");
                a1 = Double.parseDouble(scanner.nextLine());
                System.out.print("Введите B1: ");
                b1 = Double.parseDouble(scanner.nextLine());
                System.out.print("Введите C1: ");
                c1 = Double.parseDouble(scanner.nextLine());
                System.out.print("Введите A2: ");
                a2 = Double.parseDouble(scanner.nextLine());
                System.out.print("Введите B2: ");
                b2 = Double.parseDouble(scanner.nextLine());
                System.out.print("Введите C2: ");
                c2 = Double.parseDouble(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите вещественные числа.");
            }
        }
        
        double[] result = solve(a1, b1, c1, a2, b2, c2);
        
        if (result == null) {
            System.out.println("Ошибка: система не имеет единственного решения (D = 0)");
        } else {
            System.out.println("\nРешение системы:");
            System.out.printf("  x = %.6f\n", result[0]);
            System.out.printf("  y = %.6f\n", result[1]);
            
            // Проверка подстановкой
            double left1 = a1 * result[0] + b1 * result[1];
            double left2 = a2 * result[0] + b2 * result[1];
            System.out.println("\nПроверка:");
            System.out.printf("  %,.2f * x + %,.2f * y = %,.6f (должно быть %,.2f)\n", 
                             a1, b1, left1, c1);
            System.out.printf("  %,.2f * x + %,.2f * y = %,.6f (должно быть %,.2f)\n",
                             a2, b2, left2, c2);
        }
        System.out.println();
    }
}