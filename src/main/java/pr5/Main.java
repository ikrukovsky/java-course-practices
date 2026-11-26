package pr5;

/** Демонстрация наследования, интерфейсов, лямбд и ссылок на методы. */
public class Main {
  private Main() {}

  public static void main(String[] args) {
    Sensor[] sensors = {
      new Temp("T-01", "Лаборатория", 23.5, 0.4),
      new Pressure("P-02", "Котельная", 101.3, 1.02),
      new Light("L-03", "Аудитория", 450.0, 2.5)
    };

    Readable[] readableDevices = {
      (Readable) sensors[0], (Readable) sensors[1], (Readable) sensors[2]
    };
    Calibratable[] calibratableDevices = {
      (Calibratable) sensors[0], (Calibratable) sensors[1], (Calibratable) sensors[2]
    };
    SensorService service = new SensorService(sensors, readableDevices, calibratableDevices);

    System.out.println("=== СРАВНЕНИЕ Calculation ===");
    Calculation anonymousCalculation =
        new Calculation() {
          @Override
          public double calculate(Sensor sensor) {
            return sensor.calibratedValue();
          }
        };
    Calculation lambdaCalculation = sensor -> sensor.calibratedValue();

    double anonymousSum = service.sumCalculations(sensors, anonymousCalculation);
    double lambdaSum = service.sumCalculations(sensors, lambdaCalculation);
    System.out.printf("Сумма (анонимный класс): %.2f%n", anonymousSum);
    System.out.printf("Сумма (лямбда): %.2f%n", lambdaSum);
    System.out.println("Суммы совпадают: " + (Math.abs(anonymousSum - lambdaSum) < 0.000001));

    System.out.println();
    System.out.println("=== ОТБОР: значение больше 100 ===");
    double firstThreshold = 100.0;
    Selection aboveFirstThreshold = sensor -> sensor.calibratedValue() > firstThreshold;
    service.printSelected(sensors, aboveFirstThreshold);

    System.out.println();
    System.out.println("=== ОТБОР: значение больше 500 ===");
    double secondThreshold = 500.0;
    Selection aboveSecondThreshold = sensor -> sensor.calibratedValue() > secondThreshold;
    service.printSelected(sensors, aboveSecondThreshold);

    Runnable report = service::printReport;
    report.run();
  }
}
