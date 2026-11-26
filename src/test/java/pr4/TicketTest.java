package pr4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TicketTest {

  @Test
  void constructorStoresValues() {
    Ticket t = new Ticket("Москва - Казань", 150000);
    assertEquals("Москва - Казань", t.getRoute());
    assertEquals(150000, t.getPriceKopecks());
  }

  @Test
  void constructorRejectsEmptyRoute() {
    assertThrows(IllegalArgumentException.class, () -> new Ticket("", 100));
    assertThrows(IllegalArgumentException.class, () -> new Ticket(null, 100));
    assertThrows(IllegalArgumentException.class, () -> new Ticket("   ", 100));
  }

  @Test
  void constructorRejectsNegativePrice() {
    assertThrows(IllegalArgumentException.class, () -> new Ticket("Москва - Тула", -1));
  }

  @Test
  void infoContainsRouteAndPrice() {
    Ticket t = new Ticket("Москва - Сочи", 890000);
    String info = t.info();
    // info() — final, поэтому проверяем, что он содержит нужные данные
    assertEquals(true, info.contains("Москва - Сочи"));
    assertEquals(true, info.contains("890000"));
  }

  @Test
  void validateReturnsDefaultMessage() {
    Ticket t = new Ticket("Москва - Казань", 150000);
    // Базовая реализация validate() — не пустая строка
    assertEquals(true, t.validate().contains("Москва - Казань"));
  }
}
