package pr6;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    RoomManager manager = new RoomManager(Path.of("log.txt"));
    boolean debug = false;

    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println(
          "Система бронирования комнат. Команды: book, free, status, parseRoom, debug, exit.");
      boolean running = true;

      while (running) {
        System.out.print("> ");
        if (!scanner.hasNextLine()) {
          break;
        }
        String command = scanner.nextLine().trim().toLowerCase();

        try {
          switch (command) {
            case "book" -> {
              int room = readRoom(scanner);
              System.out.print("Имя гостя: ");
              String guest = scanner.nextLine();
              manager.book(room, guest);
              System.out.println("Комната успешно забронирована.");
            }
            case "free" -> {
              int room = readRoom(scanner);
              manager.free(room);
              System.out.println("Комната освобождена.");
            }
            case "status" -> {
              int room = readRoom(scanner);
              System.out.println(manager.status(room));
            }
            case "parseRoom" -> {
              System.out.print("Введите номер комнаты: ");
              System.out.println("Результат: " + RoomParser.parseRoomNumber(scanner.nextLine()));
            }
            case "debug" -> {
              debug = !debug;
              System.out.println("Debug: " + (debug ? "ON" : "OFF"));
            }
            case "exit" -> running = false;
            case "" -> {}
            default -> System.out.println("Неизвестная команда.");
          }
        } catch (RoomOccupiedException
            | IllegalArgumentException
            | IllegalStateException
            | IOException exception) {
          System.out.println("Ошибка: " + exception.getMessage());
          if (debug) {
            exception.printStackTrace(System.out);
          }
        }
      }
    }
    System.out.println("Работа программы завершена.");
  }

  private static int readRoom(Scanner scanner) {
    System.out.print("Номер комнаты: ");
    return RoomParser.parseRoomNumber(scanner.nextLine());
  }
}
