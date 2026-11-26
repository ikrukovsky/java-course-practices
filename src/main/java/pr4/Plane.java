package pr4;

public class Plane extends Ticket {

  private final int baggageGrams; // вес багажа в граммах

  public Plane(String route, int priceKopecks, int baggageGrams) {
    super(route, priceKopecks);
    if (baggageGrams < 0) {
      throw new IllegalArgumentException("Вес багажа не может быть отрицательным");
    }
    this.baggageGrams = baggageGrams;
  }

  public int getBaggageGrams() {
    return baggageGrams;
  }

  @Override
  public String validate() {
    return String.format(
        "Самолёт: маршрут %s, багаж %d г, цена %d коп., проверка пройдена.",
        getRoute(), baggageGrams, getPriceKopecks());
  }

  @Override
  public String toString() {
    return info() + String.format(", багаж: %d г", baggageGrams);
  }
}
