package pr3;

/**
 * Практика 3. Классы, объекты, инкапсуляция
 * Вариант 11 — класс Room, поиск свободных комнат.
 * 
 * @author Круковский Иван Антонович
 * @group БОМ31ПРИ
 */
public class Practice03 {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("Практика 3. Классы, объекты, инкапсуляция");
        System.out.println("Студент: Круковский Иван Антонович");
        System.out.println("Группа: БОМ31ПРИ");
        System.out.println("Вариант: 11 (Room, поиск свободных комнат)");
        System.out.println("============================================================");

        RoomStorage storage = new RoomStorage();

        // ================== ДОБАВЛЯЕМ 5 КОМНАТ ==================
        System.out.println("\n--- Добавление комнат ---");

        storage.add(new Room(101, "Standard", 250000, false));
        storage.add(new Room(102, "Standard", 250000, true));
        storage.add(new Room(201, "Lux",     500000, false));
        storage.add(new Room(202, "Lux",     500000, true));
        storage.add(new Room(301, "Suite",   900000, false));

        // ================== ВЫВОД ВСЕХ ==================
        System.out.println("\n--- Все комнаты ---");
        storage.printAll();

        // ================== ПОИСК СВОБОДНЫХ ==================
        System.out.println("\n--- Поиск свободных комнат ---");
        storage.printFree();

        // ================== ПОИСК ПО НОМЕРУ ==================
        System.out.println("\n--- Поиск комнаты по номеру ---");
        Room found = storage.findByNumber(201);
        if (found != null) {
            System.out.println("Найдена комната: " + found);
        } else {
            System.out.println("Комната 201 не найдена.");
        }

        // ================== ДЕМОНСТРАЦИЯ equals ==================
        System.out.println("\n--- Проверка equals ---");
        Room r1 = new Room(101, "Standard", 250000, false);
        Room r2 = new Room(101, "Standard", 300000, true);  // цена и занятость другие
        System.out.println("r1 = " + r1);
        System.out.println("r2 = " + r2);
        System.out.println("r1.equals(r2) = " + r1.equals(r2) +
                           " (равны, т.к. совпадают номер и тип)");

        // ================== ПОВТОРНОЕ ДОБАВЛЕНИЕ ==================
        System.out.println("\n--- Попытка добавить дубликат ---");
        boolean added = storage.add(new Room(101, "Standard", 250000, false));
        System.out.println("Результат добавления: " + added);

        // ================== УДАЛЕНИЕ ПО НОМЕРУ ==================
        System.out.println("\n--- Удаление комнаты 102 ---");
        boolean removed = storage.removeByNumber(102);
        System.out.println("Результат удаления: " + removed);

        System.out.println("\n--- Комнаты после удаления ---");
        storage.printAll();

        // ================== ЕЩЁ ОДИН ПОИСК СВОБОДНЫХ ==================
        System.out.println("\n--- Свободные комнаты после удаления ---");
        storage.printFree();

        System.out.println("\n============================================================");
        System.out.println("Программа завершена.");
        System.out.println("============================================================");
    }
}