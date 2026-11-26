package pr6;

public class Booking {
  private final int roomNumber;
  private String guestName;

  public Booking(int roomNumber) {
    if (roomNumber <= 0) {
      throw new IllegalArgumentException("Номер комнаты должен быть положительным.");
    }
    this.roomNumber = roomNumber;
  }

  public int getRoomNumber() {
    return roomNumber;
  }

  public boolean isOccupied() {
    return guestName != null;
  }

  public String getGuestName() {
    return guestName;
  }

  public void book(String guest) throws RoomOccupiedException {
    if (isOccupied()) {
      throw new RoomOccupiedException(
          "Комната " + roomNumber + " уже занята гостем " + guestName + ".");
    }
    if (guest == null || guest.isBlank()) {
      throw new IllegalArgumentException("Имя гостя не должно быть пустым.");
    }
    guestName = guest.trim();
  }

  public void free() {
    guestName = null;
  }
}
