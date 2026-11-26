package pr6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class BookingTest {
  @TempDir Path tempDir;

  @Test
  void booksAnAvailableRoom() throws Exception {
    Booking booking = new Booking(101);
    booking.book("Иван");

    assertTrue(booking.isOccupied());
    assertEquals("Иван", booking.getGuestName());
  }

  @Test
  void rejectsBookingAnOccupiedRoom() throws Exception {
    Booking booking = new Booking(101);
    booking.book("Иван");

    assertThrows(RoomOccupiedException.class, () -> booking.book("Пётр"));
  }

  @Test
  void freesRoomForAnotherGuest() throws Exception {
    Booking booking = new Booking(101);
    booking.book("Иван");
    booking.free();
    booking.book("Пётр");

    assertEquals("Пётр", booking.getGuestName());
  }

  @Test
  void rejectsInvalidRoomNumber() {
    assertThrows(IllegalArgumentException.class, () -> new Booking(0));
  }

  @Test
  void parsesRoomNumber() {
    assertEquals(305, RoomParser.parseRoomNumber("305"));
  }

  @Test
  void rejectsInvalidRoomNumberText() {
    assertThrows(IllegalArgumentException.class, () -> RoomParser.parseRoomNumber("abc"));
  }

  @Test
  void writesSuccessfulOperationsToLog() throws Exception {
    Path log = tempDir.resolve("log.txt");
    RoomManager manager = new RoomManager(log);
    manager.book(12, "Анна");
    manager.free(12);

    String content = Files.readString(log);
    assertTrue(content.contains("Заселение"));
    assertTrue(content.contains("Освобождение"));
  }

  @Test
  void doesNotLogFailedBooking() throws Exception {
    Path log = tempDir.resolve("log.txt");
    RoomManager manager = new RoomManager(log);
    manager.book(12, "Анна");

    assertThrows(RoomOccupiedException.class, () -> manager.book(12, "Борис"));
    assertFalse(Files.readString(log).contains("Борис"));
  }
}
