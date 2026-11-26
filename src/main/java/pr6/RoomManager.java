package pr6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class RoomManager {
  private final Map<Integer, Booking> rooms = new LinkedHashMap<>();
  private final Path logPath;

  public RoomManager(Path logPath) {
    this.logPath = logPath;
  }

  public Booking getOrCreateRoom(int roomNumber) {
    if (roomNumber <= 0) {
      throw new IllegalArgumentException("Номер комнаты должен быть положительным.");
    }
    return rooms.computeIfAbsent(roomNumber, Booking::new);
  }

  public void book(int roomNumber, String guest) throws RoomOccupiedException, IOException {
    Booking booking = getOrCreateRoom(roomNumber);
    booking.book(guest);
    log("Заселение: комната " + roomNumber + ", гость " + guest);
  }

  public void free(int roomNumber) throws IOException {
    Booking booking = getOrCreateRoom(roomNumber);
    if (!booking.isOccupied()) {
      throw new IllegalStateException("Комната " + roomNumber + " уже свободна.");
    }
    String guest = booking.getGuestName();
    booking.free();
    log("Освобождение: комната " + roomNumber + ", прежний гость " + guest);
  }

  public String status(int roomNumber) {
    Booking booking = getOrCreateRoom(roomNumber);
    return booking.isOccupied()
        ? "Комната " + roomNumber + " занята: " + booking.getGuestName()
        : "Комната " + roomNumber + " свободна.";
  }

  private void log(String message) throws IOException {
    String line = LocalDateTime.now() + " | " + message + System.lineSeparator();
    Files.writeString(
        logPath,
        line,
        StandardCharsets.UTF_8,
        StandardOpenOption.CREATE,
        StandardOpenOption.APPEND);
  }
}
