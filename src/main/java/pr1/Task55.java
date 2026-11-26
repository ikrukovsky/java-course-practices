package pr1;

import java.util.Scanner;

/** Задача 55 из задачника. Известны три числа. Найти сумму двух наибольших из них. */
public class Task55 {

  /**
   * Находит сумму двух наибольших чисел из трёх.
   *
   * @param a первое число
   * @param b второе число
   * @param c третье число
   * @return сумма двух наибольших чисел
   */
  public static double sumOfTwoLargest(double a, double b, double c) {
    // Находим минимум и вычитаем его из суммы всех трёх чисел
    double min = Math.min(Math.min(a, b), c);
    return a + b + c - min;
  }

  /**
   * Альтернативный вариант нахождения двух наибольших чисел. Возвращает массив из двух наибольших
   * чисел.
   */
  public static double[] findTwoLargest(double a, double b, double c) {
    // Находим минимум
    double min = Math.min(Math.min(a, b), c);

    // Находим два наибольших
    double first, second;
    if (a == min) {
      first = Math.min(b, c);
      second = Math.max(b, c);
    } else if (b == min) {
      first = Math.min(a, c);
      second = Math.max(a, c);
    } else { // c == min
      first = Math.min(a, b);
      second = Math.max(a, b);
    }
    return new double[] {first, second};
  }

  public static void run(Scanner scanner) {
    System.out.println("\n=== Задача 55 ===");
    System.out.println("Найти сумму двух наибольших из трёх чисел.");

    double a = 0, b = 0, c = 0;
    boolean validInput = false;

    while (!validInput) {
      try {
        System.out.print("Введите первое число (A): ");
        a = Double.parseDouble(scanner.nextLine());
        System.out.print("Введите второе число (B): ");
        b = Double.parseDouble(scanner.nextLine());
        System.out.print("Введите третье число (C): ");
        c = Double.parseDouble(scanner.nextLine());
        validInput = true;
      } catch (NumberFormatException e) {
        System.out.println("Ошибка: введите вещественные числа.");
      }
    }

    System.out.println("\nИсходные числа: " + a + ", " + b + ", " + c);

    double sum = sumOfTwoLargest(a, b, c);
    double[] largest = findTwoLargest(a, b, c);

    System.out.printf("Два наибольших числа: %.4f и %.4f\n", largest[0], largest[1]);
    System.out.printf("Сумма двух наибольших: %.4f\n", sum);
    System.out.println();
  }
}
