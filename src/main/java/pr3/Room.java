package pr3;

import java.util.Objects;

/**
 * Класс сущности "Комната отеля".
 * Вариант 11.
 * 
 * @author Круковский Иван Антонович
 * @group БОМ31ПРИ
 */
public class Room {

    // ================== ПОЛЯ (private) ==================
    private int number;        // номер комнаты
    private String type;       // тип (Standard, Lux, Suite, ...)
    private int price;      // цена за сутки
    private boolean occupied;  // занята ли

    // ================== КОНСТРУКТОР С ПРОВЕРКАМИ ==================
    /**
     * @param number   номер комнаты (должен быть > 0)
     * @param type     тип комнаты (не пустой)
     * @param price    цена за сутки (>= 0)
     * @param occupied занята ли комната
     */
    public Room(int number, String type, int price, boolean occupied) {
        if (number <= 0) {
            throw new IllegalArgumentException("Номер комнаты должен быть > 0");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Тип комнаты не может быть пустым");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
        this.number = number;
        this.type = type;
        this.price = price;
        this.occupied = occupied;
    }

    // ================== GETTERS ==================
    public int getNumber()     { return number; }
    public String getType()    { return type; }
    public int getPrice()   { return price; }
    public boolean isOccupied(){ return occupied; }

    // ================== SETTERS (по необходимости) ==================
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
        this.price = price;
    }

    // ================== toString ==================
    @Override
    public String toString() {
        return String.format(
            "Room{номер=%d, тип='%s', цена=%d, %s}",
            number, type, price/100, occupied ? "занята" : "свободна"
        );
    }

    // ================== equals / hashCode ==================
    /**
     * Две комнаты считаются равными, если у них совпадают номер и тип.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return number == room.number && Objects.equals(type, room.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type);
    }
}