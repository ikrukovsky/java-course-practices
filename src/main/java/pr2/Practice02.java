package pr2;

import java.util.Scanner;

/**
 * Практика 2. Ветвления, циклы, массивы
 * Вариант 11
 * Задачи: 49, 96, 136, 166, 196, 236
 * 
 * @author Круковский Иван Антонович
 * @group БОМ31ПРИ
 */
public class Practice02 {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("Практика 2. Ветвления, циклы, массивы");
        System.out.println("Студент: Круковский Иван Антонович");
        System.out.println("Группа: БОМ31ПРИ");
        System.out.println("Вариант: 11 (задачи: 49, 96, 136, 166, 196, 236)");
        System.out.println("============================================================");

        // Если переданы аргументы — работаем в режиме командной строки
        if (args.length > 0) {
            runFromArgs(args);
            return;
        }

        // Иначе — интерактивный режим через Scanner
        runInteractive();
    }

    /**
     * Интерактивный режим через Scanner (как раньше).
     */
    private static void runInteractive() {
        System.out.println("\n[Режим: интерактивный ввод через Scanner]");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            System.out.print("Выберите задачу (1-6) или 0 для выхода: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 0 до 6.\n");
                continue;
            }

            if (choice == 0) {
                System.out.println("Программа завершена.");
                break;
            }

            switch (choice) {
                case 1 -> task49(scanner);
                case 2 -> task96(scanner);
                case 3 -> task136(scanner);
                case 4 -> task166(scanner);
                case 5 -> task196(scanner);
                case 6 -> task236(scanner);
                default -> System.out.println("Ошибка: выберите число от 0 до 6.\n");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n--- Меню задач ---");
        System.out.println("1. Задача 49:  перераспределить A и B (min -> A, max -> B)");
        System.out.println("2. Задача 96:  приближённое значение arctg(x)");
        System.out.println("3. Задача 136: соседние числа Фибоначчи для N");
        System.out.println("4. Задача 166: макс. количество подряд идущих чётных");
        System.out.println("5. Задача 196: чередование чётных и нечётных");
        System.out.println("6. Задача 236: увеличить чётные на первое чётное");
        System.out.println("0. Выход");
        System.out.println("-------------------");
    }

    /**
     * Режим работы через аргументы командной строки.
     * Формат: java pr2.Practice02 <номер задачи> <данные...>
     */
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
            System.out.println("Ошибка: первый аргумент должен быть номером задачи (1-6).");
            return;
        }

        switch (taskNumber) {
            case 1 -> task49FromArgs(args);
            case 2 -> task96FromArgs(args);
            case 3 -> task136FromArgs(args);
            case 4 -> task166FromArgs(args);
            case 5 -> task196FromArgs(args);
            case 6 -> task236FromArgs(args);
            default -> System.out.println("Ошибка: неизвестный номер задачи: " + taskNumber);
        }
    }

    // ================== ЗАДАЧА 49 ==================
    /**
     * Перераспределяет значения: в A — меньшее, в B — большее.
     */
    public static void task49(Scanner scanner) {
        System.out.println("\n=== Задача 49 ===");
        System.out.println("Перераспределить A и B: A = min, B = max.");

        double a = readDouble(scanner, "Введите A: ");
        double b = readDouble(scanner, "Введите B: ");

        System.out.printf("До:    A = %.4f, B = %.4f\n", a, b);

        if (a > b) {
            double temp = a;
            a = b;
            b = temp;
        }

        System.out.printf("После: A = %.4f, B = %.4f\n", a, b);
    }

    // ================== ЗАДАЧА 96 ==================
    /**
     * Приближённое значение arctg(x):
     * x - x^3/3 + x^5/5 - ... + (-1)^N * x^(2N+1)/(2N+1)
     */
    public static double arctgApprox(double x, int n) {
        double sum = 0.0;
        for (int k = 0; k <= n; k++) {
            double term = Math.pow(x, 2 * k + 1) / (2 * k + 1);
            if (k % 2 == 0) {
                sum += term;
            } else {
                sum -= term;
            }
        }
        return sum;
    }

    public static void task96(Scanner scanner) {
        System.out.println("\n=== Задача 96 ===");
        System.out.println("Приближённое значение arctg(x).");

        double x;
        while (true) {
            x = readDouble(scanner, "Введите X (|X| < 1): ");
            if (Math.abs(x) < 1) break;
            System.out.println("Ошибка: |X| должно быть < 1.");
        }

        int n = readInt(scanner, "Введите N (> 0): ");
        if (n <= 0) {
            System.out.println("Ошибка: N должно быть > 0.");
            return;
        }

        double approx = arctgApprox(x, n);
        double exact = Math.atan(x);

        System.out.printf("Приближённое значение: %.10f\n", approx);
        System.out.printf("Точное (Math.atan):    %.10f\n", exact);
        System.out.printf("Разница:               %.10e\n", Math.abs(approx - exact));
    }

    // ================== ЗАДАЧА 136 ==================
    /**
     * Находит F(k-1) и F(k+1) для заданного числа Фибоначчи N = F(k).
     * Возвращает массив {F(k-1), F(k+1)} или null, если N не число Фибоначчи.
     */
    public static long[] fibNeighbors(long n) {
        if (n < 1) return null;

        long prev = 0;   // F(0) = 0
        long curr = 1;   // F(1) = 1

        // Если N == 1, то это F(1) или F(2), соседи: F(0)=0 и F(2)=1 или F(3)=2
        if (n == 1) {
            return new long[]{0, 1}; // для F(1): F(0)=0, F(2)=1
        }

        while (curr < n) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }

        if (curr == n) {
            // нашли: prev = F(k-1), next = curr + prev = F(k+1)
            return new long[]{prev, curr + prev};
        }
        return null;
    }

    public static void task136(Scanner scanner) {
        System.out.println("\n=== Задача 136 ===");
        System.out.println("Для числа Фибоначчи N найти F(k-1) и F(k+1).");

        long n = readLong(scanner, "Введите N (> 1, число Фибоначчи): ");
        if (n <= 1) {
            System.out.println("Ошибка: N должно быть > 1.");
            return;
        }

        long[] result = fibNeighbors(n);
        if (result == null) {
            System.out.println("Ошибка: " + n + " не является числом Фибоначчи.");
        } else {
            System.out.printf("F(k-1) = %d\n", result[0]);
            System.out.printf("N      = %d\n", n);
            System.out.printf("F(k+1) = %d\n", result[1]);
        }
    }

        // ================== ЗАДАЧА 166 ==================
    /**
     * Максимальное количество подряд идущих чётных чисел во всём массиве.
     * Перегруженный метод: вызывает версию с границами.
     */
    public static int maxConsecutiveEven(int[] arr) {
        return maxConsecutiveEven(arr, 0, arr.length - 1);
    }

    /**
     * Максимальное количество подряд идущих чётных чисел в диапазоне [from, to].
     * @param arr массив
     * @param from начальный индекс (0-based)
     * @param to   конечный индекс (0-based, включительно)
     */
    public static int maxConsecutiveEven(int[] arr, int from, int to) {
        int maxLen = 0;
        int curLen = 0;
        for (int i = from; i <= to; i++) {
            if (arr[i] % 2 == 0) {
                curLen++;
                if (curLen > maxLen) {
                    maxLen = curLen;
                }
            } else {
                curLen = 0;
            }
        }
        return maxLen;
    }

    public static void task166(Scanner scanner) {
        System.out.println("\n=== Задача 166 ===");
        System.out.println("Максимальное количество подряд идущих чётных чисел.");

        int n = readInt(scanner, "Введите N: ");
        if (n <= 0) {
            System.out.println("Ошибка: N должно быть > 0.");
            return;
        }

        int[] arr = readIntArray(scanner, n);

        // Перегруженный метод без границ
        int resultAll = maxConsecutiveEven(arr);
        System.out.println("Максимум по всему массиву: " + resultAll);

        // Демонстрация перегруженного метода с границами
        System.out.print("Проверить диапазон? (y/n): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        if (answer.equals("y")) {
            int from = readInt(scanner, "Введите from (1-based): ") - 1;
            int to = readInt(scanner, "Введите to (1-based): ") - 1;
            if (from < 0 || to >= arr.length || from > to) {
                System.out.println("Ошибка: некорректный диапазон.");
                return;
            }
            int resultRange = maxConsecutiveEven(arr, from, to);
            System.out.println("Максимум в диапазоне [" + (from + 1) + ", " + (to + 1) + "]: " + resultRange);
        }
    }

    // ================== ЗАДАЧА 196 ==================
    /**
     * Проверяет, чередуются ли чётные и нечётные числа.
     * Возвращает "ok", если чередуются, иначе — номер первого нарушителя (1-based).
     */
    public static String checkAlternatingParity(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            boolean prevEven = arr[i - 1] % 2 == 0;
            boolean currEven = arr[i] % 2 == 0;
            if (prevEven == currEven) {
                return String.valueOf(i + 1); // 1-based номер текущего элемента
            }
        }
        return "ok";
    }

    public static void task196(Scanner scanner) {
        System.out.println("\n=== Задача 196 ===");
        System.out.println("Проверка чередования чётных и нечётных чисел.");

        int n = readInt(scanner, "Введите N: ");
        if (n <= 0) {
            System.out.println("Ошибка: N должно быть > 0.");
            return;
        }

        int[] arr = readIntArray(scanner, n);
        String result = checkAlternatingParity(arr);
        if (result.equals("ok")) {
            System.out.println("ok");
        } else {
            System.out.println("Нарушение на позиции: " + result);
        }
    }

    // ================== ЗАДАЧА 236 ==================
    /**
     * Увеличивает все чётные числа массива на значение первого чётного.
     * Если чётных нет — массив не меняется.
     * Возвращает новый массив (или тот же, если чётных нет).
     */
    public static int[] increaseEvenByFirstEven(int[] arr) {
        int firstEven = 0;
        boolean found = false;
        for (int value : arr) {
            if (value % 2 == 0) {
                firstEven = value;
                found = true;
                break;
            }
        }

        if (!found) {
            return arr; // без изменений
        }

        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                result[i] = arr[i] + firstEven;
            } else {
                result[i] = arr[i];
            }
        }
        return result;
    }

    public static void task236(Scanner scanner) {
        System.out.println("\n=== Задача 236 ===");
        System.out.println("Увеличить все чётные числа на первое чётное.");

        int n = readInt(scanner, "Введите N: ");
        if (n <= 0) {
            System.out.println("Ошибка: N должно быть > 0.");
            return;
        }

        int[] arr = readIntArray(scanner, n);
        System.out.print("Исходный массив: ");
        printArray(arr);

        int[] result = increaseEvenByFirstEven(arr);
        System.out.print("Результат:       ");
        printArray(result);
    }

    // ================== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ==================

    static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите вещественное число.");
            }
        }
    }

    static int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    static long readLong(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    static int[] readIntArray(Scanner scanner, int n) {
        int[] arr = new int[n];
        System.out.println("Введите " + n + " целых чисел (каждое — с новой строки):");
        for (int i = 0; i < n; i++) {
            arr[i] = readInt(scanner, "  arr[" + (i + 1) + "]: ");
        }
        return arr;
    }

    static void printArray(int[] arr) {
        printArray(arr, 0, arr.length - 1);
    }

    static void printArray(int[] arr, int from, int to) {
        System.out.print("[");
        for (int i = from; i <= to; i++) {
            System.out.print(arr[i]);
            if (i < to) System.out.print(", ");
        }
        System.out.println("]");
    }

    // ================== ЗАПУСК ИЗ АРГУМЕНТОВ ==================

    private static void task49FromArgs(String[] args) {
        if (args.length < 3) {
            System.out.println("Ошибка: для задачи 49 нужно 2 аргумента — A и B.");
            System.out.println("Пример: java pr2.Practice02 1 7.5 2.3");
            return;
        }
        try {
            double a = Double.parseDouble(args[1]);
            double b = Double.parseDouble(args[2]);

            System.out.println("=== Задача 49 ===");
            System.out.printf("До:    A = %.4f, B = %.4f%n", a, b);
            if (a > b) {
                double temp = a;
                a = b;
                b = temp;
            }
            System.out.printf("После: A = %.4f, B = %.4f%n", a, b);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: A и B должны быть вещественными числами.");
        }
    }

    private static void task96FromArgs(String[] args) {
        if (args.length < 3) {
            System.out.println("Ошибка: для задачи 96 нужно 2 аргумента — X и N.");
            System.out.println("Пример: java pr2.Practice02 2 0.5 20");
            return;
        }
        try {
            double x = Double.parseDouble(args[1]);
            int n = Integer.parseInt(args[2]);

            if (Math.abs(x) >= 1) {
                System.out.println("Ошибка: |X| должно быть < 1.");
                return;
            }
            if (n <= 0) {
                System.out.println("Ошибка: N должно быть > 0.");
                return;
            }

            System.out.println("=== Задача 96 ===");
            double approx = arctgApprox(x, n);
            double exact = Math.atan(x);
            System.out.printf("Приближённое значение: %.10f%n", approx);
            System.out.printf("Точное (Math.atan):    %.10f%n", exact);
            System.out.printf("Разница:               %.10e%n", Math.abs(approx - exact));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: X должен быть числом, N — целым.");
        }
    }

    private static void task136FromArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("Ошибка: для задачи 136 нужен 1 аргумент — N.");
            System.out.println("Пример: java pr2.Practice02 3 13");
            return;
        }
        try {
            long n = Long.parseLong(args[1]);
            if (n <= 1) {
                System.out.println("Ошибка: N должно быть > 1.");
                return;
            }

            System.out.println("=== Задача 136 ===");
            long[] result = fibNeighbors(n);
            if (result == null) {
                System.out.println("Ошибка: " + n + " не является числом Фибоначчи.");
            } else {
                System.out.printf("F(k-1) = %d%n", result[0]);
                System.out.printf("N      = %d%n", n);
                System.out.printf("F(k+1) = %d%n", result[1]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: N должно быть целым числом.");
        }
    }

    private static void task166FromArgs(String[] args) {
        // args[0] = 4, args[1] = N, затем N чисел, опционально from и to
        if (args.length < 2) {
            System.out.println("Ошибка: для задачи 166 нужно указать N.");
            System.out.println("Пример (без границ): java pr2.Practice02 4 8 2 4 6 1 8 10 12 14");
            System.out.println("Пример (с границами): java pr2.Practice02 4 8 2 4 6 1 8 10 12 14 5 8");
            return;
        }

        try {
            int n = Integer.parseInt(args[1]);
            if (n <= 0) {
                System.out.println("Ошибка: N должно быть > 0.");
                return;
            }
            if (args.length < 2 + n) {
                System.out.println("Ошибка: нужно " + n + " чисел после N.");
                return;
            }

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(args[2 + i]);
            }

            System.out.println("=== Задача 166 ===");
            System.out.print("Массив: ");
            printArray(arr);

            // Проверяем: переданы ли from и to?
            int expectedWithoutBounds = 2 + n;
            int expectedWithBounds = 2 + n + 2;

            if (args.length == expectedWithoutBounds) {
                // Вариант 1: только массив — вызывается перегрузка без границ
                int resultAll = maxConsecutiveEven(arr);
                System.out.println("Максимум по всему массиву: " + resultAll);
            } else if (args.length == expectedWithBounds) {
                // Вариант 2: массив + границы — вызывается перегрузка с границами
                int from = Integer.parseInt(args[2 + n]) - 1;      // 1-based → 0-based
                int to = Integer.parseInt(args[2 + n + 1]) - 1;    // 1-based → 0-based

                if (from < 0 || to >= arr.length || from > to) {
                    System.out.println("Ошибка: некорректный диапазон [" + (from + 1) + ", " + (to + 1) + "]");
                    return;
                }

                int resultRange = maxConsecutiveEven(arr, from, to);
                System.out.println("Максимум в диапазоне [" + (from + 1) + ", " + (to + 1) + "]: " + resultRange);
            } else {
                System.out.println("Ошибка: неверное количество аргументов.");
                System.out.println("Ожидается: <номер> <N> <N чисел> [<from> <to>]");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: N, элементы массива и границы должны быть целыми числами.");
        }
    }

    private static void task196FromArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("Ошибка: для задачи 196 нужно указать N.");
            System.out.println("Пример: java pr2.Practice02 5 5 1 2 3 4 5");
            return;
        }
        try {
            int n = Integer.parseInt(args[1]);
            if (n <= 0) {
                System.out.println("Ошибка: N должно быть > 0.");
                return;
            }
            if (args.length < 2 + n) {
                System.out.println("Ошибка: нужно " + n + " чисел после N.");
                return;
            }

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(args[2 + i]);
            }

            System.out.println("=== Задача 196 ===");
            System.out.print("Массив: ");
            printArray(arr);
            String result = checkAlternatingParity(arr);
            if (result.equals("ok")) {
                System.out.println("ok");
            } else {
                System.out.println("Нарушение на позиции: " + result);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: N и элементы массива должны быть целыми числами.");
        }
    }

    private static void task236FromArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("Ошибка: для задачи 236 нужно указать N.");
            System.out.println("Пример: java pr2.Practice02 6 6 3 4 7 6 9 8");
            return;
        }
        try {
            int n = Integer.parseInt(args[1]);
            if (n <= 0) {
                System.out.println("Ошибка: N должно быть > 0.");
                return;
            }
            if (args.length < 2 + n) {
                System.out.println("Ошибка: нужно " + n + " чисел после N.");
                return;
            }

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(args[2 + i]);
            }

            System.out.println("=== Задача 236 ===");
            System.out.print("Исходный массив: ");
            printArray(arr);
            int[] result = increaseEvenByFirstEven(arr);
            System.out.print("Результат:       ");
            printArray(result);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: N и элементы массива должны быть целыми числами.");
        }
    }
}