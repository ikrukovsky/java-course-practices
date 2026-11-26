package pr4;

/**
 * Практика 4. Наследование и полиморфизм
 * Вариант 11 — базовый класс Ticket, наследники Train, Bus, Plane.
 * 
 * @author Круковский Иван Антонович
 * @group БОМ31ПРИ
 */
public class Practice04 {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("Практика 4. Наследование и полиморфизм");
        System.out.println("Студент: Круковский Иван Антонович");
        System.out.println("Группа: БОМ31ПРИ");
        System.out.println("Вариант: 11 (Ticket -> Train, Bus, Plane)");
        System.out.println("============================================================");

        // ================== МАССИВ БАЗОВОГО ТИПА ==================
        Ticket[] tickets = {
            new Train("Москва - Казань",        150000, 5),   // 1500 руб. 00 коп.
            new Bus("Москва - Тула",            45050,  12),  // 450 руб. 50 коп.
            new Plane("Москва - Сочи",          890000, 23000), // багаж 23 кг
            new Train("Санкт-Петербург - Москва", 220000, 8),
            new Bus("Казань - Самара",          38075,  7),
            new Plane("Москва - Владивосток",   1550000, 15000)
        };

        // ================== ПОЛИМОРФНЫЕ ВЫЗОВЫ ==================
        System.out.println("\n--- Все билеты (полиморфный вызов validate()) ---");
        for (Ticket t : tickets) {
            System.out.println("  " + t.validate());
        }

        // ================== ВЫВОД info() ==================
        System.out.println("\n--- Информация о билетах (final info()) ---");
        for (int i = 0; i < tickets.length; i++) {
            System.out.println("  " + (i + 1) + ". " + tickets[i].info());
        }

        // ================== ОБРАБОТКА ОДНОГО ТИПА ЧЕРЕЗ instanceof ==================
        System.out.println("\n--- Обработка билетов на самолёт (instanceof) ---");
        int planeCount = 0;
        int totalBaggageGrams = 0;
        for (Ticket t : tickets) {
            if (t instanceof Plane p) {  // pattern matching (Java 16+)
                planeCount++;
                totalBaggageGrams += p.getBaggageGrams();
                System.out.printf(
                    "  Самолёт %s: багаж %d г, цена %d коп.%n",
                    p.getRoute(), p.getBaggageGrams(), p.getPriceKopecks()
                );
            }
        }
        System.out.println("Всего билетов на самолёт: " + planeCount);
        System.out.println("Суммарный вес багажа: " + totalBaggageGrams + " г");

        // ================== ОБРАБОТКА ВСЕХ ТИПОВ ЧЕРЕЗ instanceof ==================
        System.out.println("\n--- Статистика по типам билетов ---");
        int trainCount = 0, busCount = 0, planeCount2 = 0;
        for (Ticket t : tickets) {
            if (t instanceof Train) {
                trainCount++;
            } else if (t instanceof Bus) {
                busCount++;
            } else if (t instanceof Plane) {
                planeCount2++;
            }
        }
        System.out.println("  Поезд:   " + trainCount);
        System.out.println("  Автобус: " + busCount);
        System.out.println("  Самолёт: " + planeCount2);

        // ================== ДЕМОНСТРАЦИЯ final info() ==================
        System.out.println("\n--- Демонстрация final info() ---");
        System.out.println("Метод info() объявлен final в базовом классе Ticket,");
        System.out.println("поэтому наследники НЕ могут его переопределить.");
        System.out.println("Пример вызова: " + tickets[0].info());

        System.out.println("\n============================================================");
        System.out.println("Программа завершена.");
        System.out.println("============================================================");
    }
}