package pr4;


public class Ticket {

    // ================== ПОЛЯ ==================
    private final String route;      // маршрут (например, "Москва - Казань")
    private final int priceKopecks;  // цена в копейках

    // ================== КОНСТРУКТОР ==================
    public Ticket(String route, int priceKopecks) {
        if (route == null || route.isBlank()) {
            throw new IllegalArgumentException("Маршрут не может быть пустым");
        }
        if (priceKopecks < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        this.route = route;
        this.priceKopecks = priceKopecks;
    }

    // ================== GETTERS ==================
    public String getRoute() {
        return route;
    }

    public int getPriceKopecks() {
        return priceKopecks;
    }

    // ================== info() — final ==================
    /**
     * Метод info() объявлен final — наследники НЕ могут его переопределить.
     */
    public final String info() {
        return String.format("Маршрут: %s, цена: %d коп.", route, priceKopecks);
    }

    // ================== МЕТОД ПОВЕДЕНИЯ ==================
    /**
     * Проверка билета. Наследники переопределяют.
     */
    public String validate() {
        return "Билет действителен: " + route;
    }

    // ================== toString ==================
    @Override
    public String toString() {
        return info();
    }
}