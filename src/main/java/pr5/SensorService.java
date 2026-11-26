package pr5;

import java.util.Locale;

/** Сервис для полиморфной обработки датчиков и их интерфейсных ролей. */
public class SensorService {
  private final Sensor[] sensors;
  private final Readable[] readableDevices;
  private final Calibratable[] calibratableDevices;

  public SensorService(
      Sensor[] sensors, Readable[] readableDevices, Calibratable[] calibratableDevices) {
    this.sensors = sensors.clone();
    this.readableDevices = readableDevices.clone();
    this.calibratableDevices = calibratableDevices.clone();
  }

  /**
   * Суммирует результаты переданной стратегии расчёта. Сам сервис не выбирает формулу, а вызывает
   * объект Calculation.
   */
  public double sumCalculations(Sensor[] items, Calculation calculation) {
    double sum = 0.0;
    for (Sensor item : items) {
      sum += calculation.calculate(item);
    }
    return sum;
  }

  /** Печатает датчики, удовлетворяющие переданному условию. */
  public void printSelected(Sensor[] items, Selection selection) {
    int selectedCount = 0;
    for (Sensor item : items) {
      if (selection.test(item)) {
        System.out.println(item);
        selectedCount++;
      }
    }
    if (selectedCount == 0) {
      System.out.println("Подходящих датчиков нет.");
    }
    System.out.println("Количество выбранных датчиков: " + selectedCount);
  }

  /** Печатает отчёт по подготовленным объектам без параметров. */
  public void printReport() {
    System.out.println();
    System.out.println("=== ОТЧЁТ ПО ДАТЧИКАМ ===");
    System.out.println("1. Общие сведения и рассчитанные значения:");
    for (Sensor sensor : sensors) {
      System.out.println(sensor);
    }

    System.out.println("2. Показания через интерфейс Readable:");
    printReadableReport(readableDevices);

    System.out.println("3. Калибровка через интерфейс Calibratable:");
    printCalibrationReport(calibratableDevices, sensors);
    System.out.println("=========================");
  }

  public void printReadableReport(Readable[] items) {
    for (Readable item : items) {
      System.out.println(item.readWithLabel());
    }
  }

  public void printCalibrationReport(Calibratable[] items, Sensor[] sourceSensors) {
    int count = Math.min(items.length, sourceSensors.length);
    for (int i = 0; i < count; i++) {
      double result = items[i].calibrate(sourceSensors[i].getRawValue());
      System.out.printf(Locale.ROOT, "%s: %.2f%n", sourceSensors[i].getId(), result);
    }
  }
}
