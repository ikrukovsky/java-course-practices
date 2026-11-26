package pr4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PolymorphismTest {

  @Test
  void arrayOfTicketsHoldsDifferentSubtypes() {
    Ticket[] tickets = {
      new Train("Москва - Казань", 150000, 5),
      new Bus("Москва - Тула", 45050, 12),
      new Plane("Москва - Сочи", 890000, 23000)
    };

    assertEquals(3, tickets.length);
    assertTrue(tickets[0] instanceof Train);
    assertTrue(tickets[1] instanceof Bus);
    assertTrue(tickets[2] instanceof Plane);
  }

  @Test
  void polymorphicValidateGivesDifferentMessages() {
    Ticket train = new Train("Москва - Казань", 150000, 5);
    Ticket bus = new Bus("Москва - Тула", 45050, 12);
    Ticket plane = new Plane("Москва - Сочи", 890000, 23000);

    String trainMsg = train.validate();
    String busMsg = bus.validate();
    String planeMsg = plane.validate();

    // Каждый наследник даёт свою строку
    assertTrue(trainMsg.startsWith("Поезд"));
    assertTrue(busMsg.startsWith("Автобус"));
    assertTrue(planeMsg.startsWith("Самолёт"));
  }

  @Test
  void countPlanesViaInstanceof() {
    Ticket[] tickets = {
      new Train("Москва - Казань", 150000, 5),
      new Plane("Москва - Сочи", 890000, 23000),
      new Plane("Москва - Владивосток", 1550000, 15000),
      new Bus("Москва - Тула", 45050, 12)
    };

    int planeCount = 0;
    for (Ticket t : tickets) {
      if (t instanceof Plane) {
        planeCount++;
      }
    }
    assertEquals(2, planeCount);
  }

  @Test
  void totalBaggageViaPatternMatching() {
    Ticket[] tickets = {
      new Plane("Москва - Сочи", 890000, 23000),
      new Plane("Москва - Владивосток", 1550000, 15000),
      new Bus("Москва - Тула", 45050, 12)
    };

    int totalGrams = 0;
    for (Ticket t : tickets) {
      if (t instanceof Plane p) { // pattern matching
        totalGrams += p.getBaggageGrams();
      }
    }
    assertEquals(38000, totalGrams);
  }
}
