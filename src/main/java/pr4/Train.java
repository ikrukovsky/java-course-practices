package pr4;

public class Train extends Ticket {

  private final int car; // номер вагона

  public Train(String route, int priceKopecks, int car) {
    super(route, priceKopecks); // вызов конструктора базового класса
    if (car <= 0) {
      throw new IllegalArgumentException("Номер вагона должен быть > 0");
    }
    this.car = car;
  }

  public int getCar() {
    return car;
  }

  @Override
  public String validate() {
    return String.format(
        "Поезд: маршрут %s, вагон №%d, цена %d коп., проверка пройдена.",
        getRoute(), car, getPriceKopecks());
  }

  @Override
  public String toString() {
    return info() + String.format(", вагон: %d", car);
  }
}
