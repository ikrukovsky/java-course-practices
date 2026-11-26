package pr6;

public final class RoomParser {
  private RoomParser() {}

  public static int parseRoomNumber(String value) {
    try {
      int roomNumber = Integer.parseInt(value.trim());
      if (roomNumber <= 0) {
        throw new NumberFormatException("Номер должен быть положительным.");
      }
      return roomNumber;
    } catch (NumberFormatException exception) {
      throw new IllegalArgumentException(
          "Некорректный номер комнаты: \"" + value + "\".", exception);
    }
  }
}
