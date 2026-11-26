package pr4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PlaneTest {

  @Test
  void constructorStoresValues() {
    Plane p = new Plane("Москва - Сочи", 890000, 23000);
    assertEquals("Москва - Сочи", p.getRoute());
    assertEquals(890000, p.getPriceKopecks());
    assertEquals(23000, p.getBaggageGrams());
  }

  @Test
  void constructorAllowsZeroBaggage() {
    // 0 г — допустимо
    Plane p = new Plane("Москва - Сочи", 890000, 0);
    assertEquals(0, p.getBaggageGrams());
  }

  @Test
  void constructorRejectsNegativeBaggage() {
    assertThrows(IllegalArgumentException.class, () -> new Plane("Москва - Сочи", 890000, -1));
  }

  @Test
  void validateContainsBaggageAndRoute() {
    Plane p = new Plane("Москва - Сочи", 890000, 23000);
    String msg = p.validate();
    assertTrue(msg.contains("Самолёт"));
    assertTrue(msg.contains("Москва - Сочи"));
    assertTrue(msg.contains("23000"));
    assertTrue(msg.contains("890000"));
  }
}
