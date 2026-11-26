package pr4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TrainTest {

    @Test
    void constructorStoresValues() {
        Train t = new Train("Москва - Казань", 150000, 5);
        assertEquals("Москва - Казань", t.getRoute());
        assertEquals(150000, t.getPriceKopecks());
        assertEquals(5, t.getCar());
    }

    @Test
    void constructorRejectsNonPositiveCar() {
        assertThrows(IllegalArgumentException.class,
                () -> new Train("Москва - Казань", 150000, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new Train("Москва - Казань", 150000, -3));
    }

    @Test
    void validateContainsCarAndRoute() {
        Train t = new Train("Москва - Казань", 150000, 5);
        String msg = t.validate();
        assertTrue(msg.contains("Поезд"));
        assertTrue(msg.contains("Москва - Казань"));
        assertTrue(msg.contains("5"));
        assertTrue(msg.contains("150000"));
    }

    @Test
    void isSubclassOfTicket() {
        Ticket t = new Train("Москва - Казань", 150000, 5);
        // Проверяем наследование
        assertTrue(t instanceof Train);
        assertTrue(t instanceof Ticket);
    }
}