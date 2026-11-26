package pr3;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранилище комнат отеля. Использует ArrayList для хранения.
 *
 * @author Круковский Иван Антонович
 * @group БОМ31ПРИ
 */
public class RoomStorage {

  private final List<Room> rooms = new ArrayList<>();

  /**
   * Добавить комнату в хранилище.
   *
   * @param room комната
   * @return true, если добавлено
   */
  public boolean add(Room room) {
    if (room == null) {
      return false;
    }
    if (rooms.contains(room)) {
      System.out.println("Комната уже есть в хранилище: " + room);
      return false;
    }
    rooms.add(room);
    return true;
  }

  /**
   * Удалить комнату из хранилища.
   *
   * @param room комната
   * @return true, если удалено
   */
  public boolean remove(Room room) {
    if (room == null) return false;
    return rooms.remove(room);
  }

  /**
   * Удалить комнату по номеру.
   *
   * @param number номер комнаты
   * @return true, если удалено
   */
  public boolean removeByNumber(int number) {
    for (int i = 0; i < rooms.size(); i++) {
      if (rooms.get(i).getNumber() == number) {
        rooms.remove(i);
        return true;
      }
    }
    return false;
  }

  /** Вывести все комнаты. */
  public void printAll() {
    if (rooms.isEmpty()) {
      System.out.println("Хранилище пусто.");
      return;
    }
    System.out.println("Все комнаты (" + rooms.size() + "):");
    for (int i = 0; i < rooms.size(); i++) {
      System.out.println("  " + (i + 1) + ". " + rooms.get(i));
    }
  }

  /**
   * Найти все свободные комнаты.
   *
   * @return список свободных комнат
   */
  public List<Room> findFree() {
    List<Room> result = new ArrayList<>();
    for (Room room : rooms) {
      if (!room.isOccupied()) {
        result.add(room);
      }
    }
    return result;
  }

  /** Найти свободные комнаты и вывести их. */
  public void printFree() {
    List<Room> free = findFree();
    if (free.isEmpty()) {
      System.out.println("Свободных комнат нет.");
      return;
    }
    System.out.println("Свободные комнаты (" + free.size() + "):");
    for (Room room : free) {
      System.out.println("  " + room);
    }
  }

  /**
   * Найти комнату по номеру.
   *
   * @param number номер
   * @return комната или null
   */
  public Room findByNumber(int number) {
    for (Room room : rooms) {
      if (room.getNumber() == number) {
        return room;
      }
    }
    return null;
  }

  public int size() {
    return rooms.size();
  }

  public boolean isEmpty() {
    return rooms.isEmpty();
  }
}
