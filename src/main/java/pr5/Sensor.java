package pr5;

/** Общая модель датчика. */
public abstract class Sensor {
  private final String id;
  private final String location;
  private final double rawValue;
  private final double calibrationParameter;

  protected Sensor(String id, String location, double rawValue, double calibrationParameter) {
    if (id == null || id.isBlank()) {
      throw new IllegalArgumentException("Идентификатор датчика не должен быть пустым");
    }
    if (location == null || location.isBlank()) {
      throw new IllegalArgumentException("Место установки не должно быть пустым");
    }
    if (!Double.isFinite(rawValue) || !Double.isFinite(calibrationParameter)) {
      throw new IllegalArgumentException("Числовые параметры должны быть конечными");
    }
    this.id = id;
    this.location = location;
    this.rawValue = rawValue;
    this.calibrationParameter = calibrationParameter;
  }

  /** Возвращает значение после калибровки. */
  public abstract double calibratedValue();

  public String getId() {
    return id;
  }

  public String getLocation() {
    return location;
  }

  public double getRawValue() {
    return rawValue;
  }

  public double getCalibrationParameter() {
    return calibrationParameter;
  }

  @Override
  public String toString() {
    return String.format(
        java.util.Locale.ROOT,
        "%s{id='%s', место='%s', исходное=%.2f, после калибровки=%.2f}",
        getClass().getSimpleName(),
        id,
        location,
        rawValue,
        calibratedValue());
  }
}
