package pr4;

public class Bus extends Ticket {

  private final int seat; // номер места

  public Bus(String route, int priceKopecks, int seat) {
    super(route, priceKopecks);
    if (seat <= 0) {
      throw new IllegalArgumentException("Номер места должен быть > 0");
    }
    this.seat = seat;
  }

  public int getSeat() {
    return seat;
  }

  @Override
  public String validate() {
    return String.format(
        "Автобус: маршрут %s, место №%d, цена %d коп., проверка пройдена.",
        getRoute(), seat, getPriceKopecks());
  }

  @Override
  public String toString() {
    return info() + String.format(", место: %d", seat);
  }
}
