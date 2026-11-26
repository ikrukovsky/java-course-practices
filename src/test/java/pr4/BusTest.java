package pr4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BusTest {

  @Test
  void constructorStoresValues() {
    Bus b = new Bus("Москва - Тула", 45050, 12);
    assertEquals("Москва - Тула", b.getRoute());
    assertEquals(45050, b.getPriceKopecks());
    assertEquals(12, b.getSeat());
  }

  @Test
  void constructorRejectsNonPositiveSeat() {
    assertThrows(IllegalArgumentException.class, () -> new Bus("Москва - Тула", 45050, 0));
    assertThrows(IllegalArgumentException.class, () -> new Bus("Москва - Тула", 45050, -1));
  }

  @Test
  void validateContainsSeatAndRoute() {
    Bus b = new Bus("Москва - Тула", 45050, 12);
    String msg = b.validate();
    assertTrue(msg.contains("Автобус"));
    assertTrue(msg.contains("Москва - Тула"));
    assertTrue(msg.contains("12"));
    assertTrue(msg.contains("45050"));
  }
}
